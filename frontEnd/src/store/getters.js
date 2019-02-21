const getters = {
  getWSData: state => state.app.wsData,
  AjaxUrl: state => state.app.AjaxUrl,
  getChangeUser: state=>state.app.changeUser,
  webSocket: state=>state.app.webSocket,
}
export default getters
