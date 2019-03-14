<template>
    <div class="loncom_content custom">
        <h2>测试备份数据库</h2>
		<el-search-table-pagination type="local" class="loncom_position_relative" border 
			ref="thisRef"
			:data="table_data" :columns="table_info_columns"
			stripe
			>   
			<el-table-column slot="prepend" type="selection"></el-table-column>
			<template slot-scope="scope" slot="preview-handle">
				<div>
					<a href="javascript:;" class="loncom_color" @click="back(scope.row)">还原</a> 
				</div>
			</template>
		</el-search-table-pagination>
    </div>
</template>

<script>
export default {
	created () {
		this.getList();
	},
	data(){
		
		return {
			table_data:[],
			table_info_columns:[
			  { prop: 'id', label: '编号',minWidth:10},
              { prop: 'time', label: '备份时间',minWidth:10},
              { prop: 'handel', label: '操作',slotName:'preview-handle',width:100},
			]
		}
	},
	methods:{
		getList:function(){
			this.$api.post('/database/query', {}, r => {
                console.log(r)
                if(r.err_code=="0"){
                    this.table_data=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
		},
		back:function(item){
			console.log(item)
			let time=this.$tool.Format("yyyyMMddhhmmss",item.time);
			this.$api.post('/database/query', {time:time}, r => {
                console.log(r)
                if(r.err_code=="0"){
                    this.$message.success(r.err_msg);
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
		},
	},
	watch:{
			
	},
}
</script>

<style scoped lang="less">
	
</style>