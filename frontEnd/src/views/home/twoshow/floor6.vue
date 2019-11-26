<template>
    <div class="floor_right_map" id="centermap">
        <img :src="'images/2-6F.png'" usemap="#map" id="mapimg">
        <map name="map" id="map">
            <template v-for="item in floorData">
                <otherpropver v-if="item.code=='22_603_604'" :item="item"></otherpropver>
                <propver v-else :item="item"></propver>
            </template>
            <!-- <el-popover
                class="mapshow" coords="1349,62,1552,186"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室601-602</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室601-602</h2>
                    <div class="info">
                        <p>
                            <span class="txt">有人/无人</span>
                            <span class="number">有人</span>
                        </p>
                        <p>
                            <span class="txt">空调状态</span>
                            <span class="number">打开</span>
                        </p>
                        <p>
                            <span class="txt">照明状态</span>
                            <span class="number">关闭</span>
                        </p>
                        <p>
                            <span class="txt">环境温度</span>
                            <span class="number">25.6</span>
                        </p>
                        <p>
                            <span class="txt">实时能耗</span>
                            <span class="number">1.6kw</span>
                        </p>
                    </div>
                </div>
            </el-popover>
            <el-popover
                class="mapshow" coords="570,30,900,30,900,60,1112,60,1112,183,570,183" floor="A"
                width="220"
                trigger="hover">
                <div class="maparea othermaparea" slot="reference">
                    <div class="othermap othermap-top"></div>
                    <div class="othermap othermap-bottom"></div>
                    <div class="maparea-con"><div class="maparea-conbox">603-604</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>603-604</h2>
                    <div class="info">
                        <p>
                            <span class="txt">有人/无人</span>
                            <span class="number">有人</span>
                        </p>
                        <p>
                            <span class="txt">空调状态</span>
                            <span class="number">打开</span>
                        </p>
                        <p>
                            <span class="txt">照明状态</span>
                            <span class="number">关闭</span>
                        </p>
                        <p>
                            <span class="txt">环境温度</span>
                            <span class="number">25.6</span>
                        </p>
                        <p>
                            <span class="txt">实时能耗</span>
                            <span class="number">1.6kw</span>
                        </p>
                    </div>
                </div>
            </el-popover>
            <el-popover
                class="mapshow" coords="205,30,553,186"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室605-606</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室605-606</h2>
                    <div class="info">
                        <p>
                            <span class="txt">有人/无人</span>
                            <span class="number">有人</span>
                        </p>
                        <p>
                            <span class="txt">空调状态</span>
                            <span class="number">打开</span>
                        </p>
                        <p>
                            <span class="txt">照明状态</span>
                            <span class="number">关闭</span>
                        </p>
                        <p>
                            <span class="txt">环境温度</span>
                            <span class="number">25.6</span>
                        </p>
                        <p>
                            <span class="txt">实时能耗</span>
                            <span class="number">1.6kw</span>
                        </p>
                    </div>
                </div>
            </el-popover> -->
           
        </map>
        <webSocket :wsInfo="infoData" v-if="infoData" v-on:backInfo="setValue"></webSocket>
    </div>
</template>

<script>
import map from '../mixin/map'
export default {
    mixins:[map],
    props:["info"],
    created () {
        
    },
    mounted() {
            
    },
    data() {
        return {
             floorData:[
                {coords:"1349,62,1552,186",code:"22_601_602",classname:"22_601_602",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"570,30,900,30,900,60,1112,60,1112,183,570,183",code:"22_603_604",classname:"22_603_604",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"205,30,553,186",code:"22_605_606",classname:"22_605_606",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
            ],
        }
    },
    methods:{
        
    },
    watch: {
        
    },
    components:{}

 }
</script>


