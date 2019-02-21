<template>
    <div class="loncom_content">
        <div class="loncom_index_top">
            <em class="loncom_index_top_line"></em>
            <em class="loncom_index_top_line1"></em>
            <div class="loncom_index_top_text"><span>元岗校区能效管理系统</span></div>
        </div>
        <div class="loncom_index_con">
            <div class="loncom_index_con_top">
                <div class="loncom_index_con_topcon loncom_fl">
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <div class="loncom_index_publicbox loncom_index_con_topbox">
                                <div class="loncom_fl">
                                    <h2>教室概况</h2>
                                    <span><em>180</em>间</span>
                                </div>
                                <div class="loncom_fr loncom_index_progress">
                                    <h2>空调开机率<em class="loncom_fr">40%</em></h2>
                                    <el-progress :text-inside="true" :stroke-width="15" :percentage="40" style="padding-top: 5px;"></el-progress>
                                </div>
                            </div>
                        </el-col>
                        <el-col :span="12">
                            <div class="loncom_index_publicbox loncom_index_con_topbox loncom_index_con_topboxr">
                                <div class="loncom_fl">
                                    <h2>宿舍概况</h2>
                                    <span><em>180</em>间</span>
                                </div>
                                <div class="loncom_fr loncom_index_progress">
                                    <h2>空调开机率<em class="loncom_fr">40%</em></h2>
                                    <el-progress :text-inside="true" :stroke-width="15" :percentage="40" style="padding-top: 5px;"></el-progress>
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
                                    <span><em>124563</em>平方米</span>
                                </div>
                            </div>
                        </el-col>
                        <el-col :span="12">
                            <div class="loncom_index_publicbox loncom_index_con_topbox">
                                <div class="loncom_fl">
                                    <h2>校园人口规模</h2>
                                    <span><em>563</em></span>
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
                                <el-col :span="12"><div class="colorfff" style="font-size:20px;">234234</div></el-col>
                            </el-row>
                        </div>
                        <div class="loncom_index_conbody_li">
                            <el-row>
                                <el-col :span="12">单位面积摊份</el-col>
                                <el-col :span="12"><div class="colorfff">12.4kw</div></el-col>
                            </el-row>
                        </div>
                        <div class="loncom_index_conbody_li">
                            <el-row>
                                <el-col :span="12">人均摊份</el-col>
                                <el-col :span="12"><div class="colorfff">1223kw</div></el-col>
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
                        <li><em>1</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>2</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>3</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>4</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>5</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>6</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>7</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>8</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>9</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                        <li><em>10</em><span>教室A</span><span class="loncom_fr">35265</span></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
  created () {
    
  },
  mounted() {
        let yData=[
                    {value:335, name:'教室'},
                    {value:310, name:'宿舍'},
                    {value:234, name:'办公室'},
                    {value:135, name:'其它'}
                ];
        let xData=["教室","宿舍","办公室","其它"];
        let myChart=this.$tool.annulus('myChart',xData,yData);
        window.onresize=function(){
            myChart.resize();
        }
    
  },
  data() {
    return {
       
    }
  },
    methods:{
        
    },
    watch: {
        
    },
    components:{}

 }
</script>


