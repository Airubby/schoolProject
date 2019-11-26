const getters = {
  getWSData: state => state.app.wsData,
  AjaxUrl: state => state.app.AjaxUrl,
  userInfo: state=>state.app.userInfo,
  webSocket: state=>state.app.webSocket,
  getDevType: state => state.app.devtype,
  addRouters: state => state.app.addRouters,
  limits:state=>state.app.limits,
  navList: state => state.app.navList,
}
export default getters
