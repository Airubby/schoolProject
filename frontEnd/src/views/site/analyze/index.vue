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
                    value-format="yyyy-MM-dd hh:mm:ss"
                    type="datetimerange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                    </el-date-picker>
                    <el-button type="primary" size="small" @click="getInfo">查询</el-button>
                </div>
                <div class="loncom_analyze_con" v-loading="loading">
                    <el-row :gutter="15">
                        <el-col :span="8">
                            <div class="loncom_analyze_allpower bg1C2443">
                                <h2 class="loncom_analyze_title">累计用电量</h2>
                                <div class="loncom_analyze_allpower_con">
                                    <span>{{allpower}}</span>kwh
                                </div>
                            </div>
                            <div class="loncom_analyze_top10 bg1C2443">
                                <h2 class="loncom_analyze_title">用电排行榜</h2>
                                <div class="loncom_analyze_top10_con">
                                    <el-search-table-pagination type="local" 
                                        :show-pagination="false"
                                        border :data="top_data" :columns="top_columns" stripe>   
                                        <el-table-column slot="prepend" type="index" label="排名" width="100"></el-table-column>
                                        <template slot-scope="scope" slot="preview-rate">
                                            <el-progress :text-inside="true" :stroke-width="14" :percentage="Number(Number(scope.row.rate).toFixed(2))"></el-progress>
                                        </template>
                                        <template slot-scope="scope" slot="preview-value">
                                            <div style="text-align:right">
                                                {{scope.row.value}}
                                            </div>
                                        </template>
                                    </el-search-table-pagination>
                                </div>
                            </div>
                        </el-col>
                        <el-col :span="16">
                            <div class="loncom_analyze_line bg1C2443">
                                <div class="loncom_analyze_line_top">
                                    <span>最大值<em>{{echart.max}}</em></span>
                                    <span>最小值<em>{{echart.min}}</em></span>
                                    <span>平均值<em>{{echart.average}}</em></span>
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
                                            <template slot-scope="scope" slot="preview-time">
                                                <span v-if="time=='day'">{{$tool.Format("hh:mm:ss",scope.row.TIME)}}</span>
                                                <span v-else>{{$tool.Format("yyyy-MM-dd hh:mm:ss",scope.row.TIME)}}</span>
                                            </template>
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
        this.search=[this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date(new Date().getTime()-3600*1000*24)),this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date())]
        this.getInfo();
    },
    mounted() {
        // let xData=["2018-10-11 09:00:11", "2018-10-11 09:03", "2018-10-11 09:13", "2018-10-11 09:14",
        // "2018-10-11 09:24","2018-10-11 09:34","2018-10-11 09:44","2018-10-11 09:54","2018-10-11 10:04","2018-10-11 10:14","2018-10-11 10:24"];
        // let yData=[320, 232, 301, 234, 390, 230, 310,18, 391, 234, 290, 343, 310];
        //     let myChart=this.$tool.lineChar('lineChar',xData,yData);
        //     window.onresize=function(){
        //         myChart.resize();
        //     }
        
    },
   data() {
       return {
           loading:false,
            time:'day',
            search:[],
            allpower:0,
            top_columns:[
              { prop: 'classname', label: '区域',minWidth:10},
              { prop: 'rate', label: '占比',slotName:'preview-rate',minWidth:15},
              { prop: 'value', label: '用电量',slotName:'preview-value',minWidth:10},
            ],
            top_data:[
            //     {'classname':'教室A','rate':70,'value':'345'},{'classname':'教室A','rate':30,'value':'345'},
            ],
            table_columns:[
              { prop: 'TIME', label: '时间',minWidth:20,slotName:'preview-time'},
              { prop: 'allpower', label: '总用电量',minWidth:10},
            //   { prop: 'louone', label: '教学楼',minWidth:10},
            //   { prop: 'loutwo', label: '实训楼',minWidth:10},
            ],
            table_data:[
                // {time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},{time:'2018-10-22',allpower:'24.6',louone:'25.2',loutwo:'25.3'},
           ],
           echart:{
               min:'',
               max:'',
               average:'',
           }
       }
    },
    methods:{
        getInfo:function(){
            this.getPowerTop();
            this.getPower();
            // this.getReport();
        },
        //top10及累计用电量
        getPowerTop:function(){
            this.loading=true;
            this.$api.post('/service/top', {startTime:this.search[0],endTime:this.search[1]}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    this.allpower=r.data.allPower;
                    this.top_data=r.data.top;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        //用电数据
        getPower:function(){
            this.loading=true;
            this.$api.post('/service/allInfo', {startTime:this.search[0],endTime:this.search[1]}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    let table_columns=[
                        { prop: 'TIME', label: '时间',minWidth:20,slotName:'preview-time'},
                        { prop: 'allpower', label: '总用电量',minWidth:10},
                    ];
                    this.table_columns=table_columns.concat(r.data.tableTitle);
                    this.table_data=r.data.tableBody;
                    let xData=[],yData=[];
                    let min=this.table_data[0].allpower,max=this.table_data[0].allpower,allpower=0;
                    for(let i=0;i<this.table_data.length;i++){
                        allpower+=parseFloat(this.table_data[i].allpower);
                        if (this.table_data[i].allpower < min){ 
                            min = this.table_data[i].allpower; 
                        }
                        if (this.table_data[i].allpower > max){ 
                            max = this.table_data[i].allpower; 
                        }
                        xData.push(this.$tool.Format("yyyy-MM-dd hh:mm:ss",this.table_data[i].TIME));
                        yData.push(this.table_data[i].allpower);
                    }
                    this.echart.min=min;
                    this.echart.max=max;
                    this.echart.average=(allpower/(this.table_data.length)).toFixed(2);
                    let myChart=this.$tool.lineChar('lineChar',xData,yData,this.time);
                    window.onresize=function(){
                        myChart.resize();
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        //获取echarts报表
    //     getReport:function(){
    //         this.loading=true;
    //         this.$api.post('/service/report', {startTime:this.search[0],endTime:this.search[1]}, r => {
    //             console.log(r)
    //             this.loading=false;
    //             if(r.err_code=="0"){
    //                 this.echart.min=r.data.min;
    //                 this.echart.max=r.data.max;
    //                 this.echart.average=r.data.average;
    //                 let xData=r.data.time;
    //                 let yData=r.data.value;
    //                 let myChart=this.$tool.lineChar('lineChar',xData,yData);
    //                 window.onresize=function(){
    //                     myChart.resize();
    //                 }
    //             }else{
    //                 this.$message.warning(r.err_msg);
    //             }
    //         })
    //     },
    },
    watch:{
        time:function(val){
            console.log(val)
            if(val=="day"){
                this.search=[this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date(new Date().getTime()-3600*1000*24)),this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date())];
            }else if(val=="week"){
                this.search=[this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date(new Date().getTime()-3600*1000*24*7)),this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date())];
            }else if(val=="month"){
                this.search=[this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date(new Date().getTime()-3600*1000*24*30)),this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date())];
            }
            this.getInfo();
        }
    },
    components:{},
}
</script>

<style scoped>
    @import "~@/assets/css/analyze.less";
</style>