<template>
    <div class="loncom_login">
        <div class="loncom_login_con loncom_public_shadow" :class="{'mlogin_con':devtype}">
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
		if(/Android|webOS|iPhone|iPad|BlackBerry/i.test(navigator.userAgent)){
            this.devtype=true;
        }else{
            this.devtype=false;
        }
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
		  devtype:false,
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
							sessionStorage.loginInfo= JSON.stringify(r.data);

							var date=new Date();
							var expiresDays=30000;
							// 将date设置为30000天以后的时间
							date.setTime(date.getTime()+expiresDays*24*3600*1000);
							// 将userId和userName两个cookie设置为10天后过期
							document.cookie=" userid="+this.user.userid+"; expires="+date.toGMTString();
							this.$router.push({path:'/'});
						}else{
							this.$refs.psinput.focus();
							this.$message.error(r.err_msg);
						}
					});
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