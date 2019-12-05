import {router} from '@/router/index'
import { Message } from 'element-ui'
import store from '@/store/index'
// import NProgress from 'nprogress' // Progress 进度条
// import 'nprogress/nprogress.css'// Progress 进度条样式
import { Loading } from 'element-ui'
import request from './api/index'
import tool from './utils/tool'

routerGo();
//定义得写上服务，不然刷新，close()找不到
let loadingInstance=null;

function filterAsyncRouter(url, roles) {
    roles.forEach(element => {
        if(url==element.path){
            store.dispatch('setLimits',element.meta.limits.split(","));
            return;
        }
        if(element.children&&element.children.length>0){
            filterAsyncRouter(url,element.children);
        }
    });
}
function getInfo(){  //刷新页面重新获取权限
    loadingInstance.close();
    return new Promise(function(resolve, reject){
        let roleid=(tool.Decrypt(sessionStorage.energyInfo)&&tool.Decrypt(sessionStorage.energyInfo).split("_").length>1)?tool.Decrypt(sessionStorage.energyInfo).split("_")[0]:""
        request.get('/role/rolequery',{id:roleid},res=>{
            if(res.err_code=="0"){
                if(res.data.length>0){
                    store.dispatch('setAuthInfo',res.data);
                    let url=window.document.URL.split("#")[1];
                    filterAsyncRouter(url,res.data);
                }else{
                    console.log("没有任何权限，跳转到没有任何权限的页面")
                    router.push({path:'/login'});
                }
            }else{
                Message.warning("权限获取失败");
            }
            resolve()
        })
    })
}

async function routerGo(){
    loadingInstance=Loading.service({text:"拼命加载中",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.8)"})
    if(sessionStorage.energyInfo){
        await getInfo();
    }
    router.beforeEach((to, from, next) => {
        loadingInstance = Loading.service({text:"拼命加载中",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.8)"});
        // NProgress.start()
        const whiteList = ['/login','/401','/404','/slide'] // 不重定向白名单
        // let token=store.getters.token;
        if(sessionStorage.energyInfo){
            if (to.path!=="/"&&whiteList.indexOf(to.path) !== -1) {
                next()
            } else {
                if(JSON.stringify(to.meta)!="{}"){
                    if(to.meta.show&&to.meta.show=="true"){
                        if(to.meta.limits&&to.meta.limits!=""){
                            store.dispatch('setLimits',to.meta.limits.split(",")).then(()=>{
                                next();
                            });
                        }else{
                            next();
                        }
                    }else{
                        next('/401') 
                        console.log("没有权限访问")
                    }
                }else{
                    next('/404') 
                    console.log("访问的页面不存在")
                }
            }
            
        }else{
            if (to.path!=="/"&&whiteList.indexOf(to.path) !== -1) {
                next()
            } else {
                next('/login')
            }
        }
    
    })
    
    router.afterEach((to,from) => {
        let title=to.meta.title?`${to.meta.title}`:'广州市市政职业学校能耗管理系统';
        window.document.title = title;
        loadingInstance&&loadingInstance.close();
        // NProgress.done() // 结束Progress
    })
}

