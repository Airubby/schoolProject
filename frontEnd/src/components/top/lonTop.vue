<template>
    <div class="loncom_index_body_top">
        <router-link to="/" class="loncom_logo">
            <img src="~@/assets/images/logo.png" alt="">
        </router-link>
        <div class="loncom_index_top_right">
            <ul>
                <li>
                    <a href="javascript:void(0)" @click="switcFullScreen()">
                        <i class="el-icon-full-screen"></i>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)" @click="enterAlarm()">
                        <el-badge :value="alarmInfo[0]['value']"  class="item">
                            <i class="el-icon-bell"></i>
                        </el-badge>
                    </a>
                </li>
                <li class="loncom_index_userhover">
                    <el-popover
                        placement="bottom"
                        trigger="hover">
                        <div class="loncom_index_user_info" style="width:200px;height: 150px;">
                            <h2>
                                <span>账户信息</span>
                            </h2>
                            <ul>
                                <li>
                                    <label>账号：</label><div class="loncom_dis_inline">{{loginInfo.userid}}</div>
                                </li>
                                <li>
                                    <label>电话：</label><div class="loncom_dis_inline">{{loginInfo.phone}}</div>
                                </li>
                                <li>
                                    <label>邮箱：</label><div class="loncom_dis_inline">{{loginInfo.email}}</div>
                                </li>
                            </ul>
                        </div>
                        <a href="javascript:void(0)" slot="reference" @click="enterUserList">
                            <i class="el-icon-user loncom_mr5"></i>
                            <span id="username" style="vertical-align: top;">{{loginInfo.name}}</span>
                        </a>
                    </el-popover>
                </li>
                <li>
                    <a href="javascript:void(0)" @click="powerOff">
                        <i class="el-icon-switch-button"></i>
                    </a>
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
        if(sessionStorage.loginInfo){
            let loginInfo=JSON.parse(sessionStorage.loginInfo);
            this.getDetail(loginInfo.id)
            this.getAlarm();
        }
    },
    mounted() {
      

    },
    computed:{
        ...mapGetters([
            'getChangeUser'
        ]),
    },
    data(){
        return {
            alarmInfo:[{
                alarmkey:"alarmAll",
                value:'0',
            }],
            loginInfo:{
                userid:"",
                name:"",
                phone:'',
                email:'',
            },
        }
    },
    methods:{
        getDetail:function(id){
            this.$api.post('/user/details', {id:id}, r => {
                console.log(r)
                if(r.err_code=="0"){
                    for(var item in this.loginInfo){
                        this.loginInfo[item]=r.data[item];
                    }
                    sessionStorage.loginInfo= JSON.stringify(r.data);
                    store.dispatch('setChangeUser',false);
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
                            Cookies.remove("userid");
                            // let exp = new Date(); 
                            // console.log(exp.setTime(exp.getTime()-1))
                            // exp.setTime(exp.getTime() - 1); 

                            // let cval,arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
                            // if(arr=document.cookie.match(reg)){
                            //     cval= unescape(arr[2]);
                            // }else{
                            //     cval= null;
                            // }
                            // if(cval!=null) 
                            //     document.cookie =" userid="+cval+";expires=" + exp.toGMTString() + ";path=/lottery";
                                
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
        getChangeUser: function(val) { 
            if(val){
                let loginInfo=JSON.parse(sessionStorage.loginInfo);
                this.getDetail(loginInfo.id);
            }
        },
    } 
}
</script>

