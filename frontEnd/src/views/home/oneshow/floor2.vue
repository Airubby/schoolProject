<template>
    <div class="floor_right_map" id="centermap">
        <img :src="'images/1-2F.png'" usemap="#map" id="mapimg">
        <map name="map" id="map">
            <template v-for="item in floorData">
                <propver :item="item"></propver>
            </template>
            <!-- <el-popover
                class="mapshow" coords="72,612,187,713"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室201</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室201</h2>
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
                class="mapshow" coords="199,612,314,713"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室202</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室202</h2>
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
                class="mapshow" coords="326,612,442,713"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室203</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室203</h2>
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
                class="mapshow" coords="454,612,569,713"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室204</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室204</h2>
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
                class="mapshow" coords="581,612,696,713"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室205</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室205</h2>
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
                class="mapshow" coords="707,612,822,713"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室206</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室206</h2>
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
                class="mapshow" coords="887,299,981,485"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室210</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室210</h2>
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
                class="mapshow" coords="709,61,822,174"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室213</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室213</h2>
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
                class="mapshow" coords="518,61,696,174"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室214</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室214</h2>
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
                class="mapshow" coords="454,61,505,174"
                width="220"
                trigger="hover">
                <div class="maparea openair" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室215</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室215</h2>
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
                class="mapshow" coords="353,61,442,174"
                width="220"
                trigger="hover">
                <div class="maparea openair" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室216</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室216</h2>
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
                class="mapshow" coords="263,61,348,174"
                width="220"
                trigger="hover">
                <div class="maparea openair" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室217</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室217</h2>
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
                class="mapshow" coords="168,61,250,174"
                width="220"
                trigger="hover">
                <div class="maparea openair" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室218</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室218</h2>
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
                class="mapshow" coords="72,61,162,174"
                width="220"
                trigger="hover">
                <div class="maparea openair" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室219</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室219</h2>
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
                {coords:"72,612,187,713",code:"11_201",classname:"11_201",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"199,612,314,713",code:"11_202",classname:"11_202",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"326,612,442,713",code:"11_203",classname:"11_203",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"454,612,569,713",code:"11_204",classname:"11_204",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"581,612,696,713",code:"11_205",classname:"11_205",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"707,612,822,713",code:"11_206",classname:"11_206",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"887,299,981,485",code:"12_210",classname:"12_210",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"709,61,822,174",code:"11_213",classname:"11_213",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"518,61,696,174",code:"11_214",classname:"11_214",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"454,61,505,174",code:"11_215",classname:"11_215",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"353,61,442,174",code:"11_216",classname:"11_216",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"263,61,348,174",code:"11_217",classname:"11_217",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"168,61,250,174",code:"11_218",classname:"11_218",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"72,61,162,174",code:"11_219",classname:"11_219",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
            ],
        }
    },
    methods:{
        
    },

 }
</script>


