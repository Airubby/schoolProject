<template>
    <el-dialog title="批量关空调" :visible.sync="dialogInfo.visible" width="600px" v-dialogDrag class="custom">
        <el-scrollbar style="height:400px;" class="loncom_scrollbar">
            <div v-loading="loading" style="overflow:hidden;padding:20px;">
                <div class="loncom_mb10" style="text-align:right;">
                    <el-button type="primary" size="small" @click="morecloseAir()">批量关闭</el-button>
                </div>
                <el-search-table-pagination type="local" 
                    border 
                    :data="dialogInfo.table_data" 
                    :show-select-all="true"
                    select-id="code"
                    :columns="table_columns" stripe ref="thisRef">   
                    <el-table-column slot="prepend" type="selection"></el-table-column>
                    <template slot-scope="scope" slot="preview-handle">
                        <a @click="closeAir(scope.row)">关闭空调</a>
                    </template>
                </el-search-table-pagination>
            </div>
        </el-scrollbar>
    </el-dialog>
</template>

<script>
import dialogBtnInfo from '@/components/dialogBtnInfo.vue'
export default {
    created () {
        
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
            table_columns:[
                { prop: 'classname', label: '房间名称',minWidth:10},
                { prop: 'handle', label: '操作',width:100,slotName:'preview-handle'},
            ],
        }
    },
    methods:{
        closeAir:function(item){
            let obj={
                closecmd:item.airClosecmd,
                serviceid:item.serviceid,
                devid:item.aircontrol_devid,
                stateid:item.aircontrol_pointid
            };
            this.loading=true;
            this.$api.post('/service/switchOrder', obj, r => {
                this.loading=false;
                if(r.err_code=="0"){
                    this.$message.success(r.err_msg);
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        morecloseAir:function(){
            let data=this.$refs.thisRef.allSelection.length>0?this.$refs.thisRef.allSelection:this.dialogInfo.table_data;
            this.$api.post('/service/moreswitchOrder', {classnode:data}, r => {
                this.loading=false;
                if(r.err_code=="0"){
                    this.$message.success(r.err_msg);
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },

    },
    watch:{
        
    },
    components:{dialogBtnInfo},
    props:["dialogInfo"]
}
</script>
<style scoped>
    
</style>
