import Cookies from 'js-cookie'
import {syncRouter, resetRouter,router } from '@/router/index'

/**
 * 递归过滤异步路由表，生成router数据结构
 * @param asyncRouterMap
 * @param data
 */
function filterAsyncRouter(data) {
  data.map((item)=>{
      let addr=item.component;
      item.component = () => import(`@/views/${addr}`);
      if(item.children&&item.children.length>0){
          item["redirect"]=item.children[0].path;
          item.children=filterAsyncRouter(item.children);
      }
  })
  return data;
}


const app = {
  state: {
    wsData:{},
    AjaxUrl:'',
    userInfo:{
      userid:"",
      name:"",
      phone:'',
      email:'',
    },
    webSocket:'',
    devtype:false,  //默认false 为web端
    routers: syncRouter,
    addRouters: [],
    limits:[],
    navList:[],
  },
  mutations: {
    setwsData(state,wsData){
        state.wsData=wsData;
    },
    setAjaxUrl(state,ajaxUrl){
        state.AjaxUrl=ajaxUrl;
    },
    setWebSocketUrl(state,webSocket){
      state.webSocket=webSocket;
    },
    setChangeUser(state,data){
      state.userInfo=data;
    },
    setDevType(state,flag){
      state.devtype=flag;
    },
    setAuthInfo(state,data){
        resetRouter(); //重置初始路由
        state.navList=data; //导航用
        let redirect=data.length>0?data[0].path:'/401';
        let newRouter={
            path: '/site',
            name:'site',
            meta: { title: '首页'},
            component: () => import('@/views/site/index.vue'),
            redirect:redirect,
            children:[]
        }
        if(data.length>0){
          let theAsyncRouter = filterAsyncRouter(data);
          if(theAsyncRouter){
              newRouter.redirect=theAsyncRouter[0].path;
              newRouter.children=theAsyncRouter;
          }
        }
        
        // router.options.routes.push(newRouter)
        router.addRoutes([newRouter]);
    },
    setLimits(state,data){
      state.limits=data;
    }
  },
  actions: {
    setwsData({commit},wsData){
        commit('setwsData',wsData)
    },
    setAjaxUrl({commit},ajaxUrl){
        commit('setAjaxUrl',ajaxUrl)
    },
    setWebSocketUrl({commit},webSocket){
      commit('setWebSocketUrl',webSocket)
    },
    setChangeUser({commit},data){
      commit('setChangeUser',data)
    },
    setDevType({commit},flag){
      commit('setDevType',flag)
    },
    //设置获取的权限信息
    setAuthInfo({commit},data){
      commit('setAuthInfo',data)
    },
    //设置功能权限
    setLimits({commit},data){
      commit('setLimits',data);
    }
  }

}

export default app
