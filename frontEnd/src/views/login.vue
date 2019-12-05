<template>
    <div class="loncom_login">
        <div class="loncom_login_con loncom_public_shadow" :class="{'mlogin_con':$store.getters.getDevType}">
            <div class="loncom_logo_img"></div>
            <div class="loncom_logo_text">元岗校区能效管理系统</div>
            <div class="loncom_login_input">
				<el-form :model="user" :rules="rules" ref="form" status-icon>
                    <el-form-item prop="userid" class="loncom_user">
                        <el-input v-model.trim="user.userid" placeholder="请输入账号"></el-input>
                    </el-form-item>
                    <el-form-item prop="psword" class="loncom_pass">
                        <el-input type="password" v-model.trim="user.psword" ref="psinput" placeholder="请输入密码" @keyup.native="keyLogin($event,'userName')"></el-input>
                    </el-form-item>
                    <el-button type="primary" @click="loginIn()" @keydown="keyLogin($event)">登录</el-button>
                </el-form>
            </div>
        </div>
        
    </div>
</template>

<script>
export default {
	created () {
			
	},
	data(){
		let checkpassword = (rule, value, callback) => {
			if (value === '') {
				this.user.psword="";
				this.$nextTick(() => {
					callback(new Error('请输入密码'));
				})
			} else {
				callback();
			}
		};
		return {
			user:{
				userid:"",
				psword:""
			},
			rules: {
				userid:[
					{ required: true, message: '请输入账号', trigger: 'blur' },
				],
				psword:[
					{ required: true,  trigger: 'blur' ,validator:checkpassword},
				]
			}
		}
	},
	methods:{
		
		keyLogin:function(ev){
			if(ev.keyCode == 13){
				this.loginIn();
			}
		},
		loginIn:function(){
			this.$refs['form'].validate((valid) => {
				if(valid){
					this.user.psword=this.$tool.Encrypt(this.user.psword);
					console.log(this.user.psword)
					this.$api.post('/user/login', {"obj":this.user}, r => {
						console.log(r);
						if(r.err_code=="0"){
							this.$message.success(r.err_msg);
							  //刷新页面的时候用roleid获取权限问题； //获取详情用
							sessionStorage.energyInfo=this.$tool.Encrypt(r.data.roleid+"_"+r.data.id);
							this.getLimit(r.data.roleid)  //获取权限
							var date=new Date();
							var expiresDays=30000;
							// 将date设置为30000天以后的时间
							date.setTime(date.getTime()+expiresDays*24*3600*1000);
							// 将userId和userName两个cookie设置为10天后过期
							document.cookie=" userid="+this.user.userid+"; expires="+date.toGMTString();
							// this.$router.push({path:'/'});
						}else{
							this.user.psword="";
							this.$refs.psinput.focus();
							this.$message.error(r.err_msg);
						}
					});
				}
			});
		},
		filterAsyncRouter:function(url, roles) {
			roles.forEach(element => {
				if(url==element.path){
					this.$store.dispatch('setLimits',element.meta.limits.split(","));
					return;
				}
				if(element.children&&element.children.length>0){
					this.filterAsyncRouter(url,element.children);
				}
			});
		},
		getLimit:function(id){
			this.$api.post('/role/rolequery', {id:id}, r => {
				console.log(r);
				this.loading=false;
				if(r.err_code=="0"){
					if(r.data.length>0){
						this.$store.dispatch('setAuthInfo',r.data);
						// let url=window.document.URL.split("#")[1];
						// this.filterAsyncRouter(url,r.data);
						this.$router.push({path:'/'});
					}else{
						console.log("没有任何权限，跳转到没有任何权限的页面")
						this.$router.push({path:'/login'});
					}
				}else{
					this.$message.error(r.err_msg);
				}
			});
		},
		
	},
	watch:{
			
	},
}
</script>

<style scoped lang="less">
	
</style>