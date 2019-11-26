<template>
    <div class="loncom_content">
        <menuC :menuInfo="menuInfo"></menuC>
        <div class="public_top">
            <div class="btn" @click="menuclick">
                <i class="el-icon-menu"></i>
            </div>
            <div class="title">用电排行</div>
            <div class="btn">
                <div class="loncom_content"  @click="typeclick">
                    <i class="el-icon-more"></i>
                </div>
                <typeC :typeInfo="typeInfo"></typeC>
            </div>
        </div>
        <div class="public_content" v-loading="loading">
            <el-scrollbar class="scrollbar">
                <ul class="topli">
                    <li v-for="(item,index) in dataInfo">
                        <span class="index" :class="{'one':index==0,'two':index==1,'three':index==2}">{{index+1}}</span>
                        <span class="name">{{item.classname}}</span>
                        <span class="value">{{item.value}}KWh</span>
                    </li>
                </ul>
            </el-scrollbar>
        </div>
    </div>
</template>


<script>
import menuC from '@/components/menu.vue'
import typeC from '@/components/type.vue'
export default {
    
    created () {
        let _this=this;
        this.getList();
        
        this.timer=setInterval(function(){
            _this.getList();
        },15000)

    },
    mounted() {
     
    },
    destroyed() {
        clearInterval(this.timer);
        this.timer='';
    }, 
   data() {
       return {
            loading:false,
            timer:'',
            topData:{},
            dataInfo:[],
            menuInfo:{
                visible:false
            },
            typeInfo:{
                visible:false,
                roomtype:'all',
            }
       }
    },
    methods:{
        menuclick:function(){
            this.menuInfo.visible=true;
        },
        typeclick:function(){
            this.typeInfo.visible=!this.typeInfo.visible;
        },
        getList:function(){
            let startTime=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date());
            let endTime=this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date());
            this.$api.post('/service/topInfo', {startTime:startTime,endTime:endTime}, r => {
                if(r.err_code=="0"){
                    this.topData=r.data;
                    if(this.typeInfo.roomtype=="classroom"){
                        this.dataInfo=this.topData.classtopy;
                    }else if(this.typeInfo.roomtype=="officeroom"){
                        this.dataInfo=this.topData.officetopy;
                    }else{
                        this.dataInfo=this.topData.alltop;
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
    },
    watch:{
        'typeInfo.roomtype':function(val){
            console.log(val)
            if(val){
                if(val=="classroom"){
                    this.dataInfo=this.topData.classtopy;
                }else if(val=="officeroom"){
                    this.dataInfo=this.topData.officetopy;
                }else{
                    this.dataInfo=this.topData.alltop;
                }
            }
        }
    },
    components:{menuC,typeC},
}
</script>

<style scoped lang="less">
    .topli li{
        width:100%;
        height:60px;
        line-height: 60px;
        display:flex;
        border-bottom:1px solid #222747;
        font-size: 16px;
        .index{
            width: 30px;
            height: 30px;
            border-radius: 50%;
            text-align: center;
            line-height: 30px;
            margin: 15px 30px 15px 20px;
        }
        .one{
            background: #e22b2a;
            color:#fff;
        }
        .two{
            background: #e79626;
            color:#fff;
        }
        .three{
            background: #c4bc26;
            color:#fff;
        }
        .name{
            min-width: 150px;
        }
        .value{
            float:right;
            margin-right: 20px;
            width:calc(100% - 250px);
            text-align:right;
        }
    }
    
</style>