import Vue from 'vue';
const TYPE_MAP = {
  'Analogy': '数字量',
  'Digital': '状态量',
  'CommStatus':'通讯量'
};
const ALARM_MAP={
  '0':'正常',
  '1':'告警'
};
const ONOFF_MAP={
  '0':'停用',
  '1':'启用',
}
const STATE_MAP={
  '0':'维护',
  '1':'启用',
  '2':'过期',
}
Vue.filter('typeFilter', function(s) {
  if (!s) return ''
  return TYPE_MAP[s];
})
Vue.filter('alarmFilter', function(s) {
  if (s==''||s==null) return ALARM_MAP['0'];
  return ALARM_MAP[s];
})
Vue.filter('onoffFilter', function(s) {
  if (s==''||s==null) return ONOFF_MAP['0'];
  return ONOFF_MAP[s];
})
Vue.filter('stateFilter', function(s) {
  if (s==''||s==null) return STATE_MAP['2'];
  return STATE_MAP[s];
})

//账号管理，管理域用
Vue.filter('idToNameFilter', function(s,v) {
  var arr=s?s.split(','):[];
  var name=[];
  for(var i=0;i<arr.length;i++){
    for(var j=0;j<v.length;j++){
      if(arr[i]==v[j].id){
        name.push(v[j].name);
      }
    }
  }
  return name.toString();
})






