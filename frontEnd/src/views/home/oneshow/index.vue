<template>
    <div class="floor_right">
        <div class="floor_right_change">
            <span>教学楼</span>
            <div class="floor_radio">
                <el-radio v-model="floorRadio" label="1" border size="small">1层</el-radio>
                <el-radio v-model="floorRadio" label="2" border size="small">2层</el-radio>
                <el-radio v-model="floorRadio" label="3" border size="small">3层</el-radio>
                <el-radio v-model="floorRadio" label="4" border size="small">4层</el-radio>
                <el-radio v-model="floorRadio" label="5" border size="small">5层</el-radio>
                <el-radio v-model="floorRadio" label="6" border size="small">6层</el-radio>
                <el-radio v-model="floorRadio" label="7" border size="small">7层</el-radio>
                <el-radio v-model="floorRadio" label="8" border size="small">8层</el-radio>
                <el-radio v-model="floorRadio" label="9" border size="small">9层</el-radio>
                <el-radio v-model="floorRadio" label="10" border size="small">10层</el-radio>
            </div>    
        </div>
        <floor1 v-if="floorRadio=='1'" :info="info"></floor1>
        <floor2 v-if="floorRadio=='2'" :info="info"></floor2>
        <floor3 v-if="floorRadio=='3'" :info="info"></floor3>
        <floor4 v-if="floorRadio=='4'" :info="info"></floor4>
        <floor5 v-if="floorRadio=='5'" :info="info"></floor5>
        <floor6 v-if="floorRadio=='6'" :info="info"></floor6>
        <floor7 v-if="floorRadio=='7'" :info="info"></floor7>
        <floor8 v-if="floorRadio=='8'" :info="info"></floor8>
        <floor9 v-if="floorRadio=='9'" :info="info"></floor9>
        <floor10 v-if="floorRadio=='10'" :info="info"></floor10>
    </div>
</template>

<script>
import floor1 from './floor1.vue'
import floor2 from './floor2.vue'
import floor3 from './floor3.vue'
import floor4 from './floor4.vue'
import floor5 from './floor5.vue'
import floor6 from './floor6.vue'
import floor7 from './floor7.vue'
import floor8 from './floor8.vue'
import floor9 from './floor9.vue'
import floor10 from './floor10.vue'
export default {
    components:{floor1,floor2,floor3,floor4,floor5,floor6,floor7,floor8,floor9,floor10},
    props:["info"],
    created () {
        
    },
    mounted() {
            
    },
    data() {
        return {
            floorRadio:'1',
        }
    },
    methods:{
        
    },
    watch: {
        floorRadio:function(val){
            this.$emit("backInfo",val);
        },
    },
    

 }
</script>


