<template>
    <div class="floor_right_map" id="centermap">
        <img :src="'images/2-3F.png'" usemap="#map" id="mapimg">
        <map name="map" id="map">
            <template v-for="item in floorData">
                <propver :item="item"></propver>
            </template>
            <!-- <el-popover
                class="mapshow" coords="1278,80,1378,170"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室301</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室301</h2>
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
                class="mapshow" coords="922,80,1110,170"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室302-303</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室302-303</h2>
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
                class="mapshow" coords="724,80,912,170"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室304-305</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室304-305</h2>
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
                class="mapshow" coords="65,235,182,354"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室309</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室309</h2>
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
                class="mapshow" coords="196,235,317,475"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室310</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室310</h2>
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
                class="mapshow" coords="328,235,448,475"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室311</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室311</h2>
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
                class="mapshow" coords="462,235,580,475"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室312</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室312</h2>
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
                class="mapshow" coords="592,235,711,475"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室313</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室313</h2>
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
                {coords:"1278,80,1378,170",code:"22_301",classname:"22_301",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"922,80,1110,170",code:"22_302_303",classname:"22_302_303",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"724,80,912,170",code:"22_304_305",classname:"22_304_305",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"65,235,182,354",code:"22_309",classname:"22_309",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"196,235,317,475",code:"22_310",classname:"22_310",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"328,235,448,475",code:"22_311",classname:"22_311",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"462,235,580,475",code:"22_312",classname:"22_312",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"592,235,711,475",code:"22_313",classname:"22_313",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
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


