<template>
    <div class="loncom_content">
        <div class="loncom_right_content">
            <div class="bg1C2443 loncom_pd20 loncom_content">
                <div class="loncom_mb20 custom">
                    <el-select v-model="initParams.ROOMTYPE" placeholder="请选择" size="small" class="loncom_control_topc" style="width:150px;">
                        <el-option key="" label="所有" value=""></el-option>
                        <el-option key="classroom" label="教室" value="classroom"></el-option>
                        <el-option key="officeroom" label="办公室" value="officeroom"></el-option>
                    </el-select>
                    <el-input
                        style="width:300px;margin-left:10px;"
                        size="small"
                        class="loncom_mr10"
                        placeholder="请输入教室名称"
                        prefix-icon="el-icon-search"
                        v-model="initParams.CLASSNAME">
                    </el-input>
                    <el-button type="primary" size="small" @click="search">搜索</el-button>
                    <el-button type="primary" size="small" @click="set" class="loncom_fr" v-permission="'edit'">设置</el-button>
                </div>
                <div class="scrollbar custom" style='height:calc(100% - 50px)' v-loading="loading">
                    <el-scrollbar style='height:100%' class="loncom_control_con">
                        <el-search-table-pagination type="local" 
                            :params="initParams"
                            border 
                            :data="table_data" 
                            :columns="table_columns" stripe
                            ref="thisRef">   
                            <template slot-scope="scope" slot="preview-type">
                                <span v-if="scope.row.TYPE=='1'">日告警</span>
                                <span v-else>月告警</span>
                            </template>
                        </el-search-table-pagination>
                    </el-scrollbar>
                </div>
            </div>                                    
        </div>
        <setVlaue v-if="setInfo.visible" :dialogInfo="setInfo"></setVlaue>
    </div>
</template>


<script>
import setVlaue from './dialogSet.vue'
export default {
    
    created () {
        this.getList();
    },
    mounted() {
        
    },
   data() {
       return {
            loading:false,
            initParams:{
                CLASSNAME:'',
                ROOMTYPE:'',
            },
            table_data:[
                // {CLASSNAME:'明说123',ROOMTYPE:'classroom'},
                // {CLASSNAME:'333',ROOMTYPE:'classroom'},
                // {CLASSNAME:'搜到是',ROOMTYPE:'officeroom'},
                // {CLASSNAME:'外网',ROOMTYPE:'classroom'},
                // {CLASSNAME:'问问',ROOMTYPE:'officeroom'},
            ],
            table_columns:[
                { prop: 'CLASSNAME', label: '名称',minWidth:10},
                { prop: 'CREATETIME', label: '告警时间',minWidth:15},
                { prop: 'TYPE', label: '告警类型',slotName:'preview-type',minWidth:10},
                { prop: 'DESC', label: '告警内容',minWidth:30},
            ],
            setInfo:{
                visible:false,
            }
       }
    },
    methods:{
        search:function(){
            this.$refs.thisRef.searchHandler(true);
        },
        getList:function(){
            this.loading=true;
            this.$api.post('/alarm/query', {createTime:this.$tool.Format('yyyy-MM-dd 00:00:00',new Date())}, r => {
                
                this.loading=false;
                if(r.err_code=="0"){
                   this.table_data=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        set:function(){
            this.setInfo.visible=true;
        },
        
    },
    watch:{
        'initParams.CLASSNAME':function(val){
            if(!val){
                this.search();
            }
        }
    },
    components:{setVlaue},
}
</script>

<style scoped>
</style>