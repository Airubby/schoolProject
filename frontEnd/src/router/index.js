import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export const syncRouter=[
  {
    path: '/',
    meta: { title: 'home',show:"true"},
    component: (resolve) => require(['@/views/index'], resolve),
  },
  // {
  //   path: '/web',
  //   component: (resolve) => require(['@/views/webIndex'], resolve)
  // },{
  //   path: '/mobile',
  //   component: (resolve) => require(['@/views/mobileIndex'], resolve)
  // },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve)
  },
  // {
  //   path: '/site',
  //   meta: { title: '404',show:"true"},
  //   component: (resolve) => require(['@/views/site/index'], resolve),
  //   redirect:'/site/control',
  //   children:[
  //     { path:'/site/control',meta: { title: '运行监控',show:"true"},component:(resolve) => require(['@/views/site/control/index'], resolve),name:'control'},
  //     { path:'/site/waterEle',meta: { title: '水电监控',show:"true"},component:(resolve) => require(['@/views/site/waterEle/index'], resolve),name:'waterEle'},
  //     { path:'/site/alarm',meta: { title: '超限告警',show:"true"},component:(resolve) => require(['@/views/site/alarm/index'], resolve),name:'alarm'},
  //     { path:'/site/analyze',meta: { title: '运行分析',show:"true"},component:(resolve) => require(['@/views/site/analyze/index'], resolve),name:'analyze'}, 
  //     { path:'/site/analyze/more',meta: { title: '更多分析',show:"true"},component:(resolve) => require(['@/views/site/analyze/more'], resolve),name:'analyzemore'},    
  //     { path:'/site/config',meta: { title: '时区配置',show:"true"},component:(resolve) => require(['@/views/site/config/index'], resolve),name:'config'},    
  //     { path:'/site/account',meta: { title: '账号管理',show:"true"},component:(resolve) => require(['@/views/site/account/index'], resolve),name:'account'},   
  //     { path:'/site/role',meta: { title: '角色管理',show:"true"},component:(resolve) => require(['@/views/site/role/index'], resolve),name:'role'},    
  //     { path:'/site/log',meta: { title: '系统日志',show:"true"},component:(resolve) => require(['@/views/site/log/index'], resolve),name:'log'},    
  //   ]
  // },
  // {
  //   path: '/msite',
  //   component: (resolve) => require(['@/views/msite/index'], resolve),
  //   redirect:'/msite/control',
  //   children:[
  //     { path:'/msite/control',component:(resolve) => require(['@/views/msite/control/index'], resolve),name:'手机运行监控'},
  //     { path:'/msite/alarm',component:(resolve) => require(['@/views/msite/alarm/index'], resolve),name:'手机告警管理'},
  //     { path:'/msite/analyze',component:(resolve) => require(['@/views/msite/analyze/index'], resolve),name:'手机运行分析'}, 
  //   ]
  // },
  {
    path: '/slide',
    meta: { title: 'slide',show:"true"},
    component: (resolve) => require(['@/views/slide/index'], resolve)
  },
  {
    path:"/view",
    meta:{title:'view',show:"true"},
    component: (resolve) => require(['@/views/home/showIndex'], resolve)
  },
  {
    path: '/test8',
    component: (resolve) => require(['@/views/test'], resolve)
  },
  { path: '/404',meta: { title: '404'}, component: () => import('@/views/errorPage/404') },
  { path: '/401',meta: { title: '401'}, component: () => import('@/views/errorPage/401') },
];

const createRouter = () => new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: syncRouter
})

export function resetRouter () {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher
}
export const router = createRouter()