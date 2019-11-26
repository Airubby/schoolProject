<template>
    <div class="loncom_content">
        <div class="loncom_content" v-if="!$store.getters.getDevType">
            <lon-top></lon-top>
            <div class="loncom_sidebar" ref="sidebar">
                <div class="loncom_sidebar_list">
                    <ul>
                        <li v-for="item in navList" v-if="navList.length>0">
                            <router-link :to="item.path">
                                <p><span :class="item.meta.icon"></span>{{item.menuname}}</p>
                            </router-link>
                        </li>
                    <!-- 
                        <li>
                            <router-link to="/site/analyze">
                                <p><span class="icon-loncom_statement"></span>运行分析</p>
                            </router-link>
                        </li>
                        <li>
                            <router-link to="/site/waterEle">
                                <p><span class="icon-loncom_power"></span>水电监控</p>
                            </router-link>
                        </li>
                        <li>
                            <router-link to="/site/alarm">
                                <p><span class="icon-loncom_alarm"></span>超限告警</p>
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
                            <router-link to="/site/role">
                                <p><span class="icon-loncom_security"></span>角色管理</p>
                            </router-link>
                        </li>
                        <li>
                            <router-link to="/site/log">
                                <p><span class="icon-loncom_equipment"></span>系统日志</p>
                            </router-link>
                        </li> 
                    -->
                    </ul>
                </div>
            </div>
            <div class="loncom_sidebar_right" ref="content">
                <router-view></router-view>
            </div>
        </div>
        <router-view v-if="$store.getters.getDevType"></router-view>
    </div>
</template>


<script>
export default {
    created () {
        
    },
    computed: {
        navList: {
            get() {
                return this.$store.getters.navList
            },
        },
    },
    mounted() {
        
    },
    data() {
       return {
           navbtn:'open',
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