<template>
    <div class="loncom_content">
        <div class="loncom_index_top">
            <em class="loncom_index_top_line"></em>
            <em class="loncom_index_top_line1"></em>
            <div class="loncom_index_top_text"><span>元岗校区能效管理系统</span></div>
        </div>
        <div class="loncom_index_con" v-loading="loading">
            <div class="loncom_index_con_top">
                <div class="loncom_index_con_topcon loncom_fl">
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <div class="loncom_index_publicbox loncom_index_con_topbox">
                                <div class="loncom_fl">
                                    <h2>教室概况</h2>
                                    <span><em>{{overview.classroomcount}}</em>间</span>
                                </div>
                                <div class="loncom_fr loncom_index_progress">
                                    <h2>空调开机率<em class="loncom_fr">{{overview.classair}}%</em></h2>
                                    <el-progress :text-inside="true" :stroke-width="15" :percentage="overview.classair" style="padding-top: 5px;"></el-progress>
                                </div>
                            </div>
                        </el-col>
                        <el-col :span="12">
                            <div class="loncom_index_publicbox loncom_index_con_topbox loncom_index_con_topboxr">
                                <div class="loncom_fl">
                                    <h2>办公室概况</h2>
                                    <span><em>{{overview.dormcount}}</em>间</span>
                                </div>
                                <div class="loncom_fr loncom_index_progress">
                                    <h2>空调开机率<em class="loncom_fr">{{overview.dormair}}%</em></h2>
                                    <el-progress :text-inside="true" :stroke-width="15" :percentage="Number(Number(overview.dormair).toFixed(2))" style="padding-top: 5px;"></el-progress>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
                <div class="loncom_index_con_topcon loncom_fr">
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <div class="loncom_index_publicbox loncom_index_con_topbox loncom_index_con_topboxl">
                                <div class="loncom_fl">
                                    <h2>校园建筑面积</h2>
                                    <span><em>{{overview.acreage}}</em>平方米</span>
                                </div>
                            </div>
                        </el-col>
                        <el-col :span="12">
                            <div class="loncom_index_publicbox loncom_index_con_topbox">
                                <div class="loncom_fl">
                                    <h2>校园人口规模</h2>
                                    <span><em>{{overview.countnumber}}</em></span>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </div>
            <div class="loncom_index_conbody">
                <div class="loncom_index_conbody_left loncom_index_publicbox">
                    <div>
                        <div class="loncom_index_conbody_li">
                            <el-row>
                                <el-col :span="12">总能耗</el-col>
                                <el-col :span="12"><div class="colorfff" style="font-size:20px;">{{echart.allPower}}</div></el-col>
                            </el-row>
                        </div>
                        <div class="loncom_index_conbody_li">
                            <el-row>
                                <el-col :span="12">单位面积摊份</el-col>
                                <el-col :span="12"><div class="colorfff">{{echart.areaPower}} kw</div></el-col>
                            </el-row>
                        </div>
                        <div class="loncom_index_conbody_li">
                            <el-row>
                                <el-col :span="12">人均摊份</el-col>
                                <el-col :span="12"><div class="colorfff">{{echart.peoplePower}} kw</div></el-col>
                            </el-row>
                        </div>
                    </div>
                    <div id="myChart" :style="{width: '100%', height: '320px'}"></div>
                </div>
                <div class="loncom_index_conbody_center">
                    <router-link to="/site"><img src="~@/assets/images/home.png"></router-link>
                </div>
                <div class="loncom_index_conbody_right loncom_index_publicbox">
                    <h2 class="loncom_index_top10title">教室能效排名TOP10</h2>
                    <ul class="loncom_index_top10">
                        <li v-for="(item,index) in top_data"><em>{{index+1}}</em><span>{{item.classname}}</span><span class="loncom_fr">{{item.value}}</span></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
  created () {
      this.getOverview();
      this.getPowerTop();
      this.getRate();
  },
  mounted() {
        // let yData=[
        //             {value:335, name:'教室'},
        //             {value:310, name:'宿舍'},
        //             {value:234, name:'办公室'},
        //             {value:135, name:'其它'}
        //         ];
        // let xData=["教室","宿舍","办公室","其它"];
        // let myChart=this.$tool.annulus('myChart',xData,yData);
        // window.onresize=function(){
        //     myChart.resize();
        // }
    
  },
  data() {
    return {
        loading:false,
        overview:{
            acreage:0,
            classair:0,
            classroomcount:0,
            countnumber:0,
            dormair:0,
            dormcount:0,
        },
        top_data:[],
        echart:{
            allPower:'',
            areaPower:'',
            peoplePower:'',
            name:[],
            value:[],
        }
    }
  },
    methods:{
        getPowerTop:function(){
            this.loading=true;
            let startTime=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date());
            let endTime=this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date());
            this.$api.post('/service/top', {startTime:startTime,endTime:endTime,type:"classroom"}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    this.top_data=r.data.top;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        getOverview:function(){
            this.loading=true;
            this.$api.get('/service/queryOverview', {}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                   this.overview=r.data
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        getRate:function(){
            this.loading=true;
            this.$api.get('/service/rate', {}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    this.echart.allPower=r.data.allPower;
                    this.echart.areaPower=r.data.areaPower;
                    this.echart.peoplePower=r.data.peoplePower;
                    let yData=r.data.value;
                    let xData=r.data.name;
                    let myChart=this.$tool.annulus('myChart',xData,yData);
                    window.onresize=function(){
                        myChart.resize();
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
    },
    watch: {
        
    },
    components:{}

 }
</script>


