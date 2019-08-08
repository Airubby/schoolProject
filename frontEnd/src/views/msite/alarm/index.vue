<template>
    <div class="loncom_content">
        <menuC :menuInfo="menuInfo"></menuC>
        <div class="public_top">
            <div class="btn" @click="menuclick">
                <i class="el-icon-menu"></i>
            </div>
            <div class="title">能耗超限告警</div>
            <div class="btn">
                <div class="loncom_content"  @click="typeclick">
                    <i class="el-icon-more"></i>
                </div>
                <typeC :typeInfo="typeInfo"></typeC>
            </div>
        </div>
        <div class="public_content" v-loading="loading">
            <el-scrollbar class="scrollbar">
                <ul class="alarmli">
                    <li v-for="(item,index) in dataInfo">
                        <h2>{{item.CLASSNAME}}能耗超限</h2>
                        <p>{{item.DESC}}</p>
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
        this.getList();
    },
    mounted() {
        
    },
   data() {
       return {
            loading:false,
            dataInfo:[
                // {ROOMTYPE:'classroom',CLASSNAME:"教室1",DESC:"富大厦复合大师咖啡店分开发富大厦复合大师咖啡店分开发富大厦复合大师咖啡店分开发富大厦复合大师咖啡店分开发发送到莲富大厦复合大师咖啡店分开发的开放的说法"},
                // {ROOMTYPE:'classroom',CLASSNAME:"教室2",DESC:"发送到莲富大厦复合大师咖啡店分开发的开放的说法"},
                // {ROOMTYPE:'officeroom',CLASSNAME:"教室3",DESC:"今日已用电234，限值199"},
            ],
            backdata:[
                // {ROOMTYPE:'classroom',CLASSNAME:"教室1",DESC:"富大厦复合大师咖啡店分开发富大厦复合大师咖啡店分开发富大厦复合大师咖啡店分开发富大厦复合大师咖啡店分开发发送到莲富大厦复合大师咖啡店分开发的开放的说法"},
                // {ROOMTYPE:'classroom',CLASSNAME:"教室2",DESC:"发送到莲富大厦复合大师咖啡店分开发的开放的说法"},
                // {ROOMTYPE:'officeroom',CLASSNAME:"教室3",DESC:"今日已用电234，限值199"},
            ],
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
            this.loading=true;
            this.$api.post('/alarm/query', {createTime:this.$tool.Format('yyyy-MM-dd 00:00:00',new Date())}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                   this.dataInfo=r.data;
                   this.backdata=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        getSort:function(val){
            if(val=='all'){
                this.dataInfo=this.backdata;
            }else{
                let arr=[];
                for(let i=0;i<this.backdata.length;i++){
                    if(val==this.backdata[i]["ROOMTYPE"]){
                        arr.push(this.backdata[i]);
                    }
                }
                this.dataInfo=arr;
            }
        },
        
    },
    watch:{
        'typeInfo.roomtype':function(val){
            if(val){
                this.getSort(val);
            }
        }
    },
    components:{typeC,menuC},
}
</script>

<style scoped lang="less">
    .alarmli li{
        width:100%;
        min-height:60px;
        padding: 20px;
        border-bottom:1px solid #222747;
        h2{
            margin-bottom:10px;
            font-size: 16px;
        }
        p{
            color:#606A8D;
        }
    }
</style>