<template>
    <el-dialog title="手动操作" :visible.sync="dialogInfo.visible" width="450px" v-dialogDrag class="custom">
        <el-scrollbar style="height:200px;" class="loncom_scrollbar">
            <div class="loncom_public_table" v-loading="loading">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="控制模式" prop="model" size="small">
                        <el-radio-group v-model="ruleForm.model">
                            <el-radio label="1">自动</el-radio>
                            <el-radio label="0">手动</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <div v-if="ruleForm.model=='1'">
                    <el-form-item label="规则描述" prop="times" size="small">
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
                code:'',
                model:'1',
                times:'',
                timegroup:'',
            },
            rules: {
                times: [
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
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });
                }
            })
        },
        //取消操作
        dialogCancel:function(){
            this.dialogInfo.visible=false;
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
