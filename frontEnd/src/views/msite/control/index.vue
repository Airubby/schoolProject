<template>
    <div class="loncom_content prelative">
        <menuC :menuInfo="menuInfo"></menuC>
        <div class="public_top">
            <div class="btn" @click="menuclick">
                <i class="el-icon-menu"></i>
            </div>
            <div class="title">实时监控</div>
            <div class="btn">
                <div class="loncom_content"  @click="typeclick">
                    <i class="el-icon-more"></i>
                </div>
                <typeC :typeInfo="typeInfo"></typeC>
            </div>
        </div>
        <div class="public_content" v-loading="loading">
            <div class="public_search custom">
                <el-input
                    size="small"
                    placeholder="请输入内容"
                    v-model="name">
                    <i slot="suffix" class="el-input__icon el-icon-search" @click="getList()"></i>
                </el-input>
            </div>
            <el-scrollbar class="scrollbar" style="height:calc(100% - 55px);">
                <div> 
                    <template v-for="item in data" v-if="data.length>0">
                        <div class="loncom_control_box custom">
                            <h2>
                                <span>{{item.classname}}</span>
                                <span @click="edit(item)" class="loncom_fr edit">编辑</span>
                            </h2>
                            <div class="loncom_control_boxcon">
                                <div class="loncom_control_img">
                                    <img src="~@/assets/images/noPeople.png" v-if="!item.infrareddev||parseInt(item.infrareddev)==0||item.infrareddev==''">
                                    <img src="~@/assets/images/hasPeople.png" v-else>
                                </div>
                                <div class="loncom_control_info">
                                    <div class="info">
                                        <span class="loncom_mr5">空调状态</span>
                                        <el-switch
                                            ref="airtype"
                                            @change="changeair(item)"
                                            :disabled="item.airtype"
                                            v-model="item.air"
                                            active-color="#2CA858"
                                            inactive-color="#6E7DAA">
                                        </el-switch>
                                    </div>
                                    <div class="info">
                                        <span class="loncom_mr5">环境温度</span>
                                        <em class="color2CA858">{{item.humituredev}}</em>℃
                                    </div>
                                    <div class="info">
                                        <span class="loncom_mr5">照明状态</span>
                                        <el-switch
                                            @change="changelamp(item)"
                                            :disabled="item.lamptype"
                                            v-model="item.lamp"
                                            active-color="#2CA858"
                                            inactive-color="#6E7DAA">
                                        </el-switch>
                                    </div>
                                    <div class="info">
                                        <span class="loncom_mr5">实时功耗</span>
                                        <em class="color2CA858">{{item.roompower}}</em>Kw
                                    </div>
                                    <div class="info info1">
                                        <span class="loncom_mr5">总用电量</span>
                                        <em class="color2CA858">{{item.powerdegree}}</em>KWh
                                    </div>
                                </div>
                            </div>
                        </div>
                    </template>
                </div>
            </el-scrollbar>
        </div>
        
        <dialog-control :dialogInfo="dialog_info"></dialog-control>
        <webSocket :wsInfo="data" v-if="data"></webSocket>
    </div>
</template>


<script>
import dialogControl from './dialogControl.vue'
import webSocket from '@/components/webSocket.vue'
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
            groupno:'',
            floorno:'',
            classno:'',
            airStatus:'',
            lampStatus:'',
            name:'',
            groupOptions:[],
            floorOptions:[],
            dialog_info:{
                visible:false,
                code:'',
            },
            slide_info:{
                visible:false,
            },
            data:[],
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
            this.$api.post('/service/query', {
                name:this.name,groupno:this.groupno,
                floor:this.floorno,classno:this.classno,
                lampStatus:'',airStatus:''}, r => {
                
                this.loading=false;
                if(r.err_code=="0"){
                   this.data=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        edit:function(item){
            this.dialog_info.code=item.code;
            this.dialog_info.visible=true;
        },
        changeair:function(item){
            
            let obj={closecmd:item.airClosecmd,serviceid:item.serviceid};
            if(!item.air){ //开着的 发关机指令
                obj.devid=item.aircontrol_devid;
                obj.stateid=item.aircontrol_pointid;
                this.loading=true;
                this.$api.post('/service/switchOrder', obj, r => {
                    
                    this.loading=false;
                    if(r.err_code=="0"){
                        this.$message.success(r.err_msg);
                    }else{
                        this.$message.warning(r.err_msg);
                        item.air=!item.air;
                    }
                });
            }
            
            
        },
        changelamp:function(item){
            //进入这个的时候，lamp的状态就已经改变了；lamp为false是关着的；进来的时候其实就已经开着状态了，所以下面的指令就取相反
            let obj={closecmd:item.lampClosecmd,serviceid:item.serviceid};
            if(item.lamp){ //开着的其实是关着的 发开机指令
                obj.devid=item.powercontrolopen_devid;
                obj.stateid=item.powercontrolopen_pointid;
            }else{ //关机状态，其实是开着的，发关机指令
                if(
                    (item.powercontrol_devid=="8093,80103"&&item.powercontrol_pointid=="3000000002,3000000002")||
                    (item.powercontrol_devid=="80603"&&item.powercontrol_pointid=="3000000002")){
                    return;
                }
                obj.devid=item.powercontrol_devid;
                obj.stateid=item.powercontrol_pointid;
            }  
            this.loading=true;
            this.$api.post('/service/switchOrder', obj, r => {
                
                this.loading=false;
                if(r.err_code=="0"){
                    this.$message.success(r.err_msg);
                }else{
                    this.$message.warning(r.err_msg);
                    item.lamp=!item.lamp;
                }
            });
        },
    },
    watch:{
        'typeInfo.roomtype':function(val){
            
            if(val){
                this.classno=val=='all'?"":val;
                this.getList();
            }
        }
    },
    components:{dialogControl,webSocket,menuC,typeC},
}
</script>

<style scoped>
    @import "~@/assets/css/mcontrol.less";
</style>