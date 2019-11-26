<template>
    <div class="floor_right_map" id="centermap">
        <img :src="'images/1-1F.png'" usemap="#map" id="mapimg">
        <map name="map" id="map">
            <template v-for="item in floorData">
                <propver :item="item"></propver>
            </template>
            <!-- <el-popover
                class="mapshow" coords="102,653,218,755"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室101</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室101</h2>
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
                class="mapshow" coords="232,653,350,755"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室102</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室102</h2>
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
                class="mapshow" coords="362,653,480,755"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室103</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室103</h2>
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
                class="mapshow" coords="492,653,610,755"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室104</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室104</h2>
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
                class="mapshow" coords="621,653,742,755"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">招生办</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>招生办</h2>
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
                class="mapshow" coords="753,653,870,755"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">医务室</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>医务室</h2>
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
                class="mapshow" coords="936,653,1146,818"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室107</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室107</h2>
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
                class="mapshow" coords="936,38,1036,202"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室111#1</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室111</h2>
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
                class="mapshow" coords="1040,38,1146,202"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室111#2</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室111</h2>
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
                class="mapshow" coords="754,88,869,202"
                width="220"
                trigger="hover">
                <div class="maparea" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室112</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室112</h2>
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
                class="mapshow" coords="623,88,740,202"
                width="220"
                trigger="hover">
                <div class="maparea openair" slot="reference">
                    <div class="maparea-con"><div class="maparea-conbox">教室113</div></div>
                </div>
                <div class="mapcon loncom_index_publicbox">
                    <h2>教室113</h2>
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
                {coords:"67,643,188,748",code:"11_101",classname:"11_101",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"198,643,320,748",code:"11_102",classname:"11_102",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"327,643,450,748",code:"11_103",classname:"11_103",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"459,643,580,748",code:"11_104",classname:"11_104",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"588,643,710,748",code:"12_zsb1",classname:"12_zsb1",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"720,643,840,748",code:"12_yws1",classname:"12_yws1",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"902,643,1117,811",code:"12_107",classname:"12_107",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"902,30,1006,197",code:"12_111_1",classname:"12_111_1",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"1009,30,1118,197",code:"12_111_2",classname:"12_111_2",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"720,80,839,197",code:"11_112",classname:"11_112",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
                {coords:"588,80,710,197",code:"11_113",classname:"11_113",infrareddev:"",air:false,lamp:false,humituredev:"",roompower:""},
            ],
        }
    },
    methods:{
        
    },

 }
</script>


