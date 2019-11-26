<template>
    <div class="floor_right" style="padding-top:30px;">
        <el-row :gutter="30">
            <template v-for="item in infoData">
                <el-col :span="6">
                    <div class="mapcon loncom_index_publicbox">
                        <h2>{{item.classname}}</h2>
                        <div class="info">
                            <p>
                                <span class="txt">有人/无人</span>
                                <span class="number" v-if="parseInt(item.infrareddev)==0||item.infrareddev==''">无人</span>
                                <span class="number" v-else>有人</span>
                            </p>
                            <p>
                                <span class="txt">空调状态</span>
                                <span class="number" v-if="item.air">打开</span>
                                <span class="number" v-else>关闭</span>
                            </p>
                            <p>
                                <span class="txt">照明状态</span>
                                <span class="number" v-if="item.lamp">打开</span>
                                <span class="number" v-else>关闭</span>
                            </p>
                            <p>
                                <span class="txt">环境温度</span>
                                <span class="number">{{item.humituredev}}℃</span>
                            </p>
                            <p>
                                <span class="txt">实时能耗</span>
                                <span class="number">{{item.roompower}}kw</span>
                            </p>
                        </div>
                    </div>
                </el-col>
            </template>
        </el-row>
        <webSocket :wsInfo="infoData" v-if="infoData" v-on:backInfo="setValue"></webSocket>
    </div>
</template>

<script>
import webSocket from '@/components/webSocket.vue'
export default {
    components:{webSocket},
    props:["info"],
    created () {
        this.infoData=this.info.data.list?this.info.data.list:[];
        console.log(this.infoData)
    },
    mounted() {
            
    },
    data() {
        return {
           infoData:[],
        }
    },
    methods:{
        setValue:function(){
            // for(let i=0;i<this.floorData.length;i++){
            //     for(let j=0;j<this.infoData.length;j++){
            //         if(this.floorData[i].code==this.infoData[j].code){
            //             this.floorData[i]=Object.assign(this.floorData[i],this.infoData[j]);
            //         }
            //     }
            // }
        },
    },
    watch: {
        info:{
            handler:function(val){
                this.infoData=this.info.data.list?this.info.data.list:[];
            },
            deep: true
        }
    },
    

 }
</script>


