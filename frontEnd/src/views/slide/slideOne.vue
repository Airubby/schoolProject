<template>
    <div class="loncom_content">
        <div class="loncom_index_publicbox" id="top1"></div>
        <div class="loncom_index_publicbox" id="top2"></div>
        <div class="loncom_index_publicbox" id="top3"></div>
        <div class="loncom_index_publicbox" id="top4"></div>
    </div>
</template>

<script>

export default {
    created () {
        // this.getList();
    },
    mounted() {
        // let xData=['温湿度13','温湿度12','温湿度11','温湿度10','温湿度09','温湿度08','温湿度07','温湿度06','温湿度05','温湿度04'];
        // let yData=[10, 8, 14, 34, 29, 33, 45,30,25,20];
        // let color="#4C78FF";
        // let color1="#2CA858"
        // let title="教室能耗排名TOP10";
        // this.$tool.barChar("top1",title,xData,yData,color,50)
        // this.$tool.barChar("top2",title,xData,yData,color1,50)
        // this.$tool.barChar("top3",title,xData,yData,color,50)
        // this.$tool.barChar("top4",title,xData,yData,color1,50)
        
    },
    data() {
        return {
            loading:false,
            search:[],

        }
    },
    methods:{
        getList:function(topInfo){
            let _this=this;
            let classtopy=topInfo.classtopy;  //教室能耗
            let classtopn=topInfo.classtopn;  //教室节能
            let officetopy=topInfo.officetopy;
            let officetopn=topInfo.officetopn;
            let xData1=[],yData1=[],xData2=[],yData2=[],xData3=[],yData3=[],xData4=[],yData4=[];
            for(let i=0;i<classtopy.length;i++){
                xData1.push(classtopy[i].classname);
                yData1.push(classtopy[i].value);
            }
            for(let i=0;i<classtopn.length;i++){
                xData2.push(classtopn[i].classname);
                yData2.push(classtopn[i].value);
            }
            for(let i=0;i<officetopy.length;i++){
                xData3.push(officetopy[i].classname);
                yData3.push(officetopy[i].value);
            }
            for(let i=0;i<officetopn.length;i++){
                xData4.push(officetopn[i].classname);
                yData4.push(officetopn[i].value);
            }
            
            let top1=this.$tool.barChar('top1',"教室能耗排名TOP10",xData1,yData1,"#f4092a",40);
            let top2=this.$tool.barChar('top2',"教室节能排名TOP10",xData2,yData2,"#52c41a",40);
            let top3=this.$tool.barChar('top3',"办公室能耗排名TOP10",xData3,yData3,"#f4092a",40);
            let top4=this.$tool.barChar('top4',"办公室节能排名TOP10",xData4,yData4,"#52c41a",40);
            window.onresize=function(){
                top1.resize();
                top2.resize();
                top3.resize();
                top4.resize();
            }
        }
    },
    watch: {
        topInfo:{
            handler:function(val){
                this.getList(val);
            },
            deep: true
        }
    },
    props:["topInfo"],
    components:{}

 }
</script>


<style scoped lang="less">
    .loncom_content{
        display:flex;
        flex-wrap:wrap;
        width: calc(100% + 26px) !important;
    }
    .loncom_index_publicbox{
        width: calc(50% - 26px);
        margin: 0 26px 26px 0;
        height: calc(50% - 26px);
        box-shadow: inset 0px 0px 4px #4963bd;
    }
</style>
