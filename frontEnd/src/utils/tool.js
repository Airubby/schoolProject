import echarts from 'echarts'
import Vue from 'vue'
import store from '../store/index'
import CryptoJS from 'crypto-js/crypto-js'


// 默认的 KEY 与 iv 如果没有给
const KEY = "loncom";//""中与后台一样  密码
const keySize=128;
const fillKey = (key) => { 
    const filledKey = Buffer.alloc(keySize / 8); 
    const keys = Buffer.from(key); 
    if (keys.length < filledKey.length) { 
        filledKey.map((b, i) => filledKey[i] = keys[i]); 
    }
    return filledKey; 
}
/**
 * AES加密 ：字符串 key iv  返回base64
 */
function Encrypt(word, keyStr) {
    let key = keyStr ? CryptoJS.enc.Utf8.parse(fillKey(keyStr)):CryptoJS.enc.Utf8.parse(fillKey(KEY));
    let srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {
        mode: CryptoJS.mode.ECB,  //mode 为ECB  不需要iv
        padding: CryptoJS.pad.Pkcs7
    });
    // console.log("-=-=-=-", encrypted.ciphertext)
    return CryptoJS.enc.Base64.stringify(encrypted.ciphertext);
}

/**
 * AES 解密 ：字符串 key iv  返回base64
 *
 */
function Decrypt(word, keyStr, ivStr) {
    let key = keyStr ? CryptoJS.enc.Utf8.parse(fillKey(keyStr)):CryptoJS.enc.Utf8.parse(fillKey(KEY));
    let base64 = CryptoJS.enc.Base64.parse(word);
    let src = CryptoJS.enc.Base64.stringify(base64);
    var decrypt = CryptoJS.AES.decrypt(src, key, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    var decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
    return decryptedStr.toString();
}

function Format(fmt,value){
  let date=value?new Date(value):new Date();
  let o = {
      "M+": date.getMonth() + 1, //月份 
      "d+": date.getDate(), //日 
      "h+": date.getHours(), //小时 
      "m+": date.getMinutes(), //分 
      "s+": date.getSeconds(), //秒 
      "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
      "S": date.getMilliseconds() //毫秒 
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (let k in o)
      if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}

function arrayContains(v,arr){
  if(arr.indexOf(v)==-1){
    return false;
  }else{
    return true;
  }
}
//全屏切换
var isFullScreen=false;//是否是全屏状态
function switcFullScreen(){
    if(isFullScreen){//是全屏就退出全屏
        if(document.exitFullscreen) {
            document.exitFullscreen();
        } else if(document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if(document.webkitExitFullscreen) {
            document.webkitExitFullscreen();
        }
    }else{//不是就全屏
        var element=document.documentElement;
        if(element.requestFullscreen) {
            element.requestFullscreen();
        } else if(element.mozRequestFullScreen) {
            element.mozRequestFullScreen();
        } else if(element.webkitRequestFullscreen) {
            element.webkitRequestFullscreen();
        } else if(element.msRequestFullscreen) {
            element.msRequestFullscreen();
        }
    }
    isFullScreen=!isFullScreen;
}

function annulus(ID,xData,yData){
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById(ID))
    // 绘制图表
    let option = {
        color:["#FF3F3E","#E79627","#DDD437","#4C78FF"],
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'center',
            y:'bottom',
            data:xData,
            formatter: function(name) {
                var index = 0;
                var clientlabels = xData;
                clientlabels.forEach(function(value,i){
                    if(value == name){
                        index = i;
                    }
                });
                return name + "  " + yData[index].value;
            },
            textStyle:{
                color:'#fff'
            }
        },
        series: [
            {
                name:'能效概况',
                type:'pie',
                radius: ['50%', '70%'],
                center: ['50%', '32%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:yData
            }
        ]
    };
    myChart.setOption(option, true);
    return myChart;
}

function allannulus(ID,title,data,xData,yData,all){
    // var all=345;
    // var data=[
    //     {value:335, name:'教室'},
    //     {value:310, name:'办公室'}
    // ];
    // var xData=['教室','办公室'];
    // var yData=['30%','46%'];
    var myChart = echarts.init(document.getElementById(ID));
    var option = {
        color:["#6D4AFF","#2AA857","#DDAF34","#4A78FF"],
        title:{
            text:title,  
            left:10,
            top:5,
            textStyle:{
                fontSize:16,
                fontWeight:"normal",
                color:"#F5F6FA",
            },
        },
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            y:'center',
            data:xData,
            formatter: function(name) {
                var index = 0;
                var clientlabels = xData;
                var clientcounts = yData;
                clientlabels.forEach(function(value,i){
                    if(value == name){
                        index = i;
                    }
                });
                return name + "  " + clientcounts[index];
            },
            textStyle:{
                color:'#fff'
            }
        },
        series: [
            {
                name:"",
                type:'pie',
                radius: ['50%', '70%'],
                center: ['70%', '60%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: true,
                        position: 'center',
                        formatter:'{num|'+all+'}\r\n{text|总能耗}',
                        rich: {
                            num: {
                                fontSize: 26,
                                color:'#B9C6F2'
                            },
                            text: {
                                color: '#B9C6F2',
                                fontSize: 14,
                                padding: [10, 0],
                                borderRadius: 2
                            }
                        },
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:data
            }
        ]
    };

    myChart.setOption(option, true);
    myChart.on('click', function (param) {
        console.log(param)
            var index = param.dataIndex;
    }); 
    return myChart; 
}

function lineChar(ID,xData,yData,type){
    // let xData=["2018-10-11 09:00:11", "2018-10-11 09:03", "2018-10-11 09:13", "2018-10-11 09:14",
    //  "2018-10-11 09:24","2018-10-11 09:34","2018-10-11 09:44","2018-10-11 09:54","2018-10-11 10:04","2018-10-11 10:14","2018-10-11 10:24"];
    // let yData=[320, 232, 301, 234, 390, 230, 310,18, 391, 234, 290, 343, 310];
    // type=day,week,month,year
    let myChart = echarts.init(document.getElementById(ID));
    let option = {
        color:["#4A78FF"],
         title: {
            text: '用电量',
            top:0,
            left:30,
            textStyle:{
                color:"#B9C6F2",
                verticalAlign:'top',
                fontWeight:'normal',
                fontSize:'12'
            }
        },
        tooltip : {
            trigger: 'axis'
        },
        // legend: {
        //     bottom: 10,
        //     textStyle:{
        //         color:"#B9C6F2"
        //     },
        //     data:["用电量"]
        // },
        grid: {
            left: '45px',
            right: '45px',
            top:'15%',
            bottom: '15%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                axisLine:{
                    lineStyle:{
                        color:"#2A2F4E",
                        width:1,
                    },
                },
                axisTick:{
                    show:false
                },
                axisLabel:{
                    color:"#B9C6F2",
                    formatter: function (value, index) {
                        // 格式化成月/日，只在第一个刻度显示年份
                        if(type=="day"){
                            return Format('hh:mm',value);
                        }else if(type=="year"){
                            return Format('MM-dd',value);
                        }else{
                            return Format('MM-dd hh:mm',value);
                        }
                        
                    }
                },                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
                data :xData
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitLine:{
                    show:false,
                },
                axisLine:{
                    lineStyle:{
                        color:"#2A2F4E",
                        width:1,
                    }
                },
                splitLine:{
                    show:true,
                    lineStyle:{
                        color:"#1c3042",
                        width:1,
                        type:"dashed",
                    }
                },
                axisTick:{
                    show:false
                },
                axisLabel:{
                    color:"#B9C6F2",
					formatter: '{value}'
                }
            }
        ],
        series : [
            {
               name:'用电量',
               type:'line',
               smooth:"true", //平滑
               symbolSize:'40px',
               
                data:yData
           },
       ]
    };
    myChart.setOption(option, true);
    return myChart; 
}
function showWeek(week){
    let show=week.map(function(item,index){
        if(item=="1"){
            return '周一';
        }else if(item=="2"){
            return '周二';
        }else if(item=="3"){
            return '周三';
        }else if(item=="4"){
            return '周四';
        }else if(item=="5"){
            return '周五';
        }else if(item=="6"){
            return '周六';
        }else if(item=="7"){
            return '周日';
        }
    })
    return show;
}
function barChar(ID,title,xData,yData,color,rotate1){
    // let xData=['温湿度13','温湿度12','温湿度11','温湿度10','温湿度09','温湿度08','温湿度07','温湿度06','温湿度05','温湿度04'];
    // let yData=[10, 8, 14, 34, 29, 33, 45,30,25,20];
    // let color="#f00"
    let rotate=rotate1?rotate1:0;
    let myChart = echarts.init(document.getElementById(ID));
    let option = {
        color: ['#3398DB'],
        title:{
            text:title,  
            left:10,
            top:5,
            textStyle:{
                fontSize:16,
                fontWeight:"normal",
                color:"#F5F6FA",
            },
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter:'{b}：{c}'
        },
        grid: {
            top:'50px',
            bottom: '0px',
            left:'15px',
            right:'15px',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : xData,
                axisTick:{
                    show:false
                },
                axisLabel:{
                    color:"#8691BA"
                },
                splitLine:{
                    show:false,
                },
                axisLine:{
                    lineStyle:{
                        color:"#2B3151",
                        width:1,
                    }
                },
                axisLabel:{
                    interval:0,
                    rotate:rotate,
                    color: "#8691BA"
                },
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel:{
                    color:"#8691BA"
                },
                axisTick:{
                    show:false
                },
                splitLine:{
                    show:true,
                    lineStyle:{
                        color:"#2B3151",
                        width:1,
                    }
                },
                axisLine:{
                    lineStyle:{
                        color:"#2B3151",
                        width:1,
                    }
                },
            }
        ],
        series : [
            {
                type:'bar',
                barWidth: '35%',
                itemStyle:{
                    normal:{
                        color: color
                    }
                },
                data:yData
            }
        ]
    };
    myChart.setOption(option, true);
    return myChart; 
}


function wsConnection(sendMsg, callback) {
  try {
      //var SOCKECT_ADDR = "ws://" + url +":"+ port;
      let host=window.document.location.host;
      let SOCKECT_ADDR="ws://"+host+store.getters.webSocket;
    //   let SOCKECT_ADDR="ws://127.0.0.1:8090"+store.getters.webSocket;
      let ws = new WebSocket(SOCKECT_ADDR);
      Vue.prototype.$ws=ws;
      ws.onopen = function (event) {
          console.log(event)
          console.log("已经与服务器建立了连接\r\n当前连接状态：" + event);
          ws.send(sendMsg);
      };
    
      ws.onmessage = callback;
      ws.onclose = function (event) {
        console.log(event)
      };
      ws.onerror = function (event) {
        console.log("WebSocket异常！" + event.toString());
      };
      Vue.prototype.$ws=ws;

  } catch (ex) {
      console.log(ex);
  }
}
function checkPORT(obj) {
    console.log(obj);
    if (!obj.value) {
        if(obj.rules.required){
            obj.callback(new Error('不能为空'))
        }else{
            obj.callback()
        }
    } else if (Math.round(Number(obj.value)) !== Number(obj.value)) {
        obj.callback(new Error('必须为整数数字'))
    } else {
        if(Number(obj.value)>65535||Number(obj.value)<1){
            obj.callback("端口范围在1-65535之间")
        }else{
            obj.callback()
        }
    }
}
function checkIP(obj) {
    if (!obj.value) {
        if(obj.rules.required){
            obj.callback(new Error('不能为空'))
        }else{
            obj.callback()
        }
    } else {
      var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
        if(reg.test(obj.value)){
            obj.callback()
        }else{
            obj.callback("ip格式错误")
        }
    }
    
}
function checkEMAIL(obj) {
    if (!obj.value) {
        if(obj.rules.required){
            obj.callback(new Error('不能为空'))
        }else{
            obj.callback()
        }
    } else {
      var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        if(reg.test(obj.value)){
            obj.callback()
        }else{
            obj.callback("邮箱格式错误")
        }
    }
    
}
function checkPHONE(obj) {
    if (!obj.value) {
        if(obj.rules.required){
            obj.callback(new Error('不能为空'))
        }else{
            obj.callback()
        }
    } else {
      var reg = /^1[345789]\d{9}$/
        if(reg.test(obj.value)){
            obj.callback()
        }else{
            obj.callback("手机格式错误")
        }
    }
    
}
function checkNumber(obj) {
    if (!obj.value) {
        if(obj.rules.required){
            obj.callback(new Error('不能为空'))
        }else{
            obj.callback()
        }
    } else {
        let regPos = /^\d+(\.\d+)?$/; //非负浮点数
        //let regPos = /^([1-9]+)$/;  //大于0的正整数
        // let regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
        if(regPos.test(obj.value)){
            obj.callback()
        }else{
            obj.callback('请输入正数数值')
        }
    }
    
}
export default {
    Encrypt,
    Decrypt,
    switcFullScreen,
    arrayContains,
    Format,
    annulus,
    allannulus,
    lineChar,
    barChar,
    showWeek,
    wsConnection,
    checkPORT,
    checkIP,
    checkEMAIL,
    checkPHONE,
    checkNumber
}