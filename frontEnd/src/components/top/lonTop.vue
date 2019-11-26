<template>
    <div class="loncom_index_body_top">
        <router-link to="/" class="loncom_logo">
            <img src="~@/assets/images/logo.png" alt="">
        </router-link>
        <div class="loncom_index_top_right">
            <ul>
                <li>
                    <el-tooltip class="item" effect="dark" content="返回首页" placement="bottom">
                        <router-link to="/">
                            <img src="~@/assets/images/home.png" alt="" style="height:28px;">
                        </router-link>
                    </el-tooltip>
                </li>
                <li>
                    <el-tooltip class="item" effect="dark" content="全屏展示" placement="bottom">
                        <a href="javascript:void(0)" @click="switcFullScreen()">
                            <i class="el-icon-full-screen"></i>
                        </a>
                    </el-tooltip>
                </li>
                <li>
                    <el-tooltip class="item" effect="dark" content="告警信息" placement="bottom">
                        <a href="javascript:void(0)" @click="enterAlarm()">
                            <el-badge :value="alarmInfo[0]['value']"  class="item">
                                <i class="el-icon-bell"></i>
                            </el-badge>
                        </a>
                    </el-tooltip>
                </li>
                <li class="loncom_index_userhover">
                    <el-popover
                        placement="bottom"
                        trigger="hover">
                        <div class="loncom_index_user_info loncom_index_publicbox" style="width:200px;height: 170px;">
                            <h2>
                                <span>账户信息</span>
                            </h2>
                            <ul>
                                <li>
                                    <label>账号：</label><div class="loncom_dis_inline">{{userInfo.userid}}</div>
                                </li>
                                <li>
                                    <label>电话：</label><div class="loncom_dis_inline">{{userInfo.phone}}</div>
                                </li>
                                <li>
                                    <label>邮箱：</label><div class="loncom_dis_inline">{{userInfo.email}}</div>
                                </li>
                            </ul>
                        </div>
                        <a href="javascript:void(0)" slot="reference" @click="enterUserList">
                            <i class="el-icon-user loncom_mr5"></i>
                            <span id="username" style="vertical-align: top;">{{userInfo.name}}</span>
                        </a>
                    </el-popover>
                </li>
                <li>
                    <el-tooltip class="item" effect="dark" content="退出登录" placement="bottom">
                        <a href="javascript:void(0)" @click="powerOff">
                            <i class="el-icon-switch-button"></i>
                        </a>
                    </el-tooltip>
                </li>
            </ul>
        </div>
        <webSocket :wsInfo="alarmInfo" v-if="alarmInfo"></webSocket>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import store from '@/store'
import Cookies from 'js-cookie'
import webSocket from '@/components/webSocket.vue'
export default {
    name:'lonTop',
    created () {
        if(sessionStorage.energyInfo){
            let userid=this.$tool.Decrypt(sessionStorage.energyInfo).split("_")[1];
            this.getDetail(userid)
            this.getAlarm();
        }
    },
    mounted() {
      

    },
    computed:{
        // ...mapGetters([
        //     'getChangeUser'
        // ]),
        userInfo:{
            get(){
                return this.$store.getters.userInfo;
            }
        }
    },
    data(){
        return {
            alarmInfo:[{
                alarmkey:"alarmAll",
                value:'0',
            }],
        }
    },
    methods:{
        getDetail:function(id){
            this.$api.post('/user/details', {id:id}, r => {
                console.log(r)
                if(r.err_code=="0"){
                    store.dispatch("setChangeUser",r.data);
                }
            });
        },
        getAlarm:function(){
            this.$api.post('/alarm/count', {createTime:this.$tool.Format('yyyy-MM-dd 00:00:00',new Date())}, r => {
                console.log(r)
                if(r.err_code=="0"){
                    this.alarmInfo[0].value=r.data.value;
                }else{
                    // this.$message.warning(r.err_msg);
                }
            });
        },
        switcFullScreen:function(){
            this.$tool.switcFullScreen();
        },
        enterAlarm:function(){
            this.$router.push({path:'/site/alarm'});
        },
        //退出登录
        powerOff:function(){
            this.$confirm("退出系统?", '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    this.$api.post('/user/out', {}, r => {
                        if(r.err_code=="0"){
                            sessionStorage.clear();
                            sessionStorage.energyInfo=""; 
                            this.$router.push({path:'/login'});
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });

            });
        },
        enterUserList:function(){
            this.$router.push({path:'/site/account'});
        },

    }, 
    components:{webSocket},
    watch:{
        // getChangeUser: function(val) { 
        //     if(val){
        //         let userid=this.$tool.Decrypt(sessionStorage.energyInfo).split("_")[1];
        //         this.getDetail(userid);
        //     }
        // },
    } 
}
</script>

