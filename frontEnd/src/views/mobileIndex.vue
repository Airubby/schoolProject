<template>
    <div class="loncom_content mobile_index" v-loading="loading">
        <div class="index_top">
            <div class="index_top_title">元岗校区能效管理系统</div>
            <div class="index_top_con">
                <div class="index_top_box">
                    <p>{{dataInfo.allPower}}</p>
                    <span>今日用电(kwh)</span>
                </div>
                <div class="index_top_box">
                    <p>{{dataInfo.nowPower}}</p>
                    <span>实时功耗(Kw)</span>
                </div>
            </div>
        </div>
        <div class="loncom_index_center">
            <router-link to="/msite"><img src="~@/assets/images/home.png"></router-link>
        </div>
        <div class="loncom_index_bottom">
            <div class="loncom_index_bottombox">
                <router-link to="/msite/control">
                    <div class="loncom-box">
                        <em class="topleft"></em>
                        <em class="topright"></em>
                        <em class="bottomleft"></em>
                        <em class="bottomright"></em>
                        <i class="el-icon-camera-solid"></i>
                    </div>
                </router-link>
            </div>
            <div class="loncom_index_bottombox">
                <router-link to="/msite/analyze">
                    <div class="loncom-box">
                        <em class="topleft"></em>
                        <em class="topright"></em>
                        <em class="bottomleft"></em>
                        <em class="bottomright"></em>
                        <i class="el-icon-s-data"></i>
                    </div>
                </router-link>
            </div>
            <div class="loncom_index_bottombox">
                <router-link to="/msite/alarm">
                    <div class="loncom-box">
                        <em class="topleft"></em>
                        <em class="topright"></em>
                        <em class="bottomleft"></em>
                        <em class="bottomright"></em>
                        <i class="el-icon-message-solid"></i>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    created () {
        if(!sessionStorage.loginInfo){
            this.$router.push({path:'/login'});
        }
        let _this=this;
        this.getInfo();
        this.timer=setInterval(function(){
            _this.getInfo();
        },300000)
    },
    mounted() {
         
    },
    destroyed() {
        clearInterval(this.timer);
        this.timer='';
    },
    data() {
        return {
            loading:false,
            timer:'',
            dataInfo:{
                nowPower:'',
                allPower:'',
            },
        }
    },
    methods:{
        getInfo:function(){
            this.loading=true;
            //startTime:this.$tool.Format("yyyy-MM-dd 00:00:00",new Date()),endTime:this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date())
            this.$api.get('/service/energy', {}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    this.dataInfo=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        }
      
    },
    watch: {
        
    },
    components:{}

 }
</script>


