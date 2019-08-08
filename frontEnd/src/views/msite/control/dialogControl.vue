<template>
    <div class="loncom_content pabsolute">
        <div class="public_top">
            <div class="btn btntxt" @click="dialogCancel">
                <i class="el-icon-arrow-left"></i>返回
            </div>
            <div class="title">手动操作</div>
            <div class="btn btntxt" @click="dialogSure">
                保存<i class="el-icon-check"></i>
            </div>
        </div>
        <div class="public_content custom" v-loading="loading">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                <el-form-item label="控制模式" prop="model" size="small">
                    <el-radio-group v-model="ruleForm.model">
                        <el-radio label="1">自动</el-radio>
                        <el-radio label="0">手动</el-radio>
                    </el-radio-group>
                </el-form-item>
                <div v-if="ruleForm.model=='1'">
                    <el-form-item label="规则描述" prop="times" size="small" class="elinput">
                        <el-input v-model="ruleForm.times" placeholder="多少分钟监测一下，单位分钟"></el-input>
                    </el-form-item>
                    <el-form-item label="时间范围" prop="timegroup" size="small" class="elinput">
                        <el-select v-model="ruleForm.timegroup" placeholder="请选择">
                            <el-option key="" label="所有" value=""></el-option>
                            <el-option
                            v-for="item in options"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="空调关机温度" prop="closeair" size="small" class="elinput">
                        <el-input v-model="ruleForm.closeair" placeholder="关机温度"></el-input>
                    </el-form-item>
                </div>
            </el-form>
        </div>
    </div>
    <!--
    <el-dialog title="手动操作" :visible.sync="dialogInfo.visible" width="460px" v-dialogDrag class="custom">
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
                        <el-form-item label="空调关机温度" prop="closeair" size="small">
                            <el-input v-model="ruleForm.closeair" class="elinput" placeholder="关机温度"></el-input>
                        </el-form-item>
                    </div>
                </el-form>
            </div>
        </el-scrollbar>
        <dialogBtnInfo v-bind:dialogInfobtn="dialogInfo" v-on:dialogSure="dialogSure()"></dialogBtnInfo>
    </el-dialog>
    -->
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
                // openair:'',
                closeair:'',
                serviceid:'',
            },
            rules: {
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
        //取消操作
        dialogCancel:function(){
            this.dialogInfo.visible=false;
        },

    },
    watch: {
        'dialogInfo.visible':function(val){
            if(val){
                this.$el.style.display="block";
                this.$el.style.left="0";
            }else{
                this.$el.style.display="none";
                this.$el.style.left="100%";
            }
            this.$el.style.transition="all 0.4s ease-in";
        }
    },
    components:{dialogBtnInfo},
    props:["dialogInfo"]
}
</script>
<style lang="less" scoped>

    .loncom_content{
        left: 100%;
        top:0;
        z-index:999;
        background: #151936;
        display:none;
        .elinput{width:80%;}
        .public_content{
            padding-top:30px;
        }
    }
</style>

