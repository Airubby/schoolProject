<template>
    <el-dialog title="修改名称" :visible.sync="dialogInfo.visible" width="800px" v-dialogDrag class="custom">
        <el-scrollbar style="height:400px;" class="loncom_scrollbar">
            <div class="loncom_public_table" v-loading="loading">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-search-table-pagination type="local" 
                        border :data="table_data" :columns="table_columns" stripe>   
                        <template slot-scope="scope" slot="preview-name">
                            <el-input v-model="scope.row.newname"></el-input>
                        </template>
                    </el-search-table-pagination>
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
        this.getList();
    },
    mounted() {

    },
    data() {
        
        return {
            loading:false,
            ruleForm: {
            },
            rules: {
            },
            table_data:[],
            table_columns:[
                { prop: 'classname', label: '原名称',minWidth:10},
                { prop: 'newname', label: '新名称',minWidth:10,slotName:'preview-name'},
            ],
        }
    },
    methods:{
        //获取列表
        getList:function(){
            this.$api.post('/service/querylist', {}, r => {
                console.log(r)
                if(r.err_code=="0"){
                    this.table_data=r.data;
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
                    this.$api.post('/service/updateN',{list:this.table_data}, r => {
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
