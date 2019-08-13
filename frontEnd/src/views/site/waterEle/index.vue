<template>
    <div class="loncom_content">
        <div class="loncom_right_content">
            <div class="bg1C2443 loncom_pd20 loncom_content">
                <div class="loncom_mb20 custom">
                    <el-input
                        style="width:300px;"
                        size="small"
                        class="loncom_mr10"
                        placeholder="请输入房间名称"
                        prefix-icon="el-icon-search"
                        v-model="initParams.ROOMNAME">
                    </el-input>
                    <el-button type="primary" size="small" @click="searchFN">搜索</el-button>
                </div>
                <div class="scrollbar custom" style='height:calc(100% - 50px)' v-loading="loading">
                    <el-scrollbar style='height:100%' class="loncom_control_con">
                        <el-search-table-pagination type="local" 
                            border :data="table_data" 
                            :columns="table_columns" stripe
                            :params="initParams"
                            ref="thisRef">   
                            <template slot-scope="scope" slot="preview-time">
                                <span v-if="time=='day'">{{$tool.Format("hh:mm:ss",scope.row.TIME)}}</span>
                                <span v-else>{{$tool.Format("yyyy-MM-dd hh:mm:ss",scope.row.TIME)}}</span>
                            </template>
                        </el-search-table-pagination>
                    </el-scrollbar>
                </div>
            </div>                                    
        </div>
    </div>
</template>


<script>
export default {
    
    created () {
        this.getList();
    },
    mounted() {
        
    },
   data() {
       return {
            loading:false,
            initParams:{
                ROOMNAME:'',
            },
            table_data:[],
            table_columns:[
                { prop: 'BUILDNAME', label: '楼栋名称',minWidth:20},
                { prop: 'LEVELNAME', label: '楼层名称',minWidth:20},
                { prop: 'ROOMNAME', label: '房间名称',minWidth:20},
                { prop: 'USEELEC', label: '用电量',minWidth:15},
            ]
       }
    },
    methods:{
        searchFN:function(){
            this.$refs.thisRef.searchHandler(true);
        },
        getList:function(){
            this.loading=true;
            this.$api.post('/waterele/query', {}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                   this.table_data=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        
    },
    watch:{
        
    },
    components:{},
}
</script>

<style scoped>
</style>