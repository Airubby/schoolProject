<template>
    <div class="loncom_index_body_top">
        <router-link to="/" class="loncom_logo">
            <img src="~@/assets/images/logo.png" alt="">
        </router-link>
        <div class="loncom_index_top_right">
            <ul>
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
                            <i class="fa fa-user-circle-o loncom_mr5"></i>
                            <span id="username" style="vertical-align: top;">{{loginInfo.name}}</span>
                        </a>
                    </el-popover>
                </li>
                <li>
                    <a href="javascript:void(0)" @click="switcFullScreen">
                        <i class="fa fa-arrows-alt"></i>
                    </a>
                </li>
                <!--
                <li>
                    <a href="javascript:void(0)" @click="switcFullScreen">
                        <el-badge :value="200" :max="99" class="item">
                            <i class="fa fa-bell-o"></i>
                        </el-badge>
                    </a>
                </li>
                -->
                <li>
                    <a href="javascript:void(0)" @click="powerOff">
                        <i class="fa fa-power-off"></i>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import store from '@/store'
export default {
    name:'lonTop',
    created () {
        if(sessionStorage.loginInfo){
            let loginInfo=JSON.parse(sessionStorage.loginInfo);
            this.getDetail(loginInfo.id)
        }
        console.log(this.loginInfo)
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
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        switcFullScreen:function(){
            this.$tool.switcFullScreen();
        },
        //退出登录
        powerOff:function(){
            this.$confirm("退出系统?", '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    this.$api.get('/user/out', {}, r => {
                        if(r.err_code=="0"){
                            sessionStorage.clear();
                            this.$router.push({path:'/login'});
                        }
                    });

            });
        },
        enterUserList:function(){
            this.$router.push({path:'/site/account'});
        },

    }, 
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

