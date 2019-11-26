<template>
    <div class="loncom_content">
        <home-top></home-top>
        <div class="loncom_index_con" v-loading="loading">
            <div class="loncom_index_con_top">
                <div class="loncom_index_con_topconl">
                    <el-row :gutter="30" class="h100 classoffice">
                        <el-col :span="12" class="h100" style="position:relative;">
                            <div>
                                <h2>本月用电量</h2>
                                <div class="number classnumber">
                                    <span class="color">{{overview.allmvalue}}</span>KWh
                                </div>
                            </div>
                            <div class="classroom" style="position:absolute;bottom:40px;width:100%;">
                                <h2>教室占比</h2>
                                <div class="number">
                                    <span>{{classRate}}</span>%
                                    <el-progress :percentage="classRate" color="#4A78FF" :stroke-width="10" :show-text="false" style="width:70%;"></el-progress>
                                </div>
                            </div>
                        </el-col>
                        <el-col :span="12" class="h100" style="position:relative;">
                            <div>
                                <h2>今日用电量</h2>
                                <div class="number officenumber">
                                    <span class="color">{{overview.allvalue}}</span>KWh
                                </div>
                            </div>
                            <div class="officeroom classroom" style="position:absolute;bottom:40px;width:100%;">
                                <h2>办公室占比</h2>
                                <div class="number">
                                    <span class="color">{{officeRate}}</span>%
                                    <el-progress :percentage="officeRate" color="#DDAF34" :stroke-width="10" :show-text="false" style="width:70%;"></el-progress>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
                <div class="loncom_index_con_topcen">
                    <el-row :gutter="30" class="h100">
                        <el-col :span="12" class="h100"><div id="openJ" style="width:100%;height:100%;"></div></el-col>
                        <el-col :span="12" class="h100"><div id="openD" style="width:100%;height:100%;"></div></el-col>
                    </el-row>
                </div>
                <div class="loncom_index_con_topconr">
                    <div class="loncom_index_con_topconr_title">
                        <span>年度用电趋势</span>
                        <div class="loncom_fr">
                            <span class="btn" :class="{active:topbarBtn==0}" @click="checkTopbar('')">本年度</span>
                            <span class="btn" :class="{active:topbarBtn==1}" @click="checkTopbar(1)">上年度</span>
                        </div>
                    </div>
                    <div id="topbar" style="width:100%;height:calc(100% - 22px)"></div>
                </div>
            </div>
            <div class="loncom_index_conbody">
                <div class="loncom_index_conbody_center" id="centermap">
                    <img src="images/home.png" usemap="#map" id="mapimg">
                    <map name="map" id="map">
                        <el-popover
                            class="mapshow" coords="295,225,30"
                            placement="top"
                            width="260"
                            trigger="hover">
                            <area shape="circle" slot="reference" coords="295,225,30" class="maparea" @click="enterInfo('1')"></area>
                            <div class="mapcon loncom_index_publicbox">
                                <h2>1#楼</h2>
                                <div class="info">
                                    <p>
                                        <span class="txt">本月用电</span>
                                        <span class="number">
                                            <em>{{building_one.monthsum}}</em>KWh
                                        </span>
                                    </p>
                                    <p>
                                        <span class="txt">今日用电</span>
                                        <span class="number">
                                            <em>{{building_one.psum}}</em>KWh
                                        </span>
                                    </p>
                                </div>
                                <div class="echarts" id="myChart1"></div>
                            </div>
                        </el-popover>
                        <el-popover
                            class="mapshow" coords="265,455,30"
                            placement="top"
                            width="260"
                            trigger="hover">
                            <area shape="circle" slot="reference" coords="265,455,30" class="maparea" @click="enterInfo('2')"></area>
                            <div class="mapcon loncom_index_publicbox">
                                <h2>2#楼</h2>
                                <div class="info">
                                    <p>
                                        <span class="txt">本月用电</span>
                                        <span class="number">
                                            <em>{{building_two.monthsum}}</em>KWh
                                        </span>
                                    </p>
                                    <p>
                                        <span class="txt">今日用电</span>
                                        <span class="number">
                                            <em>{{building_two.psum}}</em>KWh
                                        </span>
                                    </p>
                                </div>
                                <div class="echarts" id="myChart2"></div>
                            </div>
                        </el-popover>
                        <el-popover
                            class="mapshow" coords="140,158,30"
                            placement="top"
                            width="260"
                            trigger="hover">
                            <area shape="circle" slot="reference" coords="425,138,30" class="maparea" @click="enterInfo('4')"></area>
                            <div class="mapcon loncom_index_publicbox">
                                <h2>4#楼</h2>
                                <div class="info">
                                    <p>
                                        <span class="txt">本月用电</span>
                                        <span class="number">
                                            <em>{{building_four.monthsum}}</em>KWh
                                        </span>
                                    </p>
                                    <p>
                                        <span class="txt">今日用电</span>
                                        <span class="number">
                                            <em>{{building_four.psum}}</em>KWh
                                        </span>
                                    </p>
                                </div>
                                <div class="echarts" id="myChart4"></div>
                            </div>
                        </el-popover>
                        <!-- <el-popover
                            class="mapshow" coords="425,138,30"
                            placement="top"
                            width="260"
                            trigger="hover">
                            <area shape="circle" slot="reference" coords="425,138,30" class="maparea"></area>
                            <div class="mapcon loncom_index_publicbox">
                                <h2>4#楼</h2>
                                <div class="info">
                                    <p>
                                        <span class="txt">本月用电</span>
                                        <span class="number">
                                            <em>{{building_four.monthsum}}</em>KWh
                                        </span>
                                    </p>
                                    <p>
                                        <span class="txt">今日用电</span>
                                        <span class="number">
                                            <em>{{building_four.psum}}</em>KWh
                                        </span>
                                    </p>
                                </div>
                                <div class="echarts" id="myChart4"></div>
                            </div>
                        </el-popover>
                        <div class="mapshow" coords="291,85,30">
                            <area shape="circle" slot="reference" coords="291,85,30" class="maparea"></area>
                        </div>
                        <div class="mapshow" coords="415,55,30">
                            <area shape="circle" slot="reference" coords="415,55,30" class="maparea"></area>
                        </div>
                        <div class="mapshow" coords="65,340,30">
                            <area shape="circle" slot="reference" coords="65,340,30" class="maparea"></area>
                        </div> -->
                    </map>
                    <div class="center_enter">
                        <router-link to="/site" class="center_route" v-if="navList.length>0">进入管理页</router-link>
                    </div>
                </div>
                <div class="loncom_index_conbody_right">
                    <div style="height:40px;line-height:40px;text-align:right;"><router-link to="/slide">更多排名</router-link></div>
                    <div class="loncom_index_conbody_rightbox loncom_index_publicbox">
                        <h2 class="loncom_index_top10title">教室能效排名TOP10</h2>
                        <div class="rightbox-scrollbar">
                            <el-scrollbar class="h100">
                                <ul class="loncom_index_top10">
                                    <li v-for="(item,index) in topData.classtopy" v-if="index<10"><em>{{index+1}}</em><span>{{item.classname}}</span><span class="loncom_fr">{{item.value}}</span></li>
                                </ul>
                            </el-scrollbar>
                        </div>
                    </div>
                    <div class="loncom_index_conbody_rightbox loncom_index_publicbox">
                        <h2 class="loncom_index_top10title">办公室能效排名TOP10</h2>
                        <div class="rightbox-scrollbar">
                            <el-scrollbar class="h100">
                                <ul class="loncom_index_top10">
                                    <li v-for="(item,index) in topData.officetopy" v-if="index<10"><em>{{index+1}}</em><span>{{item.classname}}</span><span class="loncom_fr">{{item.value}}</span></li>
                                </ul>
                            </el-scrollbar>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import homeTop from '@/components/homeTop.vue'
export default {
    created () {
        this.startTime=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date());
        this.endTime=this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date());
        this.getPowerTop();
        this.getRate();  //本月用电量，今日用电
        
        
    },
    computed:{
        navList:{
            get(){
                return this.$store.getters.navList;
            }
        },
        classRate:{
            get(){
                if(Number(this.overview.allmvalue)>0){
                    return Number((this.overview.classmvalue*100/this.overview.allmvalue).toFixed(2));
                }else{
                    return 0;
                }
            }
        },
        officeRate:{
            get(){
                if(Number(this.overview.allvalue)>0){
                    return Number((this.overview.officevalue*100/this.overview.allvalue).toFixed(2));
                }else{
                    return 0;
                }
            }
        }
    },
    mounted() {
        this.getRateInfo();
        this.getYearInfo();
        this.getOneInfo();
        this.getTwoInfo();
        this.getFourInfo();

        document.querySelector('#mapimg').addEventListener('load',  () =>{
            this.adjust();
        })
        let timeout = null;//onresize触发次数过多，设置定时器
        window.onresize = ()=> {  
            clearTimeout(timeout);  
            timeout = setTimeout( () =>{ 
                this.adjust(); 
            }, 100);//页面大小变化，重新加载页面以刷新MAP  
        }  

        // let yData2=[
        //             {value:335, name:'教室'},
        //             {value:234, name:'办公室'},
        //             {value:135, name:'其它'}
        //         ];
        // let xData2=["教室","办公室","其它"];
        // this.$tool.annulus('myChart1',xData2,yData2);
        // this.$tool.annulus('myChart2',xData2,yData2);
        // this.$tool.annulus('myChart4',xData2,yData2);
    },
    data() {
        return {
            loading:false,
            startTime:"",
            endTime:"",
            topbarBtn:0,  //切换年度趋势
            overview:{
                allmvalue:0, //本月总用电
                classmvalue:0,  //本月教室总用电
                officemvalue:0,  //本月办公室总用电
                allvalue:0,  //今日总用电
                classvalue:0,  //教室用电
                officevalue:0, //办公室用电
            },
            topData:{
                classtopy:[],
                officetopy:[],
            },
            xData:["教室","办公室"],
            building_one:{
                psum:"",  //今日用电
                monthsum:"",  //本月用电
            },
            building_two:{
                psum:"",  //今日用电
                monthsum:"",  //本月用电
            },
            building_four:{
                psum:"",  //今日用电
                monthsum:"",  //本月用电
            },

        }
    },
    methods:{
        checkTopbar:function(type){
            this.topbarBtn=type;
            this.getYearInfo();
        },
        getYearInfo:function(){
             this.$api.post('/service/queryYear', {type:this.topbarBtn}, r => {
                let xData=[];
                let yData=[];
                let color="#4C78FF";
                if(r.err_code=="0"){
                    if(r.data.length>0){
                        for(let i=0;i<r.data.length;i++){
                            let month=this.$tool.Format("MM",r.data[i].time);
                            xData.push(month+"月");
                            yData.push(r.data[i].value);
                        }
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
                this.$tool.homeChar("topbar",xData,yData,color); 
            })
        },
        //总功率，今日用电
        getRate(){
            this.$api.post('/service/rate', {}, r => {
                if(r.err_code=="0"){
                    this.overview=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        //开灯开机率
        getRateInfo:function(){
            this.$api.post('/service/airlightRate', {}, r => {
                if(r.err_code=="0"){
                    let air=r.data.classair+r.data.officeair;
                    let light=r.data.classlight+r.data.officelight;
                    let airData=[
                        {value:air,name:"开机"},
                        {value:r.data.allnumber-air,name:"未开机"}
                    ];
                    let lightData=[
                        {value:light,name:"开机"},
                        {value:r.data.allnumber-light,name:"未开机"}
                    ];
                    let airRate=(air*100/r.data.allnumber).toFixed(2);
                    let lightRate=(light*100/r.data.allnumber).toFixed(2);
                    var aircolor=["#4A78FF","#1C3271"]
                    var lightcolor=["#DDAF34","#554415"];
                    this.$tool.moreannulus('openJ',"开机率",airData,airRate,aircolor);
                    this.$tool.moreannulus('openD',"开灯率",lightData,lightRate,lightcolor);
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        //1栋楼的信息
        getOneInfo:function(){
            this.$api.post('/service/queryGroup', {group:'1'}, r => {
                if(r.err_code=="0"){
                    let yData=[
                        {value:r.data.classvalue, name:'教室'},
                        {value:r.data.officevalue, name:'办公室'},
                    ];
                    this.$tool.annulus('myChart1',this.xData,yData);
                    this.building_one=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        //1栋楼的信息
        getTwoInfo:function(){
            this.$api.post('/service/queryGroup', {group:'2'}, r => {
                if(r.err_code=="0"){
                    let yData=[
                        {value:r.data.classvalue, name:'教室'},
                        {value:r.data.officevalue, name:'办公室'},
                    ];
                    this.$tool.annulus('myChart2',this.xData,yData);
                    this.building_two=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
         //1栋楼的信息
        getFourInfo:function(){
            this.$api.post('/service/queryGroup', {group:'4'}, r => {
                if(r.err_code=="0"){
                    let yData=[
                        {value:r.data.classvalue, name:'教室'},
                        {value:r.data.officevalue, name:'办公室'},
                    ];
                    this.$tool.annulus('myChart4',this.xData,yData);
                    this.building_four=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        getPowerTop:function(){
            this.$api.post('/service/topInfo', {startTime:this.startTime,endTime:this.endTime}, r => {
                if(r.err_code=="0"){
                    this.topData=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        adjust:function(){
            let maplist=document.getElementById("map").childNodes;
            let mapimg=document.getElementById("mapimg");
            let centermap=document.getElementById("centermap");
            let domWidth=centermap.offsetWidth;
            let domHeight=centermap.offsetHeight;
            let mapWidth=mapimg.offsetWidth;
            let mapHeight=mapimg.offsetHeight;
            let img=new Image();
            img.src=mapimg.getAttribute("src");
            let width=img.width;
            let height=img.height;

            
            let xdom=domWidth/mapWidth;
            let ydom=domHeight/mapHeight;
            let rate=mapHeight/height;  ////宽度有多的 按高度缩放比例
            let number=domWidth-mapWidth;
            if(xdom<ydom){   //高度有多的 
                rate=mapWidth/width;  ////高度有多的 按宽度缩放比例
                number=domHeight-mapHeight;
            }
            console.log("imgk:"+width+";imgh:"+height+";domk:"+domWidth+";domh:"+domHeight+";mapk:"+mapWidth+";maph:"+mapHeight+";rate:"+rate)
            if(width<=domWidth&&height<=domHeight){
                for(let i=0;i<maplist.length;i++){
                    let oldCoords = maplist[i].getAttribute("coords"); //定义一个初始化的coords 改变浏览器用这个值去计算
                    let newcoords = this.adjustPosition(oldCoords,rate);  
                    maplist[i].querySelector(".maparea").setAttribute("coords", newcoords.toString());  
                    maplist[i].style.left=(domWidth-width)/2+parseFloat(newcoords[0])+"px";
                    maplist[i].style.top=(domHeight-height)/2+parseFloat(newcoords[1])+"px";
                    
                }
            }else{
                for(let i=0;i<maplist.length;i++){
                    let oldCoords = maplist[i].getAttribute("coords"); //定义一个初始化的coords 改变浏览器用这个值去计算
                    let newcoords = this.adjustPosition(oldCoords,rate);  
                    maplist[i].querySelector(".maparea").setAttribute("coords", newcoords.toString());  
                    if(xdom>ydom){  //宽度有多的
                        maplist[i].style.left=number/2+parseFloat(newcoords[0])+"px";
                        maplist[i].style.top=newcoords[1]+"px";
                    }else{  //高度有多的
                        maplist[i].style.left=newcoords[0]+"px";
                        maplist[i].style.top=number/2+parseFloat(newcoords[1])+"px";
                    }
                    
                }
            }
        },
        adjustPosition:function(position,rate){
            let each = position.split(",");  
            for (let i = 0; i < each.length; i++) { 
                each[i] = Math.round(parseFloat(each[i]) * rate).toString();//坐标  
            }  
            return each;  
        },
        enterInfo:function(type){
            this.$router.push({path:'/view',query:{
                param:JSON.stringify({"info":type})
            }});
        },
        
        enterSlide:function(){
            this.$router.push({path:'/slide'});
        },
        
    },
    watch: {
        
    },
    components:{homeTop}

 }
</script>


