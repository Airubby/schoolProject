<template>
    <el-dialog title="更改Slide" :visible.sync="dialogInfo.visible" width="510px" v-dialogDrag class="custom">
        <div style="height:300px;" class="loncom_scrollbar">
            <div class="loncom_public_table" v-loading="loading">
                <div class="slide_top">
                    <el-upload
                    class="upload-demo"
                    ref="upload"
                    :action="$ajaxUrl+'/uploadImg'" 
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
        <div class="dialog-footer">
            <el-button type="primary" size="small" @click="dialogSure">确 定</el-button>
        </div>
    </el-dialog>
</template>

<script>
import dialogBtnInfo from '@/components/dialogBtnInfo.vue'
export default {
    created () {
        this.getList();
    },
    mounted() {

    },
    data() {
        
        return {
            loading:false,
            fileList:[],
        }
    },
    methods:{
        add:function(file){
            console.log(file)
            if(file.response.err_code=="0"){
                this.$api.post('/slide/add', {url:file.response.data}, r => {
                    console.log(r)
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
                console.log(r)
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
            console.log(res,file,fileList)
            if(res.err_code=="0"){
                this.add(file);
            }else{//上传失败
                this.$message.warning(res.err_msg);
            }
            
        },
        onError(err, file, fileList){
            console.log(err,file,fileList)
            this.$message.warning(err);
        },
        beforeUpload(file){
            var fileArry=file.name.split(".");
            var fileType=fileArry[fileArry.length-1];
            if(fileType!="jpg"&&fileType!="JPG"&&fileType!="png"&&fileType!="PNG"){
                this.$message.warning("只能上传jpg/png格式图片");
                return false;
            }
        },
        onchange(file,fileList){
            console.log(file,fileList)
        },
        //保存的操作
        dialogSure:function(){
            this.dialogInfo.visible=false;
        },
        //取消操作
        dialogCancel:function(){
            this.dialogInfo.visible=false;
        },

    },
    watch:{
        
    },
    components:{dialogBtnInfo},
    props:["dialogInfo"]
}
</script>
<style scoped>
    .slide_top{
        width: 100%;
        overflow: hidden;
        border-bottom: 1px solid #303a62;
        padding-bottom: 15px;
        margin-bottom: 10px;
    }
</style>
