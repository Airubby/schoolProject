<template>
    <div class="loncom_content">
        <lon-top></lon-top>
        <div class="loncom_sidebar" ref="sidebar">
            <div class="loncom_sidebar_list">
                <ul>
                    <li>
                        <router-link to="/site/control">
                            <p><span class="icon-loncom_equipment"></span>运行监控</p>
                        </router-link>
                    </li>
                    <li>
                        <router-link to="/site/waterEle">
                            <p><span class="icon-loncom_equipment"></span>水电监控</p>
                        </router-link>
                    </li>
                    <li>
                        <router-link to="/site/analyze">
                            <p><span class="icon-loncom_statement"></span>运行分析</p>
                        </router-link>
                    </li>
                    <li>
                        <router-link to="/site/config">
                            <p><span class="icon-loncom_system"></span>时区配置</p>
                        </router-link>
                    </li>
                    <li>
                        <router-link to="/site/account">
                            <p><span class="icon-loncom_security"></span>账号管理</p>
                        </router-link>
                    </li>
                    <li>
                        <router-link to="/site/log">
                            <p><span class="icon-loncom_equipment"></span>系统日志</p>
                        </router-link>
                    </li>
                </ul>
            </div>
        </div>
        <div class="loncom_sidebar_right" ref="content">
            <router-view></router-view>
        </div>
        
    </div>
</template>


<script>
export default {
    created () {
        this.name = sessionStorage.getItem("listName");
        
    },
    mounted() {
        
    },
    data() {
       return {
           navbtn:'open',
           name:'',
       }
   },
    methods:{
        navclick(){
            if(this.navbtn=='open'){
                $(this.$refs.sidebar).css({
                    "left":"-200px",
                    "transition":"all 0.4s ease-in"
                });
                $(this.$refs.content).css({
                    "padding-left":"0",
                    "transition":"all 0.4s ease-in"
                });
                $(this.$refs.navbtn).css({
                    "right":"-33px",
                    "color":"#17C4BB"
                })
                this.navbtn='close';
            }else{
                $(this.$refs.sidebar).css({
                    "left":"0px",
                    "transition":"all 0.4s ease-in"
                });
                $(this.$refs.content).css({
                    "padding-left":"200px",
                    "transition":"all 0.4s ease-in"
                });
                $(this.$refs.navbtn).css({
                    "right":"0",
                    "color":"#fff"
                })
                this.navbtn='open';
            }
             
        }
    }
}
</script>