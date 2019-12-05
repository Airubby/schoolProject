<template>
    <div class="loncom_content">
        <home-top></home-top>
        <div class="loncom_index_con flex" style="justify-content: space-between;">
            <floor-left :info="info"></floor-left>
            <one v-if="info.building=='1'" :info="info" v-on:backInfo="backInfo"></one>
            <two v-if="info.building=='2'" :info="info" v-on:backInfo="backInfo"></two>
            <four v-if="info.building=='4'" :info="info" v-on:backInfo="backInfo"></four>
        </div>
    </div>
</template>

<script>
import one from './oneshow/index'
import two from './twoshow/index'
import four from './fourshow/index'
import floorLeft from './components/floorLeft'
import homeTop from '@/components/homeTop.vue'
export default {
    components:{one,two,four,floorLeft,homeTop},
    created () {
        this.changeInfo();
    },
    data() {
        return {
            floor:'1',
            info:{
                building:'1',
                data:{}
            },
        }
    },
    methods:{
        changeInfo:function(){
            let param = this.$route.query.param;
            if(param){
                this.info.building=JSON.parse(param).info;
                if(this.info.building=='4'){
                    this.floor="";
                }
                this.getInfo();
            }
        },
        getInfo:function(){
            this.$api.post('/service/queryGroup', {group:this.info.building,floor:this.floor}, r => {
                if(r.err_code=="0"){
                    this.info.data=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        backInfo:function(info){
            this.floor=info;
            this.getInfo();
        },
    },
    watch:{
        $route:function(val){
            this.changeInfo();
        }
    }
 }
</script>


