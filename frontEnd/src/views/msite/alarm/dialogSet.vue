<template>
    <el-dialog title="告警设置" :visible.sync="dialogInfo.visible" width="460px" v-dialogDrag class="custom">
        <el-scrollbar style="height:290px;" class="loncom_scrollbar">
            <div class="loncom_public_table" v-loading="loading">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-form-item label="是否启用" prop="model" size="small">
                        <el-radio-group v-model="ruleForm.model">
                            <el-radio label="1">启用</el-radio>
                            <el-radio label="0">不启用</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="日用电量阀值" prop="dayvalue" size="small">
                        <el-input v-model="ruleForm.dayvalue" class="elinput"></el-input>
                    </el-form-item>
                    <el-form-item label="月用电量阀值" prop="monthvalue" size="small">
                        <el-input v-model="ruleForm.monthvalue" class="elinput" ></el-input>
                    </el-form-item>
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
        this.getDetail();
    },
    mounted() {

    },
    data() {
        let checknumber=(rules,value,callback)=>{
            this.$tool.checkNumber({rules,value,callback})
        };
        return {
            loading:false,
            ruleForm: {
                id:'',
                model:'1',
                dayvalue:'',
                monthvalue:'',
            },
            rules: {
                dayvalue: [
                    { required: true, trigger: 'blur' ,validator:checknumber}
                ],
                monthvalue: [
                    { required: true, trigger: 'blur' ,validator:checknumber}
                ],
            },
        }
    },
    methods:{
        //获取详情
        getDetail:function(){
            this.$api.post('/alarm/threshold', {}, r => {
                if(r.err_code=="0"){
                    if(r.data){
                        this.ruleForm=r.data;
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        //保存的操作
        dialogSure:function(){
            this.$refs['ruleForm'].validate((valid) => {
                if(valid){ //验证通过
                    this.loading=true;
                    this.$api.post('/alarm/updateThreshold',this.ruleForm, r => {
                        
                        this.loading=false;
                        if(r.err_code=="0"){
                            this.$message.success(r.err_msg);
                            this.dialogInfo.visible=false;
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
