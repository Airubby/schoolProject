<template>
    <div class="loncom_content">
        <div class="loncom_right_content">
            <div class="bg1C2443 loncom_pd20 loncom_content">
                <div class="loncom_control_top custom">
                    <el-select v-model="groupno" placeholder="请选择" size="small" class="loncom_control_topc">
                        <el-option key="" label="所有" value=""></el-option>
                        <el-option
                        v-for="item in floorOptions"
                        :key="item.groupno"
                        :label="item.groupname"
                        :value="item.groupno">
                        </el-option>
                        <!--
                        <el-option key="教学楼" label="教学楼" value="教学楼"></el-option>
                        <el-option key="实训楼" label="实训楼" value="实训楼"></el-option>
                        -->
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
                                                    <img src="~@/assets/images/noPeople.png" v-if="item.infrareddev=='0'">
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
                                                        <em>{{item.humituredev}}℃</em>
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
                                                        <em>{{item.ammeterdev}}W</em>
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
    </div>
</template>


<script>
import dialogControl from './dialogControl.vue'
export default {
    
  created () {
      this.getFloor();
      this.getList();
  },
  mounted() {
    
  },
   data() {
       return {
            loading:false,
            groupno:'',
            classno:'',
            name:'',
            floorOptions:[],
            dialog_info:{
                visible:false,
                code:'',
            },
            data:[]
       }
    },
    methods:{
        getFloor:function(){
            this.$api.post('/service/queryFloor', {}, r => {
                console.log(r)
                if(r.err_code=="0"){
                   this.floorOptions=r.data
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        getList:function(){
            this.loading=true;
            this.$api.post('/service/query', {name:this.name,groupno:this.groupno,classno:this.classno}, r => {
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
            item.air=!item.air;
            this.loading=true;
            this.$api.post('/service/switchOrder', {devid:item.airDevid,stateid:item.airStateid,closecmd:item.airClosecmd}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    if(!item.airtype){
                        item.air=!item.air;
                        item.airtype=true;
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        changelamp:function(item){
            item.lamp=!item.lamp;
            this.loading=true;
            this.$api.post('/service/switchOrder', {devid:item.lampDevid,stateid:item.lampStateid,closecmd:item.lampClosecmd}, r => {
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    if(!item.lamptype){
                        item.lamp=!item.lamp;
                        item.lamptype=true;
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
    },
    watch:{
        
    },
    components:{dialogControl},
}
</script>

<style scoped>
    @import "~@/assets/css/control.less";
</style>