<template>
    <div class="loncom_content">
        <div class="loncom_right_content">
            <div class="bg1C2443 loncom_pd20 loncom_content">
                <div class="loncom_control_top custom">
                    <el-select v-model="groupno" placeholder="请选择" size="small" class="loncom_control_topc">
                        <el-option key="" label="所有" value=""></el-option>
                        <el-option
                        v-for="item in groupOptions"
                        :key="item.groupno"
                        :label="item.groupname"
                        :value="item.groupno">
                        </el-option>
                        <!--
                        <el-option key="教学楼" label="教学楼" value="教学楼"></el-option>
                        <el-option key="实训楼" label="实训楼" value="实训楼"></el-option>
                        -->
                    </el-select>
                    <el-select v-model="floorno" placeholder="请选择" size="small" class="loncom_control_topc">
                        <el-option key="" label="所有" value=""></el-option>
                            <el-option
                            v-for="item in floorOptions"
                            :key="item.floorno"
                            :label="item.floorname"
                            :value="item.floorno">
                            </el-option>
                    </el-select>
                    <el-select v-model="classno" placeholder="请选择" size="small" class="loncom_control_topc">
                        <el-option key="" label="所有" value=""></el-option>
                        <el-option key="classroom" label="教室" value="classroom"></el-option>
                        <el-option key="officeroom" label="办公室" value="officeroom"></el-option>
                    </el-select>
                    <el-input
                        style="width:300px;"
                        size="small"
                        class="loncom_control_topc"
                        placeholder="请输入内容"
                        prefix-icon="el-icon-search"
                        v-model="name">
                    </el-input>
                    <el-button type="primary" size="small" @click="getList">搜索</el-button>
                    <el-button type="primary" size="small" class="loncom_fr" @click="upload" v-if="loginInfo.userid=='admin'">更改Silde</el-button>
                </div>
                <div class="scrollbar" style='height:calc(100% - 50px)' v-loading="loading">
                    <el-scrollbar style='height:100%' class="loncom_control_con">
                        <div style="width: 100%; height:100%; position:relative;"> 
                            <el-row :gutter="20">
                                <template v-for="item in data" v-if="data.length>0">
                                    <el-col :xs="24" :lg="12" :xl="8">
                                        <div class="loncom_control_box custom">
                                            <h2>
                                                <span>{{item.classname}}</span>
                                                <el-dropdown class="loncom_index_dropdown loncom_fr">
                                                    <span class="el-dropdown-link">
                                                        <i class="el-icon-more el-icon--center"></i>
                                                    </span>
                                                    <el-dropdown-menu slot="dropdown">
                                                        <span @click="edit(item)"><el-dropdown-item>编辑</el-dropdown-item></span>
                                                    </el-dropdown-menu>
                                                </el-dropdown>
                                            </h2>
                                            <div class="loncom_control_boxcon">
                                                <div class="loncom_control_img">
                                                    <img src="~@/assets/images/noPeople.png" v-if="!item.infrareddev||parseInt(item.infrareddev)==0||item.infrareddev==''">
                                                    <img src="~@/assets/images/hasPeople.png" v-else>
                                                </div>
                                                <div class="loncom_control_info">
                                                    <el-col :span="12" class="info">
                                                        <span class="loncom_mr10">空调状态</span>
                                                        <el-switch
                                                            ref="airtype"
                                                            @change="changeair(item)"
                                                            :disabled="item.airtype"
                                                            v-model="item.air"
                                                            active-color="#2CA858"
                                                            inactive-color="#6E7DAA">
                                                        </el-switch>
                                                    </el-col>
                                                    <el-col :span="12" class="info">
                                                        <span class="loncom_mr10">环境温度</span>
                                                        <em class="color2CA858">{{item.humituredev}}</em>℃
                                                    </el-col>
                                                    <el-col :span="12" class="info">
                                                        <span class="loncom_mr10">照明状态</span>
                                                        <el-switch
                                                            @change="changelamp(item)"
                                                            :disabled="item.lamptype"
                                                            v-model="item.lamp"
                                                            active-color="#2CA858"
                                                            inactive-color="#6E7DAA">
                                                        </el-switch>
                                                    </el-col>
                                                    <el-col :span="12" class="info">
                                                        <span class="loncom_mr10">实时功耗</span>
                                                        <em class="color2CA858">{{item.roompower}}</em>Kw
                                                    </el-col>
                                                    <el-col :span="24" class="info">
                                                        <span class="loncom_mr10">总用电量</span>
                                                        <em class="color2CA858">{{item.powerdegree}}</em>kwh
                                                    </el-col>
                                                </div>
                                            </div>
                                        </div>
                                    </el-col>
                                </template>
                            </el-row>
                        </div>
                    </el-scrollbar>
                </div>
            </div>
                                                        
        </div>
        <dialog-control :dialogInfo="dialog_info" v-if="dialog_info.visible"></dialog-control>
        <dialog-change-slide :dialogInfo="slide_info" v-if="slide_info.visible"></dialog-change-slide>
        <webSocket :wsInfo="data" v-if="data"></webSocket>
    </div>
</template>


<script>
import dialogControl from './dialogControl.vue'
import dialogChangeSlide from './dialogChangeSlide.vue'
import webSocket from '@/components/webSocket.vue'
export default {
    
    created () {
        if(sessionStorage.loginInfo){
            this.loginInfo=JSON.parse(sessionStorage.loginInfo);
        }
        this.getFloor();
        this.getList();
    },
    mounted() {
        
    },
   data() {
       return {
            loading:false,
            loginInfo:{},
            groupno:'',
            floorno:'',
            classno:'',
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
            data:[]
       }
    },
    methods:{
        getFloor:function(){
            this.$api.post('/service/queryFloor', {}, r => {
                console.log(r)
                if(r.err_code=="0"){
                   this.groupOptions=r.data
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        getList:function(){
            this.loading=true;
            this.$api.post('/service/query', {name:this.name,groupno:this.groupno,floor:this.floorno,classno:this.classno}, r => {
                console.log(r)
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
            console.log(item)
            let obj={closecmd:item.airClosecmd,serviceid:item.serviceid};
            if(!item.air){ //开着的 发关机指令
                obj.devid=item.aircontrol_devid;
                obj.stateid=item.aircontrol_pointid;
                this.loading=true;
                this.$api.post('/service/switchOrder', obj, r => {
                    console.log(r)
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
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    this.$message.success(r.err_msg);
                }else{
                    this.$message.warning(r.err_msg);
                    item.lamp=!item.lamp;
                }
            });
        },
        upload:function(){
            this.slide_info.visible=true;
        }
    },
    watch:{
        groupno:function(val){
            console.log(val)
            let groupfloor="";
            for(let i=0;i<this.groupOptions.length;i++){
                if(val==this.groupOptions[i].groupno){
                    groupfloor=this.groupOptions[i].groupfloor;
                }
            }
            this.floorno="";
            this.floorOptions=[];
            for(let i=1;i<=groupfloor;i++){
                this.floorOptions.push({floorno:i,floorname:i+"楼"});
            }
        }
    },
    components:{dialogControl,dialogChangeSlide,webSocket},
}
</script>

<style scoped>
    @import "~@/assets/css/control.less";
</style>