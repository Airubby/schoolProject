<template>
    <div class="loncom_content">
        <div class="loncom_right_content custom scrollbar">
            <el-scrollbar style="height:100%">
                <div class="loncom_analyze_top bg1C2443">
                    <el-radio-group v-model="type" class="loncom_mr20" size="small">
                    <el-radio-button label="day">日显示</el-radio-button>
                    <el-radio-button label="month">月显示</el-radio-button>
                    </el-radio-group>
                    <el-date-picker
                    size="small"
                    class="loncom_mr20"
                    v-model="search"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    type="datetimerange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                    </el-date-picker>
                    <el-button type="primary" size="small" @click="getPower">查询</el-button>
                </div>
                <div class="loncom_analyze_con" v-loading="loading">
                    <el-search-table-pagination type="local" 
                        v-scrollBar="'table'"
                        :showPagination="false"
                        border :data="table_data" :columns="table_columns" stripe>   
                        
                    </el-search-table-pagination>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>


<script>
export default {
    
    created () {
        this.search=[this.$tool.Format("yyyy-MM-dd 00:00:00",new Date()),this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date())]
        this.getPower();
    },
    mounted() {
        
    },
   data() {
       return {
           loading:false,
            type:'day',
            search:[],
            table_columns:[
              { prop: 'name', label: '名称',minWidth:"80px"},
            ],
            table_data:[
                // {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
           ],
           
       }
    },
    methods:{
        //用电数据
        getPower:function(){
            if(!this.search||this.search.length==0){
                this.$message.warning("请选择查询时间段");
                return;
            }
            this.loading=true;
            this.$api.post('/service/moreInfo', {startTime:this.search[0],endTime:this.search[1],type:this.type}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    let table_columns=[
                        { prop: 'name', label: '名称',minWidth:"80px"},
                    ];
                    this.table_columns=table_columns.concat(r.data.title);
                    this.table_data=r.data.body;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
    },
    watch:{
        time:function(val){
            console.log(val)
            this.type=val;
        }
    },
    components:{},
}
</script>

<style scoped>
    @import "~@/assets/css/analyze.less";
</style>