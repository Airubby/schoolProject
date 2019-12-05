<template>
    <div class="loncom_content">
        <div class="loncom_index_publicbox">
            <h2 class="loncom_index_top10title">教室能耗排名TOP10</h2>
            <ul class="loncom_index_top10">
                <li v-for="(item,index) in top_data1" v-if="index<10"><em>{{index+1}}</em><span>{{item.classname}}</span><span class="loncom_fr">{{item.value}}</span></li>
            </ul>
        </div>
        <div class="loncom_index_publicbox">
            <h2 class="loncom_index_top10title">教室节能排名TOP10</h2>
            <ul class="loncom_index_top10 loncom_index_asc">
                <li v-for="(item,index) in top_data2" v-if="index<10"><em>{{index+1}}</em><span>{{item.classname}}</span><span class="loncom_fr">{{item.value}}</span></li>
            </ul>
        </div>
        <div class="loncom_index_publicbox">
            <h2 class="loncom_index_top10title">办公室能耗排名TOP10</h2>
            <ul class="loncom_index_top10">
                <li v-for="(item,index) in top_data3" v-if="index<10"><em>{{index+1}}</em><span>{{item.classname}}</span><span class="loncom_fr">{{item.value}}</span></li>
            </ul>
        </div>
        <div class="loncom_index_publicbox">
            <h2 class="loncom_index_top10title">办公室节能排名TOP10</h2>
            <ul class="loncom_index_top10 loncom_index_asc">
                <li v-for="(item,index) in top_data4" v-if="index<10"><em>{{index+1}}</em><span>{{item.classname}}</span><span class="loncom_fr">{{item.value}}</span></li>
            </ul>
        </div>
    </div>
</template>

<script>

export default {
    created () {
    },
    mounted() {
        
    },
    data() {
        return {
            top_data1:[],
            top_data2:[],
            top_data3:[],
            top_data4:[],
        }
    },
    methods:{
        
    },
    watch: {
        topInfo:{
            handler:function(val){
                
                this.top_data1=val.classtopy;  //教室能耗
                this.top_data2=val.classtopn;  //教室节能
                this.top_data3=val.officetopy;
                this.top_data4=val.officetopn;
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
        width: calc(25% - 26px);
        margin: 0 26px 26px 0;
        height: calc(100% - 26px);
        box-shadow: inset 0px 0px 4px #4963bd;
    }
    .loncom_index_top10{
        height: calc(100% - 36px);
    }
    .loncom_index_top10 li{
        height: 10%;
        line-height: inherit;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 10px;
        border-bottom: 1px solid #233169;
    }
    .loncom_index_top10 li:nth-last-of-type(1){
        border: none;
    }
</style>
