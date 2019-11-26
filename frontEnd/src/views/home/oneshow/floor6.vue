<template>
    <div class="floor_right_map" id="centermap">
        <img :src="'images/1-6F.png'" usemap="#map" id="mapimg">
        <map name="map" id="map">
            <template v-for="item in floorData">
                <propver :item="item"></propver>
            </template>
            <!-- <el-popover
                class="mapshow" coords="71,638,194,740"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室601</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室601</h2>
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
                class="mapshow" coords="203,638,325,740"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室602</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室602</h2>
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
                class="mapshow" coords="336,638,458,740"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室603</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室603</h2>
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
                class="mapshow" coords="468,638,590,740"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室604</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室604</h2>
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
                class="mapshow" coords="602,638,722,740"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室605</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室605</h2>
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
                class="mapshow" coords="734,638,854,740"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室606</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室606</h2>
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
                class="mapshow" coords="919,638,1020,806"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室607</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室607</h2>
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
                class="mapshow" coords="1030,638,1134,806"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室608</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室608</h2>
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
                class="mapshow" coords="1062,562,1134,623"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室608-2</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室608-2</h2>
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
                class="mapshow" coords="919,312,1020,505"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室609</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室609</h2>
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
                class="mapshow" coords="1062,195,1134,256"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室610-2</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室610-2</h2>
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
                class="mapshow" coords="1030,12,1134,182"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室610</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室610</h2>
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
                class="mapshow" coords="919,12,1019,182"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室611</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室611</h2>
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
                class="mapshow" coords="733,62,854,182"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室612</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室612</h2>
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
                class="mapshow" coords="602,62,722,182"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室613</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室613</h2>
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
                {coords:"71,638,194,740",code:"11_601",classname:"11_601",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"203,638,325,740",code:"11_602",classname:"11_602",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"336,638,458,740",code:"11_603",classname:"11_603",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"468,638,590,740",code:"11_604",classname:"11_604",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"602,638,722,740",code:"11_605",classname:"11_605",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"734,638,854,740",code:"11_606",classname:"11_606",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"919,638,1020,806",code:"12_607",classname:"12_607",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"1030,638,1134,806",code:"12_608",classname:"12_608",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"1062,562,1134,623",code:"12_608_2",classname:"12_608_2",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"919,312,1020,505",code:"12_609",classname:"12_609",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"1062,195,1134,256",code:"12_610_2",classname:"12_610_2",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"1030,12,1134,182",code:"12_610",classname:"12_610",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"919,12,1019,182",code:"12_611",classname:"12_611",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"733,62,854,182",code:"11_612",classname:"11_612",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"602,62,722,182",code:"11_613",classname:"11_613",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
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


