import webSocket from '@/components/webSocket.vue'
import propver from '../components/propver.vue'
import otherpropver from '../components/otherpropver.vue'
export default {
    components:{webSocket,propver,otherpropver},
    created () {
        
    },
    mounted() {
        document.querySelector('#mapimg').addEventListener('load',  () =>{
            this.adjust();
        })
        let timeout = null;//onresize触发次数过多，设置定时器
        window.onresize = ()=> {  
            clearTimeout(timeout);  
            timeout = setTimeout( () =>{ 
                this.adjust(); 
            }, 100);//页面大小变化，重新加载页面以刷新MAP  
        } 
    },
    data() {
        return {
            loading:false,
            infoData:[],
        }
    },
    methods:{
        setValue:function(){
            for(let i=0;i<this.floorData.length;i++){
                for(let j=0;j<this.infoData.length;j++){
                    if(this.floorData[i].code==this.infoData[j].code){
                        this.floorData[i]=Object.assign(this.floorData[i],this.infoData[j]);
                    }
                }
            }
        },
        adjust:function(){
            let maplist=document.getElementById("map").childNodes;
            let mapimg=document.getElementById("mapimg");
            let centermap=document.getElementById("centermap");
            let domWidth=centermap.offsetWidth;
            let domHeight=centermap.offsetHeight;
            let img=new Image();
            img.src=mapimg.getAttribute("src");
            let width=img.width;
            let height=img.height;

            let xrate=domWidth/width;
            let yrate=domHeight/height;

            for(let i=0;i<maplist.length;i++){
                let oldCoords = maplist[i].getAttribute("coords"); //定义一个初始化的coords 改变浏览器用这个值去计算
                let newcoords = this.adjustPosition(oldCoords,xrate,yrate); 
                maplist[i].style.left=parseFloat(newcoords[0])+"px";
                maplist[i].style.top=parseFloat(newcoords[1])+"px";
                if(newcoords.length>4){  //1栋311,704,806；2栋603-604有异形
                    let floor=maplist[i].getAttribute("floor");
                    if(floor=='A'){
                        maplist[i].querySelector(".maparea").style.width=parseFloat(newcoords[8])-parseFloat(newcoords[0])+"px";
                        maplist[i].querySelector(".maparea").style.height=parseFloat(newcoords[9])-parseFloat(newcoords[1])+"px";
                        maplist[i].querySelector(".othermap-top").style.width=parseFloat(newcoords[2])-parseFloat(newcoords[0])+"px";
                        maplist[i].querySelector(".othermap-top").style.height=parseFloat(newcoords[5])-parseFloat(newcoords[1])+"px";
                        maplist[i].querySelector(".othermap-bottom").style.width=parseFloat(newcoords[6])-parseFloat(newcoords[0])+"px";
                        maplist[i].querySelector(".othermap-bottom").style.height=parseFloat(newcoords[9])-parseFloat(newcoords[7])+"px";
                    }
                }else{
                    maplist[i].querySelector(".maparea").style.width=parseFloat(newcoords[2])-parseFloat(newcoords[0])+"px";
                    maplist[i].querySelector(".maparea").style.height=parseFloat(newcoords[3])-parseFloat(newcoords[1])+"px";
                }
            }
        },
        adjustPosition:function(position,xrate,yrate){
            let each = position.split(",");  
            for (let i = 0; i < each.length; i++) {
                let rate=xrate; 
                if(i%2==1){
                    rate=yrate;
                }
                each[i] = Math.round(parseFloat(each[i]) * rate).toString();//坐标  
            }  
            return each;  
        },
    },
    watch: {
        info:{
            handler:function(val){
                this.infoData=val.data.list?val.data.list:[];
                this.setValue();
            },
            deep: true
        }
    },
 }