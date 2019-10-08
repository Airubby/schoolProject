<template>
    <div class="loncom_content" v-loading="loading">
        <div class="loncom_slidebox">
            <div class="loncom_index_publicbox" id="threetop1">
            </div>
            <div class="loncom_index_publicbox" id="threetop2">
            </div>
            <div class="loncom_index_publicbox" id="threemyChart">
            </div>
            <div class="loncom_index_publicbox" id="threemyChart1">
            </div>
        </div>
        <div class="loncom_slideline loncom_index_publicbox" id="threeline"></div>
    </div>
</template>

<script>

export default {
    created () {
        
        this.getInfo();
        
    },
    mounted() {
        
    },
    data() {
        return {
            loading:false,
            title:[],
            timer:'',
        }
    },
    destroyed() {
        clearTimeout(this.timer);
        this.timer="";
    },
    methods:{
        async getInfo(){
            let _this=this;
            this.loading=true;
            await this.getMoreDayInfo();
            await this.getTitle();
            await this.getTypeInfo();
            this.loading=false;
            this.timer=setTimeout(function(){
                _this.getInfo();
            },15000)
        },
        getTitle:function(){
            return new Promise ((resolve, reject) => {
                this.$api.post('/service/tableTitle', {}, r => {
                    console.log(r)
                    if(r.err_code=="0"){
                        this.title=r.data;
                    }else{
                        this.$message.warning(r.err_msg);
                    }
                    resolve();
                })
            })
        },
        getTypeInfo:function(){
            return new Promise ((resolve, reject) => {
                // let startTime=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date(new Date().getTime()-3600*1000*24));
                let startTime=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date());
                let endTime=this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date());
                this.$api.post('/service/typeInfo', {startTime:startTime,endTime:endTime}, r => {
                    console.log(r)
                    if(r.err_code=="0"){
                        let classlist=r.data.classlist[0];
                        let officelist=r.data.officelist[0];
                        let counttype=r.data.counttype[0];
                        let linelist=r.data.linelist;
                        let all=counttype!=""&&counttype!=null&&counttype["ALLVALUE"]?counttype["ALLVALUE"]:0;
                        let data=[],xData=[],yData=[];
                        let data1=[],xData1=[],yData1=[];
                        for(let i=0;i<this.title.length;i++){
                            for(let item in counttype){
                                if(this.title[i].prop==item){
                                    let value=counttype[item]?counttype[item]:0;
                                    data.push({value:value,name:this.title[i].label})
                                    xData.push(this.title[i].label)
                                    let rate=value!=0?parseFloat(value*100/all).toFixed(2)+"%":'0%'
                                    yData.push(rate);
                                }
                            }
                        }
                        let cvalue=classlist!=""&&classlist!=null&&classlist.ALLVALUE?classlist.ALLVALUE:0;
                        let ovalue=officelist!=""&&officelist!=null&&officelist.ALLVALUE?officelist.ALLVALUE:0;
                        data1=[{value:cvalue,name:'教室'},{value:ovalue,name:'办公室'}];
                        xData1=['教室','办公室'];
                        let crate=cvalue!=0?parseFloat(cvalue*100/all).toFixed(2)+"%":'0%';
                        let orate=ovalue!=0?parseFloat(ovalue*100/all).toFixed(2)+"%":'0%';
                        yData1=[crate,orate]
                        let myChart=this.$tool.allannulus('threemyChart',"教室区域能耗",data,xData,yData,all);
                        let myChart1=this.$tool.allannulus('threemyChart1',"教室类型能耗",data1,xData1,yData1,all);

                        let xData2=[],yData2=[];
                        for(let i=0;i<linelist.length;i++){
                            xData2.push(this.$tool.Format("yyyy-MM-dd hh:mm:ss",linelist[i].TIME));
                            yData2.push(linelist[i].ALLVALUE);
                        }
                        let linechar=this.$tool.lineChar("threeline",xData2,yData2,"day")


                        window.onresize=function(){
                            myChart.resize();
                            myChart1.resize();
                            linechar.resize();
                        }

                    }else{
                        this.$message.warning(r.err_msg);
                    }
                    resolve();
                })
            })
        },
        getMoreDayInfo:function(){
            return new Promise ((resolve, reject) => {
                let startTime=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date(new Date().getTime()-3600*1000*24*4));
                let endTime=this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date());
                this.$api.post('/service/moreDayInfo', {startTime:startTime,endTime:endTime}, r => {
                    console.log(r)
                    if(r.err_code=="0"){
                        let classxData=[], classyData=[],officexData=[],officeyData=[];
                        let classList=r.data.classList;
                        let officeList=r.data.officeList;
                        for(let i=0;i<classList.length;i++){
                            classxData.push(classList[i].TIME);
                            classyData.push(classList[i].VALUE);
                        }
                        for(let i=0;i<officeList.length;i++){
                            officexData.push(officeList[i].TIME);
                            officeyData.push(officeList[i].VALUE);
                        }
                        let threetop1= this.$tool.barChar("threetop1","教室总能耗",classxData,classyData,"#4C78FF");
                        let threetop2=this.$tool.barChar("threetop2","办公室总能耗",officexData,officeyData,"#4C78FF");
                        window.onresize=function(){
                            threetop1.resize();
                            threetop2.resize();
                        }
                    }else{
                        this.$message.warning(r.err_msg);
                    }
                    resolve();
                })
            })
        }
    },

    watch: {
        
    },
    components:{}

 }
</script>


<style scoped lang="less">
    .loncom_slidebox{
        display:flex;
        flex-wrap:wrap;
        width: calc(100% + 26px) !important;
        height: 250px;
        margin-bottom: 26px;
    }
    .loncom_index_publicbox{
        width: calc(25% - 26px);
        margin: 0 26px 0 0;
        height: 100%;
        box-shadow: inset 0px 0px 4px #4963bd;
    }
    .loncom_slideline{
        width: 100%;
        margin: 0;
        height: calc(100% - 302px);
    }
    
</style>
