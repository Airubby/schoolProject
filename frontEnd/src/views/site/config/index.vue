<template>
	<div class="loncom_content">
        <div class="loncom_right_content">
            <div class="bg1C2443 loncom_pd20 loncom_content custom">
                <div class="search_form_table">
                    <form class="el-form el-form--inline">
                        <el-input v-model="search_input" placeholder="输入名称查询" size="small" 
                        prefix-icon="el-icon-search"
                        @keyup.native="keySearch($event)"
                        style="width:250px;margin:0 5px 10px 0"></el-input>
                        <el-button type="primary" size="small" @click="getList" @keydown="keySearch($event)">查询</el-button>
                        <el-button @click="add()" type="primary" size="small" v-permission="'edit'" style="position:absolute;right:0;top:0;">新增</el-button>                                        
                    </form>
                </div>
                <el-scrollbar style="height:calc(100% - 45px)">
                    <el-search-table-pagination type="local" class="loncom_position_relative" border 
                        ref="thisRef"
                        :data="table_data" :columns="table_info_columns"
                        stripe
                        @selection-change="handleSelectionChange"
                        >   
                        
                        <el-table-column slot="prepend" type="selection"></el-table-column>
                        <template slot-scope="scope" slot="preview-date">
                            <div>
                                {{scope.row.start_time}} - {{scope.row.end_time}}
                            </div>
                        </template>
                        <template slot-scope="scope" slot="preview-time">
                            <div>
                                <p v-for="item in scope.row.time">{{item}}</p>
                            </div>
                        </template>
                        <template slot-scope="scope" slot="preview-handle">
                            <div>
                                <p v-permission="'edit'">
                                    <a href="javascript:;" class="loncom_color loncom_mr5" @click="edit(scope.row)">编辑</a> 
                                    <a href="javascript:;" class="loncom_color" @click="remove(scope.row)">删除</a>
                                </p>
                            </div>
                        </template>
                        <div class="loncom_table_btn" v-permission="'edit'">
                            <el-button @click="remove()" type="primary" size="mini">删除</el-button>
                        </div>
                    </el-search-table-pagination>
                </el-scrollbar>
            </div>
        </div>
        
		<Add v-if="add_info.visible" :dialogInfo="add_info"></Add>
    </div>
</template>

<script>
import Add from './dialogAdd.vue'
import Vue from 'vue'
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
            search_input:'',
          	table_info_columns:[
              { prop: 'name', label: '时区名称',minWidth:10},
              { prop: 'date', label: '日期范围',minWidth:20,slotName:'preview-date'},
              { prop: 'timeweek', label: '周范围',minWidth:15},
              { prop: 'time', label: '时区范围',minWidth:10,slotName:'preview-time'},
              { prop: 'handel', label: '操作',slotName:'preview-handle',width:100},
          ],
          table_data:[],
		  add_info:{
			  visible:false,
			  title:'新增时区',
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
        },
        add:function(){
            this.add_info.id="";
            this.add_info.visible=true;
            this.add_info.title="新增时区";
        },
        getList:function(){
            this.$api.post('/time/query', {name:this.search_input}, r => {
                
                if(r.err_code=="0"){
                   this.table_data=r.data;
                    for(let j=0;j<this.table_data.length;j++){
                        let timedeteil=this.table_data[j].timeDeteil;
                        if(timedeteil&&timedeteil.length>0){
                            let first="";
                            let week=[];
                            let time=[];
                            for(let i=0;i<timedeteil.length;i++){
                                if(i==0){
                                    week.push(timedeteil[i].timeweek);
                                    time.push(timedeteil[i].begintime+"-"+timedeteil[i].endtime);
                                    first=timedeteil[i].timeweek;
                                }else{
                                    if(week.toString().indexOf(timedeteil[i].timeweek) != -1){ //包含了
                                        if(first==timedeteil[i].timeweek){
                                            time.push(timedeteil[i].begintime+"-"+timedeteil[i].endtime);
                                        }
                                    }else{
                                        week.push(timedeteil[i].timeweek)
                                    }
                                }
                            }
                            let showWeek=this.$tool.showWeek(week)
                            Vue.set(this.table_data[j],'timeweek',showWeek.toString());
                            Vue.set(this.table_data[j],'time',time);
                        }
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        edit:function(item){
            this.add_info.id=item.id;
            this.add_info.title="编辑时区";
            this.add_info.visible=true;
        },
        //删除
        remove:function(row){
            let ids=[];
            if(row!=undefined&&row.id!=undefined){ //单条
                ids.push(row.id);
            }else{  //多条
                if(this.multipleSelection.length>0){
                    for(let i=0;i<this.multipleSelection.length;i++){
                        ids.push(this.multipleSelection[i].id);
                    }
                }else{
                    this.$message.warning("请勾选需要删除的项");
                    return;
                }
            }
            this.$confirm("你确定要删除?", '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$api.post('/time/delete', {"id":ids.toString()}, r => {
                    if(r.err_code=="0"){
                        this.getList();
                        this.$message.success(r.err_msg);
                    }else{
                        this.$message.error(r.err_msg);
                    }
                }); 
            });
        },
        

    },
    watch:{
		search_input:function(val){
            if(val==""){
                this.getList();
            }
        }
	},
    props:[],  
    components:{Add},
}
</script>

