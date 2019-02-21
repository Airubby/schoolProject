<template>
    <div class="loncom_content">
        <div class="loncom_right_content custom scrollbar">
            <el-scrollbar style="height:100%">
                <div class="loncom_analyze_top bg1C2443">
                    <el-radio-group v-model="time" class="loncom_mr20" size="small">
                    <el-radio-button label="day">日</el-radio-button>
                    <el-radio-button label="week">周</el-radio-button>
                    <el-radio-button label="month">月</el-radio-button>
                    </el-radio-group>
                    <el-date-picker
                    size="small"
                    class="loncom_mr20"
                    v-model="search"
                    type="datetimerange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                    </el-date-picker>
                    <el-button type="primary" size="small">查询</el-button>
                </div>
                <div class="loncom_analyze_con">
                    <el-row :gutter="15">
                        <el-col :span="8">
                            <div class="loncom_analyze_allpower bg1C2443">
                                <h2 class="loncom_analyze_title">累计用电量</h2>
                                <div class="loncom_analyze_allpower_con">
                                    <span>125635</span>kwh
                                </div>
                            </div>
                            <div class="loncom_analyze_top10 bg1C2443">
                                <h2 class="loncom_analyze_title">用电排行榜</h2>
                                <div class="loncom_analyze_top10_con">
                                    <el-search-table-pagination type="local" 
                                        :show-pagination="false"
                                        border :data="top_data" :columns="top_columns" stripe>   
                                        <el-table-column slot="prepend" type="index" label="排名" width="100"></el-table-column>
                                        <template slot-scope="scope" slot="preview-proportion">
                                            <el-progress :text-inside="true" :stroke-width="14" :percentage="scope.row.proportion"></el-progress>
                                        </template>
                                        <template slot-scope="scope" slot="preview-power">
                                            <div style="text-align:right">
                                                {{scope.row.power}}
                                            </div>
                                        </template>
                                    </el-search-table-pagination>
                                </div>
                            </div>
                        </el-col>
                        <el-col :span="16">
                            <div class="loncom_analyze_line bg1C2443">
                                <div class="loncom_analyze_line_top">
                                    <span>最大值<em>4320</em></span>
                                    <span>最小值<em>4320</em></span>
                                    <span>平均值<em>4320</em></span>
                                </div>
                                <div class="loncom_analyze_line_con" id="lineChar" style="height:calc(100% - 50px)"></div>
                            </div>
                            <div class="loncom_analyze_table bg1C2443">
                                <h2 class="loncom_analyze_title">
                                    用电数据
                                    <el-button type="primary" size="small" class="loncom_fr" style="margin-top:12px;">导出</el-button>    
                                </h2>
                                <div class="loncom_analyze_table_con">
                                    <el-scrollbar style="height:100%;">
                                        <el-search-table-pagination type="local" 
                                            border :data="table_data" :columns="table_columns" stripe>   
                                        </el-search-table-pagination>
                                    </el-scrollbar>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>


<script>
export default {
    
  created () {
      
  },
  mounted() {
    let xData=["2018-10-11 09:00:11", "2018-10-11 09:03", "2018-10-11 09:13", "2018-10-11 09:14",
     "2018-10-11 09:24","2018-10-11 09:34","2018-10-11 09:44","2018-10-11 09:54","2018-10-11 10:04","2018-10-11 10:14","2018-10-11 10:24"];
    let yData=[320, 232, 301, 234, 390, 230, 310,18, 391, 234, 290, 343, 310];
        let myChart=this.$tool.lineChar('lineChar',xData,yData);
        window.onresize=function(){
            myChart.resize();
        }
    
  },
   data() {
       return {
            time:'day',
            search:['2017-10-12 12:12:12','2019-10-12 12:12:12'],
            top_columns:[
              { prop: 'area', label: '区域',minWidth:10},
              { prop: 'proportion', label: '占比',slotName:'preview-proportion',minWidth:15},
              { prop: 'power', label: '用电量',slotName:'preview-power',minWidth:10},
            ],
            top_data:[{'area':'教室A','proportion':70,'power':'345'},{'area':'教室A','proportion':30,'power':'345'},
            {'area':'教室A','proportion':70,'power':'345'},{'area':'教室A','proportion':50,'power':'345'},
            {'area':'教室A','proportion':70,'power':'345'},{'area':'教室A','proportion':60,'power':'345'},
            {'area':'教室A','proportion':70,'power':'345'},{'area':'教室A','proportion':80,'power':'345'},
            {'area':'教室A','proportion':70,'power':'345'},{'area':'教室A','proportion':70,'power':'345'}],
            table_columns:[
              { prop: 'time', label: '时间',minWidth:10},
              { prop: 'allpower', label: '总用电量',minWidth:15},
              { prop: 'louone', label: '教学楼',minWidth:10},
              { prop: 'loutwo', label: '实训楼',minWidth:10},
            ],
            table_data:[{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
            {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
            {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
            {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
            {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
            {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
            {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
            {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
            {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'}],
       }
    },
    methods:{
        
    },
    watch:{
    },
    components:{},
}
</script>

<style scoped>
    @import "~@/assets/css/analyze.less";
</style>