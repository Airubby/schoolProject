<template>
    <el-dialog :title="dialogInfo.title" :visible.sync="dialogInfo.visible" width="500px" v-dialogDrag class="custom">
        <el-scrollbar style="height:400px;" class="loncom_scrollbar">
            <div class="loncom_public_table">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-row :gutter="40">
                        <el-col :span="20">
                            <el-form-item label="名称" prop="name" size="small">
                                <el-input v-model="ruleForm.name"></el-input>
                            </el-form-item>
                        </el-col>   
                        <el-col :span="20">
                            <el-form-item label="日期" prop="date" size="small">
                                <el-date-picker
                                style="width:100%"
                                value-format="yyyy-MM-dd hh:mm:ss"
                                v-model="ruleForm.date"
                                type="datetimerange"
                                placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col> 
                        <el-col :span="20">
                            <el-form-item label="周范围" prop="week" size="small">
                               <el-checkbox-group v-model="week">
                                    <el-checkbox v-for="week in options" :label="week.value" :key="week.value">{{week.label}}</el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </el-col>      
                        <el-col :span="20">
                            <el-form-item label="时区范围" prop="time">
                                <div class="loncom_mb10">
                                    <el-time-picker
                                        is-range
                                        style="width:100%"
                                        v-model="time.value1"
                                        format="HH:mm:ss"
                                        value-format="HH:mm:ss"
                                        range-separator="至"
                                        start-placeholder="开始时间"
                                        end-placeholder="结束时间"
                                        placeholder="选择时间范围"
                                        size="small"
                                        >
                                    </el-time-picker>
                                </div>
                                <div class="loncom_mb10">
                                    <el-time-picker
                                        is-range
                                        style="width:100%"
                                        v-model="time.value2"
                                        format="HH:mm:ss"
                                        value-format="HH:mm:ss"
                                        range-separator="至"
                                        start-placeholder="开始时间"
                                        end-placeholder="结束时间"
                                        placeholder="选择时间范围"
                                        size="small"
                                        >
                                    </el-time-picker>
                                </div>
                                <div class="loncom_mb10">
                                    <el-time-picker
                                        is-range
                                        style="width:100%"
                                        v-model="time.value3"
                                        format="HH:mm:ss"
                                        value-format="HH:mm:ss"
                                        range-separator="至"
                                        start-placeholder="开始时间"
                                        end-placeholder="结束时间"
                                        placeholder="选择时间范围"
                                        size="small"
                                        >
                                    </el-time-picker>
                                </div>
                            </el-form-item>
                        </el-col>  
                    </el-row>
                </el-form>
            </div>
        </el-scrollbar>
        <dialogBtnInfo v-bind:dialogInfobtn="dialogInfo" v-on:dialogSure="dialogSure()"></dialogBtnInfo>
    </el-dialog>
</template>

<script>
import dialogBtnInfo from '@/components/dialogBtnInfo.vue'
import store from '@/store/index'
export default {
    created () {
        if(sessionStorage.loginInfo){
            this.loginInfo=JSON.parse(sessionStorage.loginInfo);
        }
        if(this.dialogInfo.id){
            this.readonly=true;
            this.getDetail();
        }
    },
    mounted() {

    },
    data() {
        let checktime = (rule, value, callback) => {
            console.log(value)
            if (value === ''||value==null) {
                callback(new Error('请选择时间范围'));
            } else {
                if(value=="false"){
                    callback(new Error('时间范围重复'));
                }
                callback();
            }
        };
        return {
            readonly:false,
            time:{
                value1: "",
                value2: "",
                value3: "",
            },
            week:[],
            options: [
                {value:'1',label:'周一'},{value:'2',label:'周二'},{value:'3',label:'周三'},{value:'4',label:'周四'},
                {value:'5',label:'周五'},{value:'6',label:'周六'},{value:'7',label:'周天'}
            ],
            ruleForm: {
                id:'',
                name: '',
                date:'',
                week:'',
                time:'',
            },
            rules: {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' }
                ],
                date: [
                    { required: true, message: '请选择日期范围', trigger: 'change' },
                ],
                week: [
                    { required: true, message: '请选择周范围', trigger: 'change' },
                ],
                time: [
                    { required: true,  trigger: 'change',validator:checktime },
                ]
            },
        }
    },
    methods:{
        getDetail:function(){
            this.$api.get('/time/detail', {id:this.dialogInfo.id}, r => {
                console.log(r)
                if(r.err_code=="0"){
                   this.ruleForm.id=r.data.id;
                   this.ruleForm.name=r.data.name;
                   this.ruleForm.date=[r.data.start_time,r.data.end_time];
                   let timedeteil=r.data.timeDeteil;
                   if(timedeteil&&timedeteil.length>0){
                        let first="";
                        for(let i=0;i<timedeteil.length;i++){
                            if(i==0){
                                this.week.push(timedeteil[i].timeweek);
                                this.time.value1=[timedeteil[i].begintime,timedeteil[i].endtime];
                                first=timedeteil[i].timeweek;
                            }else{
                                if(this.week.toString().indexOf(timedeteil[i].timeweek) != -1){ //包含了
                                    if(first==timedeteil[i].timeweek){
                                        if(this.time.value2==""){
                                            this.time.value2=[timedeteil[i].begintime,timedeteil[i].endtime]
                                        }else{
                                            this.time.value3=[timedeteil[i].begintime,timedeteil[i].endtime]
                                        }
                                    }
                                }else{
                                    this.week.push(timedeteil[i].timeweek)
                                }
                            }
                        }
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        //保存的操作
        dialogSure:function(){
            this.$refs['ruleForm'].validate((valid) => {
                if (valid) {
                    let timeweek=[];
                    for(let i=0;i<this.week.length;i++){
                        if(this.time.value1!=''&&this.time.value1!=null){
                            timeweek.push({"begintime":this.time.value1[0], "endtime": this.time.value1[1], "timeweek": this.week[i]})
                        }
                        if(this.time.value2!=''&&this.time.value2!=null){
                            timeweek.push({"begintime":this.time.value2[0], "endtime": this.time.value2[1], "timeweek": this.week[i]})
                        }
                        if(this.time.value3!=''&&this.time.value3!=null){
                            timeweek.push({"begintime":this.time.value3[0], "endtime": this.time.value3[1], "timeweek": this.week[i]})
                        }
                    }
                    let param={};
                    param.id=this.ruleForm.id;
                    param.name=this.ruleForm.name;
                    param.start_time=this.ruleForm.date[0];
                    param.end_time=this.ruleForm.date[1];
                    let url=this.ruleForm.id?'/time/update':'/time/add';
                    this.$api.post(url,{"obj":param,"item":timeweek}, r => {
                        console.log(r)
                        if(r.err_code=="0"){
                            this.$message.success(r.err_msg);
                            this.$parent.getList();
                            this.dialogInfo.visible=false;
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });
                } 
            });
        },
        //取消操作
        dialogCancel:function(){
            this.dialogInfo.visible=false;
        },

    },
    watch: {
        week:function(val){
            if(val.length>0){
                this.ruleForm.week=true;
            }else{
                this.ruleForm.week="";
            }
        },
        time:{
            handler:function(val){
                if((val.value1==null||val.value1=="")&&(val.value2==null||val.value2=="")&&(val.value3==null||val.value3=="")){
                    this.ruleForm.time="";
                }else{
                    let value1=val.value1?val.value1.toString():"";
                    let value2=val.value2?val.value2.toString():"";
                    let value3=val.value3?val.value3.toString():"";
                    if((value1==value2&&value1!=""&&value1!=null)||(value2==value3&&value2!=""&&value2!=null)||(value1==value3&&value1!=""&&value1!=null)){
                        this.ruleForm.time="false"
                    }else{
                        this.ruleForm.time="true";
                    }
                }
            },
            deep: true
        }
    },
    components:{dialogBtnInfo},
    props:["dialogInfo"]
}
</script>

