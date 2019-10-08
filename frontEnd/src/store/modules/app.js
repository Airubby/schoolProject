import Cookies from 'js-cookie'

const app = {
  state: {
    wsData:{},
    AjaxUrl:'',
    changeUser:'',
    webSocket:'',
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
    setChangeUser(state,changeUser){
      state.changeUser=changeUser;
    },
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
    setChangeUser({commit},flag){
      commit('setChangeUser',flag)
    },
  }

}

export default app
