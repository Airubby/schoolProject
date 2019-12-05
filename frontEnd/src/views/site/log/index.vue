<template>
	<div class="loncom_content">
        <div class="loncom_right_content">
            <div class="bg1C2443 loncom_pd20 loncom_content custom">
                <el-scrollbar style="height:100%">
                    <el-search-table-pagination class="loncom_position_relative" border 
                        :url="$ajaxUrl+'/syslog/query'"
                            list-field="data.items" 
                            total-field="data.count"
                            method='post'
                            :data="table_data" :columns="table_info_columns"
                            :formOptions="table_forms" >                                        
                            <el-table-column slot="prepend" type="selection"></el-table-column>
                    </el-search-table-pagination>
                </el-scrollbar>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    created () {
        // 
    },
    mounted() {
    },
    data(){
      return {
		  	table_forms: {
				inline: true,
				size:'small',
				submitBtnText: '查询',
                submitLoading:false,
				forms: [{prop:'userid',placeholder:'账号'},]
			},
          	table_info_columns:[
                { prop: 'userid', label: '登录账号',minWidth:10},
                { prop: 'ip', label: '登录IP',minWidth:10},
                { prop: 'createtime', label: '操作时间',minWidth:15},
                { prop: 'details', label: '操作内容',minWidth:45},
          ],
          table_data:[],
		  
          
      }
    },
    methods:{
     

    },
    watch:{
		table_data:function(val){
            
        }
	},
    props:[],  
    components:{},
}
</script>

