import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export const syncRouter=[
  {
    path: '/',
    component: (resolve) => require(['@/views/index'], resolve)
  },{
    path: '/web',
    component: (resolve) => require(['@/views/webIndex'], resolve)
  },{
    path: '/mobile',
    component: (resolve) => require(['@/views/mobileIndex'], resolve)
  },{
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve)
  },{
    path: '/site',
    component: (resolve) => require(['@/views/site/index'], resolve),
    redirect:'/site/control',
    children:[
      { path:'/site/control',component:(resolve) => require(['@/views/site/control/index'], resolve),name:'运行监控'},
      { path:'/site/waterEle',component:(resolve) => require(['@/views/site/waterEle/index'], resolve),name:'水电监控'},
      { path:'/site/alarm',component:(resolve) => require(['@/views/site/alarm/index'], resolve),name:'告警管理'},
      { path:'/site/analyze',component:(resolve) => require(['@/views/site/analyze/index'], resolve),name:'运行分析'}, 
      { path:'/site/analyze/more',component:(resolve) => require(['@/views/site/analyze/more'], resolve),name:'更多分析'},    
      { path:'/site/config',component:(resolve) => require(['@/views/site/config/index'], resolve),name:'时区配置'},    
      { path:'/site/account',component:(resolve) => require(['@/views/site/account/index'], resolve),name:'账号管理'},    
      { path:'/site/log',component:(resolve) => require(['@/views/site/log/index'], resolve),name:'系统日志'},    
    ]
  },
  {
    path: '/msite',
    component: (resolve) => require(['@/views/msite/index'], resolve),
    redirect:'/msite/control',
    children:[
      { path:'/msite/control',component:(resolve) => require(['@/views/msite/control/index'], resolve),name:'手机运行监控'},
      { path:'/msite/alarm',component:(resolve) => require(['@/views/msite/alarm/index'], resolve),name:'手机告警管理'},
      { path:'/msite/analyze',component:(resolve) => require(['@/views/msite/analyze/index'], resolve),name:'手机运行分析'}, 
    ]
  },
  {
    path: '/slide',
    component: (resolve) => require(['@/views/slide/index'], resolve)
  },
  {
    path: '/test8',
    component: (resolve) => require(['@/views/test'], resolve)
  },
  { path: '/404',meta: { title: '404'}, component: () => import('@/views/errorPage/404') },
  { path: '/401',meta: { title: '401'}, component: () => import('@/views/errorPage/401') },
  { path: '*',meta: { title: '404'}, component: () => import('@/views/errorPage/404') },
];

export const asyncRouter=[]

export const router= new Router({
  //mode: 'history',
  mode:'hash',
  base: process.env.BASE_URL,
  routes:syncRouter
})
