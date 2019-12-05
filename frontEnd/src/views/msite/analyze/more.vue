<template>
    <div class="loncom_content">
        <div class="loncom_right_content custom scrollbar">
            <el-scrollbar style="height:100%">
                <div class="loncom_analyze_top bg1C2443">
                    <el-radio-group v-model="type" class="loncom_mr20" @change="change()" size="small">
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
                        :page-sizes="[5000]"
                        border :data="table_data" :columns="table_columns" stripe>   
                        <el-table-column slot="prepend" type="index" label="排名" width="150"></el-table-column>
                        <template slot-scope="scope" slot="preview-rate">
                            <el-progress :text-inside="true" :stroke-width="14" :percentage="Number(scope.row.rate)"></el-progress>
                        </template>
                        <template slot-scope="scope" slot="preview-value">
                            <div>
                                {{scope.row.value}}
                            </div>
                        </template>
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
              { prop: 'classname', label: '区域',minWidth:30},
              { prop: 'rate', label: '占比',slotName:'preview-rate',minWidth:10},
              { prop: 'value', label: '用电量',slotName:'preview-value',minWidth:10},
            ],
            table_data:[
                // {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
           ],
           
       }
    },
    methods:{
        change:function(){
            if(this.type=="day"){
                this.search=[this.$tool.Format("yyyy-MM-dd 00:00:00",new Date()),this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date())]
            }else{
                const theDate = new Date();
                let theMonth=theDate.getMonth();
                const start =this.$tool.Format("yyyy-MM-dd 00:00:00",new Date(theDate.getFullYear(),theMonth,1));
                let nextMonth=theMonth+1;
                const end=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date(new Date(theDate.getFullYear(),nextMonth,1)-1000*60*60*24));
                this.search=[start,end]
            }
            this.getPower();
        },
        //用电数据
        getPower:function(){
            if(!this.search||this.search.length==0){
                this.$message.warning("请选择查询时间段");
                return;
            }
            this.loading=true;
            this.$api.post('/service/moreInfo', {startTime:this.search[0],endTime:this.search[1]}, r => {
                
                this.loading=false;
                if(r.err_code=="0"){
                    this.table_data=r.data.top;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
    },
    watch:{
        time:function(val){
            
            this.type=val;
        }
    },
    components:{},
}
</script>

<style scoped>
    @import "~@/assets/css/analyze.less";
</style>