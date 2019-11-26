<template>
    <div class="floor_right_map" id="centermap">
        <img :src="'images/2-2F.png'" usemap="#map" id="mapimg">
        <map name="map" id="map">
            <template v-for="item in floorData">
                <propver :item="item"></propver>
            </template>
            <!-- <el-popover
                class="mapshow" coords="1280,86,1420,170"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">22_201_202</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>22_201_202</h2>
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
                class="mapshow" coords="530,85,710,170"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">22_203_204</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>22_203_204</h2>
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
                class="mapshow" coords="384,85,519,170"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">22_205_206</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>22_205_206</h2>
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
                class="mapshow" coords="65,238,186,356"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">207</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>207</h2>
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
                {coords:"1280,86,1420,170",code:"22_201_202",classname:"22_201_202",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"530,85,710,170",code:"22_203_204",classname:"22_203_204",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"384,85,519,170",code:"22_205_206",classname:"22_205_206",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"65,238,186,356",code:"22_207",classname:"22_207",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
            ],
        }
    },
    methods:{
        
    },

 }
</script>


