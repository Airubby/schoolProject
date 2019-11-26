<template>
    <div class="floor_left">
        <div class="classoffice">
            <div class="loncom_mb15">
                <h2>本月用电</h2>
                <div class="number classnumber">
                    <span class="color">{{infoData.data.monthsum}}</span>KWh
                </div>
            </div>
            <div>
                <h2>今日用电量</h2>
                <div class="number officenumber">
                    <span class="color">{{infoData.data.psum}}</span>KWh
                </div>
            </div>
        </div>
        <div class="floor_left_img">
            <!-- <p>未关灯房间</p>
            <el-badge :value="2" :max="99" class="item">
                <img src="~@/assets/images/deng.svg">
            </el-badge> -->
            <p>未关空调房间</p>
            <el-badge :value="airOpenNum" :max="99" class="item" @click.native="closeAirFn">
                <img src="~@/assets/images/kongtiao.svg">
            </el-badge>
        </div>
        <div class="floor_left_route">
            <div class="center_route" v-if="info.building!='1'" @click="enterFloor('1')">1#楼</div>
            <div class="center_route" v-if="info.building!='2'" @click="enterFloor('2')">2#楼</div>
            <div class="center_route" v-if="info.building!='4'" @click="enterFloor('4')">4#楼</div>
            <router-link to="/site" class="center_route">进入管理页</router-link>
            <router-link to="/" class="center_route">返回首页</router-link>
        </div>
        <close-air v-if="closeAirInfo.visible" :dialogInfo="closeAirInfo"></close-air>
    </div>
</template>

<script>
import closeAir from './closeAir.vue'
export default {
    components:{closeAir},
    created () {
        
    },
    data() {
        return {
            airOpenNum:0,
            infoData:{
                building:'1',
                data:{
                    classvalue:"0",
                    monthsum:'0',
                    officevalue:'0',
                    psum:'0',
                    list:[]
                }
            },
            closeAirInfo:{
                visible:false,
                table_data:[],
            }
        }
    },
    methods:{
        enterFloor:function(building){
            this.$router.push({path:'/view',query:{
                param:JSON.stringify({"info":building})
            }});
        },
        closeAirFn:function(){
            this.closeAirInfo.visible=true;
        },
    },
    watch:{
        info:{
            handler:function(val){
                console.log(val)
                this.infoData=val;
                let list=val.data.list?val.data.list:[];
                if(list.length>0){
                    let num=0;
                    this.closeAirInfo.table_data=[];
                    for(let i=0;i<list.length;i++){
                        if(list[i].air){  //true 表示开着的
                            num+=1;
                            this.closeAirInfo.table_data.push(list[i]);
                        }
                    }
                    this.airOpenNum=num;
                }
                
            },
            deep: true
        }
    },
    props:["info"]
 }
</script>


