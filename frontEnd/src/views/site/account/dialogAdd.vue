<template>
    <el-dialog :title="dialogInfo.title" :visible.sync="dialogInfo.visible" width="900px" v-dialogDrag class="custom">
        <el-scrollbar style="height:350px;" class="loncom_scrollbar">
            <div class="loncom_public_table">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-row :gutter="40">
                        <el-col :span="12">
                            <el-form-item label="账号" prop="userid" size="small">
                                <el-input v-model="ruleForm.userid" :disabled="readonly"></el-input>
                            </el-form-item>
                        </el-col>   
                        <el-col :span="12">
                            <el-form-item label="密码" prop="psword" size="small">
                                <el-input v-model.trim="ruleForm.psword" type="password"></el-input>
                            </el-form-item>
                        </el-col>   
                        <el-col :span="12">
                            <el-form-item label="确认密码" prop="tpassword" size="small">
                                <el-input v-model.trim="ruleForm.tpassword" type="password"></el-input>
                            </el-form-item>
                        </el-col>   
                        <el-col :span="12">
                            <el-form-item label="姓名" prop="name" size="small">
                                <el-input v-model="ruleForm.name"></el-input>
                            </el-form-item>
                        </el-col>   
                        <div v-if="ruleForm.userid!='admin'">
                            <el-col :span="12">
                                <el-form-item label="角色" prop="roleid" size="small">
                                    <el-select v-model="ruleForm.roleid">
                                        <template v-for="item in role_data">
                                            <el-option :label="item.name" :value="item.id"></el-option>
                                        </template>
                                    </el-select>
                                </el-form-item>
                            </el-col>  
                            <el-col :span="12">
                                <el-form-item label="状态" prop="state" size="small">
                                    <el-select v-model="ruleForm.state">
                                        <el-option label="启用" value="1"></el-option>
                                        <el-option label="停用" value="0"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>   
                            <el-col :span="12">
                                <el-form-item label="开始时间" prop="time_start" size="small">
                                    <el-date-picker
                                        style="width:100%"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        v-model="ruleForm.time_start"
                                        type="datetime"
                                        placeholder="选择日期时间">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>   
                            <el-col :span="12">
                                <el-form-item label="结束时间" prop="time_end" size="small">
                                    <el-date-picker
                                        style="width:100%"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        v-model="ruleForm.time_end"
                                        type="datetime"
                                        placeholder="选择日期时间">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>  
                            <el-col :span="12">
                                <el-form-item label="管理域" prop="addrorrole" size="small">
                                    <el-input v-model="ruleForm.addrname" @focus="selectTree"></el-input>
                                </el-form-item>
                            </el-col>    
                        </div>
                        <el-col :span="12">
                            <el-form-item label="手机" prop="phone" size="small">
                                <el-input v-model="ruleForm.phone"></el-input>
                            </el-form-item>
                        </el-col>   
                        <el-col :span="12">
                            <el-form-item label="邮箱" prop="email" size="small">
                                <el-input v-model="ruleForm.email"></el-input>
                            </el-form-item>
                        </el-col>   
                    </el-row>
                </el-form>
            </div>
        </el-scrollbar>
        <dialogBtnInfo v-bind:dialogInfobtn="dialogInfo" v-on:dialogSure="dialogSure()"></dialogBtnInfo>
        <tree :dialogInfo="ruleForm" v-if="ruleForm.visible"></tree>
    </el-dialog>
</template>

<script>
import dialogBtnInfo from '@/components/dialogBtnInfo.vue'
import store from '@/store/index'
import tree from './tree.vue'
export default {
    created () {
        if(this.dialogInfo.id){
            this.readonly=true;
            this.$api.get('/user/details', {id:this.dialogInfo.id}, r => {
                
                if(r.err_code=="0"){
                    for(var item in this.ruleForm){
                        this.ruleForm[item]=r.data[item];
                    }
                    this.ruleForm['psword']=this.$tool.Decrypt(this.ruleForm['psword']);
                    this.ruleForm['tpassword']=this.ruleForm['psword'];
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        }
        this.getArea();
        this.getRole();
    },
    mounted() {

    },
    data() {
        let checkphone=(rules,value,callback)=>{
            this.$tool.checkPHONE({rules,value,callback})
        };
        let checkemail=(rules,value,callback)=>{
            this.$tool.checkEMAIL({rules,value,callback})
        };
        let validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码至少8位且包含数字、字母大小写'));
            } else {
                if (this.ruleForm.psword) {
                    if(/[a-z]/.test(value) && /[A-Z]/.test(value) && /[0-9]/.test(value)&& value.length>7){
                        callback();
                    }else{
                        callback(new Error('请输入密码至少8位且包含数字、字母大小写'));
                    }
                }
                callback();
            }
        };
        let validateTPass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入确认密码'));
            } else {
                if (this.ruleForm.psword !== this.ruleForm.tpassword) {
                    callback(new Error('两次密码不一样'));
                }
                callback();
            }
        };
        let checkuser = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入账号'));
            } else {
                if(this.ruleForm.id==""){
                    this.$api.get('/user/synuserid', {"userid":value}, r => {
                        
                        if(r.err_code=="0"){
                            callback();
                        }else{
                            callback(r.err_msg);
                        }
                    });
                }else{
                    callback();
                }
            }
        };
        let validateStart = (rule, value, callback) => {
            if (value === ''||value==null) {
                
                callback(new Error('请输开始时间'));
            } else {
                if(this.ruleForm.time_end){
                    if(new Date(value).getTime()>=new Date(this.ruleForm.time_end).getTime()){
                        callback(new Error('开始时间应先于结束时间'));
                    }
                }
                callback()
            }
        };
        let validateEnd = (rule, value, callback) => {
            if (value === ''||value==null) {
                callback(new Error('请输入结束时间'));
            } else {
                if(this.ruleForm.time_start){
                    if(new Date(value).getTime()<=new Date(this.ruleForm.time_start).getTime()){
                        callback(new Error('结束时间应后于开始时间应'));
                    }
                }
                callback();
            }
        };
        return {
            loginInfo:{},
            readonly:false,
            treedata:[],
            role_data:[],
            ruleForm: {
                id:'',
                userid:'',
                psword:'',
                tpassword:'',
                name: '',
                addrorrole: "",
                addrname: "",
                time_start: '',
                time_end:'',
                phone: '',
                email: '',
                roleid:'',
                state:'',
                visible:false,
            },
            rules: {
                userid: [
                    { required: true,  trigger: 'blur',validator:checkuser }
                ],
                name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' }
                ],
                psword: [
                    { validator: validatePass,required: true, trigger: 'blur' }
                ],
                tpassword: [
                    { validator: validateTPass,required: true, trigger: 'blur' }
                ],
                time_start:[
                    { validator: validateStart,required: true, trigger: 'blur' }
                ],
                time_end:[
                    { validator: validateEnd,required: true, trigger: 'blur' }
                ],
                phone:[
                    { required: false, trigger: 'blur' ,validator:checkphone}
                ],
                email:[
                    { required: false, trigger: 'blur' ,validator:checkemail}
                ],
                roleid:[
                    { required: true, message: '请选择角色', trigger: 'change' }
                ],
                state:[
                    { required: true, message: '请选择状态', trigger: 'change' }
                ]
            },
        }
    },
    methods:{
        getArea:function(){
            this.$api.get("/service/tree",{},r =>{
                
                if(r.err_code=="0"){
                    this.treedata=r.data;
                }
            });
        },
        getRole:function(){
            this.$api.post('/role/query', {}, r => {
                
                if(r.err_code=="0"){
                  this.role_data=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        selectTree:function(){
            this.ruleForm.visible=true;
        },
        //保存的操作
        dialogSure:function(){
            this.$refs['ruleForm'].validate((valid) => {
                if (valid) {
                    let url=this.ruleForm.id?'/user/update':'/user/add';
                    this.ruleForm.psword=this.$tool.Encrypt(this.ruleForm.psword);
                    this.ruleForm.tpassword=this.$tool.Encrypt(this.ruleForm.tpassword);
                    this.ruleForm.addrorrole=this.ruleForm.addrorrole.toString();
                    this.$api.post(url, {"obj":this.ruleForm}, r => {
                        
                        if(r.err_code=="0"){
                            this.$message.success(r.err_msg);
                            this.$parent.getList();
                            let userid=(this.$tool.Decrypt(sessionStorage.energyInfo)&&this.$tool.Decrypt(sessionStorage.energyInfo).split("_").length>1)?this.$tool.Decrypt(sessionStorage.energyInfo).split("_")[1]:""
                            if(this.ruleForm.id&&this.ruleForm.id==userid){
                                store.dispatch('setChangeUser',this.ruleForm);
                            }
                            this.dialogInfo.visible=false;
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });
                } 
            });
        },

    },
    watch:{
    },
    components:{dialogBtnInfo,tree},
    props:["dialogInfo"]
}
</script>

