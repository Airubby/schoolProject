<template>
    <div class="loncom_content pabsolute">
        <div class="public_top">
            <div class="btn" @click="close">
                <i class="el-icon-close"></i>
            </div>
            <div class="title">导航菜单</div>
        </div>
        <div class="public_content">
            <ul class="menu">
                <li><router-link to="/mobile"><i class="el-icon-s-home"></i>主页</router-link></li>
                <li><router-link to="/msite/control"><i class="el-icon-camera-solid"></i>实时监控</router-link></li>
                <li><router-link to="/msite/analyze"><i class="el-icon-s-data"></i>用电排行</router-link></li>
                <li><router-link to="/msite/alarm"><i class="el-icon-message-solid"></i>超限告警</router-link></li>
            </ul>
        </div>
    </div>
</template>

<script>

export default {
    created () {
        console.log(this.menuInfo)
    },
    mounted() {
        
    },
    data() {
        return {
            
        }
    },
    methods:{
        close:function(){
            this.menuInfo.visible=false;
        }
    },
    watch: {
        'menuInfo.visible':function(val){
            if(val){
                this.$el.style.display="block";
                this.$el.style.left="0";
            }else{
                this.$el.style.display="none";
                this.$el.style.left="100%";
            }
            this.$el.style.transition="all 0.4s ease-in";
        }
    },
    props:["menuInfo"]  
}
</script>
<style lang="less" scoped>
    .loncom_content{
        left: 100%;
        top:0;
        z-index:999;
        background: #151936;
        display:none;
        .menu{
            li{
                width: 100%;
                height: 60px;
                border-bottom: 1px solid #222645;
                line-height: 60px;
                font-size: 16px;
                text-indent: 15px;
                color:#919CC5;
                i{
                    font-size:22px;
                    vertical-align: middle;
                    margin-right: 5px;
                }
                a{
                    color:#b9c6f2;
                }
                a.router-link-exact-active{
                    color:#4A78FF;
                }
            }
        }
    }
</style>
