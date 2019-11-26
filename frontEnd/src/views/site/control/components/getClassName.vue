<template>
    <el-dialog title="选择需修改的房间" :visible.sync="dialogInfo.visible" width="980px" v-dialogDrag class="custom" :append-to-body="true">
        <el-scrollbar style="height:400px;" class="loncom_scrollbar">
            <div class="loncom_public_table" v-loading="loading">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-search-table-pagination type="local" 
                        border 
                        :data="table_data" 
                        :show-select-all="true"
                        select-id="code"
                        ref="thisRef"
                        :columns="table_columns" stripe>   
                        <el-table-column slot="prepend" type="selection"></el-table-column>
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
                { prop: 'classname', label: '名称',minWidth:10},
                { prop: 'code', label: '编码',minWidth:10},
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
            this.$emit("backInfo",this.$refs.thisRef.allSelection);
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
