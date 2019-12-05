<template>
    <div class="loncom_content custom">
		<el-input v-model="initParams.details"></el-input>
		<a @click="chaxun">查询</a>
		<el-search-table-pagination
                :url="$ajaxUrl+'/syslog/query'"
				list-field="data.items" 
				total-field="data.count"
                method='post' 
                :params="initParams"
                :showPagination="true"
				:showSelectAll="true"
                :columns="table_columns" ref="thisRef">   
                <el-table-column slot="prepend" type="selection"></el-table-column>
                <el-table-column slot="prepend" type="index" label="序号"></el-table-column>
                
            </el-search-table-pagination>


			<div class="loncom_public_table" v-loading="loading">
				<div class="slide_top">
					<el-upload
					class="upload-demo"
					ref="upload"
					:action="$ajaxUrl" 
					:on-success="onSuccess"
					:on-error="onError"
					:on-change="onchange"
					:before-upload="beforeUpload"
					:show-file-list="false"
					:auto-upload="true">
					<el-button size="small" type="primary">上传图片</el-button>
					</el-upload>
				</div>
				<el-scrollbar class="slide_con" style="height:220px">
					<ul class="el-upload-list el-upload-list--picture-card">
						<li class="el-upload-list__item is-success" v-for="item in fileList">
							<img :src="'static/upload/'+item.url" class="el-upload-list__item-thumbnail">
							<span class="el-upload-list__item-actions">
								<span class="el-upload-list__item-delete" @click="handleRemove(item.id)">
									<i class="el-icon-delete"></i>
								</span>
							</span>
						</li>
					</ul>
				</el-scrollbar>
			</div>
			
				
        </div>
    </div>
</template>

<script>
export default {
	created () {
		
	},
	data(){
		
		return {
			fileList:[],
			number:0,
			initParams:{
				details:""
			},
            table_columns:[
              { prop: 'createtime', label: '名称',minWidth:10,sortable:true},
              { prop: 'details', label: '内容',minWidth:10},
            ],
            table_data:[],
			multipleSelection:true,
			allSelection:[],
			currentSelection:[],
		}
	},
	methods:{
		chaxun:function(){
			// this.$refs.thisRef.searchHandler(false);
			this.$refs.thisRef.allSelection=['1232']
		},
		checkItem:function(arr1,arr2){
			let arr=[];
			for(let i=0;i<arr1.length;i++){
				let id=arr1[i].id;
				let isExit=false;
				for(let j=0;j<arr2.length;j++){
					let cid=arr2[j].id;
					if(id==cid){
						isExit=true;
						break;
					}
				}
				if(!isExit){
					arr.push(arr1[i]);
				}
			}
			return arr;
		},
		clearSelection:function() {
			this.$refs.thisRef.$refs.table.clearSelection();
			this.allSelection=[];
			this.currentSelection=[];
		},
		handleSelectionChange:function(val){
			
			if(this.multipleSelection){
				let currentArr = [];
				if(val.length>this.currentSelection.length){ //增加
					this.allSelection=this.allSelection.concat(this.checkItem(val,this.currentSelection))
				}else{//减少
					currentArr=this.checkItem(this.currentSelection,val);
					let list=[];
					if(this.allSelection.length>currentArr.length){
						this.allSelection=this.checkItem(this.allSelection,currentArr);
					}else{
						this.allSelection=this.checkItem(currentArr,this.allSelection);
					}
				}
				this.currentSelection = val;
			}
			this.multipleSelection=true;
		},
		resultData:function(value){
			this.multipleSelection=false;
			this.currentSelection=[];
            this.table_data=value.data&&value.data.items?value.data.items:[];
            this.$nextTick(function(){
                for(let i=0;i<this.table_data.length;i++){
					for(let j=0;j<this.allSelection.length;j++){
						if(this.table_data[i].id==this.allSelection[j].id){
							this.multipleSelection=false;
							this.$refs.thisRef.$refs.table.toggleRowSelection(this.table_data[i],true); 
							this.currentSelection.push(this.table_data[i]);
						}
					}
                }
            })
        },



		add:function(file){
            
            if(file.response.err_code=="0"){
                this.$api.post('/slide/add', {url:file.response.data}, r => {
                    
                    if(r.err_code=="0"){
                        this.$message.success('上传成功');
                        this.getList();
                    }else{
                        this.$message.warning(r.err_msg);
                    }
                });
            }else{
                this.$message.warning("上传失败");
            }
            
        },
        getList(){
            this.$api.get('/slide/query', {}, r => {
                
                if(r.err_code=="0"){
                    this.fileList=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            });
        },
        handleRemove(id) {
            this.$confirm("确定删除此幻灯片?", '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    this.$api.post('/slide/delete', {id:id}, r => {
                        if(r.err_code=="0"){
                            this.$message.success(r.err_msg);
                            this.getList();
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });

            });
        },
        onSuccess(res, file, fileList){
            
            if(res.err_code=="0"){
                this.add(file);
            }else{//上传失败
                this.$message.warning(res.err_msg);
            }
            
        },
        onError(err, file, fileList){
            
            this.$message.warning(err);
        },
        beforeUpload(file){
            var fileArry=file.name.split(".");
            var fileType=fileArry[fileArry.length-1];
            // if(fileType!="jpg"&&fileType!="JPG"&&fileType!="png"&&fileType!="PNG"){
            //     this.$message.warning("只能上传jpg/png格式图片");
            //     return false;
            // }
        },
        onchange(file,fileList){
            
        },
	},
	watch:{
			
	},
}
</script>

<style scoped lang="less">
	.home{width:200px;height:50px;margin:0 auto;}
</style>