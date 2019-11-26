<template>
    <el-popover
        class="mapshow" :coords="item.coords" floor="A"
        width="220"
        trigger="hover">
        <div class="maparea othermaparea" slot="reference">
            <div class="othermap othermap-top"></div>
            <div class="othermap othermap-bottom"></div>
            <div class="maparea-con"><div class="maparea-conbox">{{item.classname}}</div></div>
        </div>
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
    </el-popover>
</template>

<script>
export default {
    created () {
        
    },
    data() {
        return {
            
        }
    },
    methods:{
        
    },
    props:["item"]
 }
</script>


