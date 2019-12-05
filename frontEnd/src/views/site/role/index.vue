<template>
	<div class="loncom_content">
        <div class="loncom_right_content">
            <div class="bg1C2443 loncom_pd20 loncom_content custom">
                <div class="search_form_table">
                    <form class="el-form el-form--inline">
                        <el-input v-model="initParams.name" placeholder="输入角色查询" size="small" 
                        prefix-icon="el-icon-search"
                        @keyup.native="keySearch($event)"
                        style="width:250px;margin:0 5px 10px 0"></el-input>
                        <el-button type="primary" size="small" @click="getList" @keydown="keySearch($event)">查询</el-button>
                        <el-button @click="add()" type="primary" size="small" style="position:absolute;right:0;top:0;" v-permission="'edit'">新增</el-button>                                        
                    </form>
                </div>
                <el-scrollbar style="height:calc(100% - 45px)">
                    <el-search-table-pagination type="local" class="loncom_position_relative" border 
                        ref="thisRef"
                        :url="$ajaxUrl+'/role/query'"
                        :data="table_data" 
                        :columns="table_info_columns"
                        stripe
                        list-field="data.items" 
                        total-field="data.count"
                        :params="initParams"
                        @selection-change="handleSelectionChange"
                        >   
                        <el-table-column slot="prepend" type="selection"></el-table-column>
                        <template slot-scope="scope" slot="preview-state">
                            <div>
                                {{scope.row.state|stateFilter}}
                            </div>
                        </template>
                        <template slot-scope="scope" slot="preview-type">
                            <span v-if="scope.row.type=='1'">管理员</span>
                            <span v-else>普通用户</span>
                        </template>
                        <template slot-scope="scope" slot="preview-handle">
                            <div>
                                <p v-permission="'edit'">
                                    <a href="javascript:;" class="loncom_color loncom_mr10" @click="edit(scope.row)">编辑</a> 
                                    <a href="javascript:;" class="loncom_color" @click="remove(scope.row)" v-if="scope.row.id!='1'">删除</a>
                                </p>
                            </div>
                        </template>
                    </el-search-table-pagination>
                </el-scrollbar>
            </div>
        </div>
        
		<Add v-if="add_info.visible" :dialogInfo="add_info" v-on:backInfo="getList"></Add>
    </div>
</template>

<script>
import Add from './dialogAdd.vue'
export default {
    created () {
        this.getList();
    },
    mounted() {
    },
    data(){
      return {
            loginInfo:{

            },
            options:[],
            initParams:{name:""},
          	table_info_columns:[
              { prop: 'name', label: '名称',minWidth:10},
              { prop: 'type', label: '类型',minWidth:10,slotName:'preview-type'},
              { prop: 'details', label: '描述',minWidth:15},
              { prop: 'state', label: '状态',slotName:'preview-state',minWidth:8},
              { prop: 'handel', label: '操作',slotName:'preview-handle',width:100},
          ],
          table_data:[],
		  add_info:{
			  visible:false,
			  title:'新增角色',
              id:'',
		  },
          multipleSelection:[],
          
      }
    },
    methods:{
        keySearch:function(ev){
            if(ev.keyCode == 13){
                this.getList();
            }
        },
        handleSelectionChange:function(val){
            this.multipleSelection=val;
            // this.multipleSelection=[];
            // for(var i=0;i<val.length;i++){
            //     this.multipleSelection.push(val[i].id);
            // }
        },
        add:function(){
            this.add_info.id="";
            this.add_info.visible=true;
            this.add_info.title="新增角色";
        },
        getList:function(){
            // this.$refs['thisRef'].searchHandler(false);
            this.$api.post('/role/query', {name:this.initParams.name}, r => {
                
                if(r.err_code=="0"){
                  this.table_data=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        edit:function(item){
            this.add_info.id=item.id;
            this.add_info.title="编辑角色";
            this.add_info.visible=true;
        },
        //删除
        remove:function(item){
            this.$confirm("你确定要删除?", '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$api.post('/role/delete', {"id":item.id}, r => {
                    if(r.err_code=="0"){
                        this.getList();
                        this.$message.success(r.err_msg);
                    }else{
                        this.$message.error(r.err_msg);
                    }
                }); 
            });
        },
        //维护
        assert:function(row){
            let ids=[];
            if(row!=undefined&&row.id!=undefined){ //单条
                ids.push(row.id);
            }else{  //多条
                if(this.multipleSelection.length>0){
                    for(let i=0;i<this.multipleSelection.length;i++){
                        if(this.multipleSelection[i].userid=='admin'){
                            this.$message.warning("admin账号不能维护");
                            return;
                        }
                        ids.push(this.multipleSelection[i].id);
                    }
                    //ids=this.multipleSelection;
                }else{
                    this.$message.warning("请勾选需要维护的项");
                    return;
                }
            }

            this.$confirm("确定维护?", '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type:'warning',
                }).then(() => {
                    var thisID=ids.toString();
                    ;
                    this.$api.post('/user/updatestate', {"ids":thisID,state:'0'}, r => {
                        if(r.err_code=="0"){
                            this.$message.success(r.err_msg);
                            this.getList();
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });
                
            });
        },
        //启用
        start:function(row){
            let ids=[];
            if(row!=undefined&&row.id!=undefined){ //单条
                ids.push(row.id);
            }else{  //多条
                if(this.multipleSelection.length>0){
                    //ids=this.multipleSelection;
                    for(let i=0;i<this.multipleSelection.length;i++){
                        ids.push(this.multipleSelection[i].id);
                    }
                }else{
                    this.$message.warning("请勾选需要启用的项");
                    return;
                }
            }

            this.$confirm("确定启用?", '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type:'warning',
                }).then(() => {
                    var thisID=ids.toString();
                    ;
                    this.$api.post('/user/updatestate', {"ids":thisID,state:'1'}, r => {
                        if(r.err_code=="0"){
                            this.$message.success(r.err_msg);
                            this.getList();
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });
                
            });
        },

    },
    watch:{
		"initParams.name":function(val){
            if(val==""){
                this.getList();
            }
        }
	},
    props:[],  
    components:{Add},
}
</script>

