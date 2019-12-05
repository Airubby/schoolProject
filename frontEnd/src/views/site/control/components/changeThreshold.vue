<template>
    <el-dialog title="批量操作" :visible.sync="dialogInfo.visible" width="460px" v-dialogDrag class="custom">
        <el-scrollbar style="height:290px;" class="loncom_scrollbar">
            <div class="loncom_public_table" v-loading="loading">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-form-item label="控制模式" prop="model" size="small">
                        <el-radio-group v-model="ruleForm.model">
                            <el-radio label="1">自动</el-radio>
                            <el-radio label="0">手动</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <div v-if="ruleForm.model=='1'">
                        <el-form-item label="无人检测时间" prop="times" size="small">
                            <el-input v-model="ruleForm.times" class="elinput" placeholder="多少分钟监测一下，单位分钟"></el-input>
                        </el-form-item>
                        <el-form-item label="时间范围" prop="timegroup" size="small">
                            <el-select v-model="ruleForm.timegroup" placeholder="请选择" class="elinput">
                                <el-option key="" label="所有" value=""></el-option>
                                <el-option
                                v-for="item in options"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <!--
                        <el-form-item label="空调开机温度" prop="openair" size="small">
                            <el-input v-model="ruleForm.openair" class="elinput" placeholder="开机温度"></el-input>
                        </el-form-item>
                        -->
                        <el-form-item label="空调关机温度" prop="closeair" size="small">
                            <el-input v-model="ruleForm.closeair" class="elinput" placeholder="关机温度"></el-input>
                        </el-form-item>
                    </div>
                    <el-form-item label="房间名称" prop="classname" size="small">
                        <el-input v-model="ruleForm.classname" @focus="selectClass" readonly type="textarea" class="elinput" placeholder="需要修改的房间"></el-input>
                    </el-form-item>
                    <div v-if="backData!=''" style="padding-bottom:20px;">
                        <h2 style="color:#B9C6F2;margin-bottom:10px;">下发失败的房间</h2>
                        <div style="color:#F56C6C;">{{backData}}</div>
                    </div>
                </el-form>
            </div>
        </el-scrollbar>
        <get-class-name :dialogInfo="selectInfo" v-if="dialogInfo.visible" v-on:backInfo="backInfo"></get-class-name>
        <dialogBtnInfo v-bind:dialogInfobtn="dialogInfo" v-on:dialogSure="dialogSure()"></dialogBtnInfo>
    </el-dialog>
</template>

<script>
import dialogBtnInfo from '@/components/dialogBtnInfo.vue'
import getClassName from './getClassName.vue'
export default {
    created () {
        this.getTime();
    },
    mounted() {

    },
    data() {
        let checknumber=(rules,value,callback)=>{
            this.$tool.checkNumber({rules,value,callback})
        };
        return {
            options:[],
            loading:false,
            ruleForm: {
                classname:'',
                code:"",
                model:'1',
                times:'',
                timegroup:'',
                // openair:'',
                closeair:'',
                serviceid:'',
                classnode:[]
            },
            ruleForm1:{
                classname:'',
                code:"",
                model:'1',
                times:'',
                timegroup:'',
                // openair:'',
                closeair:'',
                serviceid:'',
            },
            backData:"",
            rules: {
                classname: [
                    { required: true, trigger: 'change' , message: '请选择房间名称'}
                ],
                times: [
                    { required: true, trigger: 'blur' ,validator:checknumber}
                ],
                // openair: [
                //     { required: true, trigger: 'blur' ,validator:checknumber}
                // ],
                closeair: [
                    { required: true, trigger: 'blur' ,validator:checknumber}
                ],
            },
            selectInfo:{
                visible:false
            }
        }
    },
    methods:{
        getTime:function(){
            this.$api.post('/time/query', {}, r => {
                
                if(r.err_code=="0"){
                    this.options=r.data;
                }
            })
        },
        selectClass:function(){
            this.selectInfo.visible=true;
        },
        backInfo:function(info){
            if(info.length>0){
                let arr=[];
                for(let i=0;i<info.length;i++){
                    arr.push(info[i].classname);
                }
                this.ruleForm.classname=arr.toString();
                this.ruleForm.classnode=info;
            }else{
                this.ruleForm.classname="";
                this.ruleForm.classnode=[];
            }
        },
        //保存的操作
        dialogSure:function(){
            this.$refs['ruleForm'].validate((valid) => {
                if(valid){ //验证通过
                    this.loading=true;
                    // this.$api.post('/service/updateT',this.ruleForm, r => {
                        
                    //     this.loading=false;
                    //     if(r.err_code=="0"){
                    //         this.$message.success(r.err_msg);
                    //         this.dialogInfo.visible=false;
                    //         this.$parent.getList();
                    //     }else{
                    //         this.$message.warning(r.err_msg);
                    //     }
                    // });
                    this.backData="";
                    this.ruleForm1.code=this.ruleForm.code;
                    this.ruleForm1.model=this.ruleForm.model;
                    this.ruleForm1.times=this.ruleForm.times;
                    this.ruleForm1.timegroup=this.ruleForm.timegroup;
                    this.ruleForm1.closeair=this.ruleForm.closeair;
                    this.update();
                    
                }
            })
        },
        async update(){
            for(let i=0;i<this.ruleForm.classnode.length;i++){
                await this.updateFn(this.ruleForm.classnode[i]);
            }
            this.loading=false;
            if(this.backData==""){
                this.$emit("backInfo");
                this.$message.success("批量下发成功！");
                this.dialogInfo.visible=false;
            }
        },
        updateFn(node){
            return new Promise((resolve, reject)=>{
                this.ruleForm1.classname=node.classname;
                this.ruleForm1.code=node.code;
                this.ruleForm1.serviceid=node.serviceid;
                this.$api.post('/service/update',this.ruleForm1, r => {
                    console.log(this.ruleForm1)
                    if(r.err_code!="0"){
                        this.backData+=node.classname+"；";
                    }
                    resolve();
                });
            })
        }


    },
    watch:{
        
    },
    components:{dialogBtnInfo,getClassName},
    props:["dialogInfo"]
}
</script>
<style scoped>
    .loncom_public_table .elinput{width:80%;}
</style>
