<template>
    <span></span>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
    name:'webSocket',
    created () {
        
    },
    mounted() {
        // let _this=this;  // 监听的话，刷新就没法send数据了
        // this.$ws.addEventListener('open', function (event) {
        //     //_this.$ws.send(_this.sendInfo)
        // });
        //推送数据回来，不用向vuex中写了,但是如果一个组件应用了两个webSocket组件时，就有问题，还是得存vuex中监听
        // this.$ws.onmessage = function(event) {
        //     console.log(event)
        //     // let result = JSON.parse(event.data);
        //     let result = eval(event.data);
        //     for(let i=0;i<result.length;i++){
        //         for(let j=0;j<_this.wsInfo.length;j++){
        //             if(result[i].matchID===_this.wsInfo[j].matchID){
        //                 _this.wsInfo[j].value=result[i].value;
        //             }
        //         }
        //     }
        // };

        if(this.sendInfo){
            this.send();
        }

    },
    computed:{
        ...mapGetters([
            'getWSData'
        ]),
    },
    data() {
        return {
            
       }
    },
    methods:{
       send:function(){
            let _this=this;
            if(this.$ws.readyState==1){
                console.log(JSON.stringify(this.sendInfo))
                this.$ws.send(JSON.stringify(this.sendInfo))
            }else{
                setTimeout(function(){
                    _this.send();
                },500)
            }
            
        }
    },
    watch:{
        sendInfo:function(val){
            if(val){
                this.send();
            }
        },
        getWSData: function(val) { 
            if(val&&val.length>0){
                for(var i=0;i<val.length;i++){
                    // console.log(val[i].key+"-------"+val[i].value);
                    for(var j=0;j<this.wsInfo.length;j++){
                        if(val[i].key===this.wsInfo[j].airstate_key){  //空调
                            this.wsInfo[j].airdev=val[i].value;
                            this.wsInfo[j].air=(val[i].value=="0"||val[i].value==""||val[i].value==0)?false:true;
                        }
                        if(val[i].key===this.wsInfo[j].lampstate_key){  //照明
                            this.wsInfo[j].lampdev=val[i].value;
                            this.wsInfo[j].lamp=(val[i].value=="0"||val[i].value==""||val[i].value==0)?false:true;
                        }
                        if(val[i].key===this.wsInfo[j].linestate_key){ //红外有无人
                            this.wsInfo[j].infrareddev=val[i].value;
                        }
                        if(val[i].key===this.wsInfo[j].temp_key){  //环境温度
                            this.wsInfo[j].humituredev=val[i].value;
                        }
                        if(this.wsInfo[j].roompower_num>1){  //多个电表的实时功率
                            for(let k=0;k<this.wsInfo[j].roompower_num;k++){
                                if(val[i].key===this.wsInfo[j]["roompower_key"+k]){
                                    let value=val[i].value?val[i].value:0;
                                    this.wsInfo[j]["roompower"+k]=value;
                                    let roompower=0;
                                    for(let m=0;m<this.wsInfo[j].roompower_num;m++){
                                        let pval=this.wsInfo[j]["roompower"+m]?this.wsInfo[j]["roompower"+m]:0;
                                        roompower+=parseFloat(pval);
                                    }
                                    this.wsInfo[j].roompower=roompower==0||roompower=="0"?"":parseFloat(roompower).toFixed(3);
                                }
                            }
                        }else{  //一个电表的实时功率
                            if(val[i].key===this.wsInfo[j].roompower_key){  
                                this.wsInfo[j].roompower=val[i].value;
                            }
                        }
                        if(this.wsInfo[j].powerdegree_num>1){  //多个电表控制显示
                            for(let k=0;k<this.wsInfo[j].powerdegree_num;k++){
                                if(val[i].key===this.wsInfo[j]["powerdegree_key"+k]){
                                    let value=val[i].value?val[i].value:0;
                                    this.wsInfo[j]["powerdegree"+k]=value;
                                    let powerdegree=0;
                                    for(let m=0;m<this.wsInfo[j].powerdegree_num;m++){
                                        let pval=this.wsInfo[j]["powerdegree"+m]?this.wsInfo[j]["powerdegree"+m]:0;
                                        powerdegree+=parseFloat(pval);
                                    }
                                    this.wsInfo[j].powerdegree=powerdegree==0||powerdegree=="0"?"":parseFloat(powerdegree).toFixed(2);
                                }
                            }
                        }else{  //一个电表控制显示
                            if(val[i].key===this.wsInfo[j].powerdegree_key){
                                this.wsInfo[j].powerdegree=val[i].value?parseFloat(val[i].value).toFixed(2):"";
                            }
                        }
                    }
                }
            }
            
        },
    },
    props:["wsInfo","sendInfo"],
    components:{}
}
</script>

<style scoped lang="less">

</style>