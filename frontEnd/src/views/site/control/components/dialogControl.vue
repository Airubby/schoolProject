<template>
    <el-dialog title="模式设置" :visible.sync="dialogInfo.visible" width="460px" v-dialogDrag class="custom">
        <el-scrollbar style="height:290px;" class="loncom_scrollbar">
            <div class="loncom_public_table" v-loading="loading">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-form-item label="房间名称" prop="classname" size="small">
                        <el-input v-model="ruleForm.classname" class="elinput" placeholder="房间的备注名称"></el-input>
                    </el-form-item>
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
                </el-form>
            </div>
        </el-scrollbar>
        <dialogBtnInfo v-bind:dialogInfobtn="dialogInfo" v-on:dialogSure="dialogSure()"></dialogBtnInfo>
    </el-dialog>
</template>

<script>
import dialogBtnInfo from '@/components/dialogBtnInfo.vue'
export default {
    created () {
        this.getTime();
        this.getDetail();
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
                code:'',
                model:'1',
                times:'',
                timegroup:'',
                // openair:'',
                closeair:'',
                serviceid:'',
            },
            rules: {
                classname: [
                    { required: true, trigger: 'blur' , message: '请输入房间名称'}
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
        }
    },
    methods:{
        getTime:function(){
            this.$api.post('/time/query', {}, r => {
                console.log(r)
                if(r.err_code=="0"){
                    this.options=r.data;
                }
            })
        },
        //获取详情
        getDetail:function(){
            console.log(this.dialogInfo.code)
            this.$api.post('/service/detail', {"code":this.dialogInfo.code}, r => {
                console.log(r)
                if(r.err_code=="0"){
                    for(let item in this.ruleForm){
                        this.ruleForm[item]=r.data[item];
                    }
                }
            })
        },
        //保存的操作
        dialogSure:function(){
            this.$refs['ruleForm'].validate((valid) => {
                if(valid){ //验证通过
                    this.loading=true;
                    this.$api.post('/service/update',this.ruleForm, r => {
                        console.log(r)
                        this.loading=false;
                        if(r.err_code=="0"){
                            this.$message.success(r.err_msg);
                            this.dialogInfo.visible=false;
                            this.$parent.getList();
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });
                }
            })
        },

    },
    watch:{
        
    },
    components:{dialogBtnInfo},
    props:["dialogInfo"]
}
</script>
<style scoped>
    .loncom_public_table .elinput{width:80%;}
</style>
