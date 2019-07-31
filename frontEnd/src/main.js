
import Vue from 'vue'
import App from './App.vue'
import {router} from './router/index'
import store from './store/index'
import axios from 'axios'
import ElementUI from 'element-ui'
import './assets/element-#4A78FF/index.css'
import ElSearchTablePagination from 'el-table-pagination'
import './utils/directive'  //element弹窗拖拽
import lonTop from './components/top'
import './utils/filters' // 自定义过滤器
import tool from './utils/tool'  //工具函数
import 'promise-polyfill'  //兼容低版本浏览器   import 'babel-polyfill'
import 'vue-transition.css'
// import './utils/mock.js'
import Swiper from 'swiper'
import 'swiper/dist/css/swiper.min.css'
import api from './api/index.js'
import './assets/css/index.less'

// 将API方法绑定到全局
Vue.prototype.$api = api
//绑定工具函数到全局
Vue.prototype.$tool = tool
Vue.prototype.$Swiper = Swiper

Vue.use(ElementUI)
Vue.use(ElSearchTablePagination)
Vue.use(lonTop)

Vue.config.productionTip = false

function getServerConfig () {
  return new Promise ((resolve, reject) => {
    axios.get('./serverConfig.json').then((result) => {
      let config = result.data;
      let ajaxUrl = process.env.NODE_ENV == 'production' ? config.production:config.develop;
      Vue.prototype.$ajaxUrl=ajaxUrl;
      store.dispatch('setAjaxUrl',ajaxUrl);
      store.dispatch('setWebSocketUrl',config.webSocket);
      resolve();
    }).catch((error) => {
      console.log(error)
      reject()
    })
  })
}

async function init() {
  await getServerConfig();
  new Vue({
    router,
    store,
    render: h => h(App),
  }).$mount('#app')
}
init();
