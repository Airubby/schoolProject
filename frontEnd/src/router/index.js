import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export const syncRouter=[
  {
    path: '/',
    component: (resolve) => require(['@/views/index'], resolve)
  },{
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve)
  },{
    path: '/site',
    component: (resolve) => require(['@/views/site/index'], resolve),
    redirect:'/site/control',
    children:[
      { path:'/site/control',component:(resolve) => require(['@/views/site/control/index'], resolve),name:'运行监控'},
      { path:'/site/analyze',component:(resolve) => require(['@/views/site/analyze/index'], resolve),name:'运行分析'},    
      { path:'/site/config',component:(resolve) => require(['@/views/site/config/index'], resolve),name:'时区配置'},    
      { path:'/site/account',component:(resolve) => require(['@/views/site/account/index'], resolve),name:'账号管理'},    
      { path:'/site/log',component:(resolve) => require(['@/views/site/log/index'], resolve),name:'系统日志'},    
    ]
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
