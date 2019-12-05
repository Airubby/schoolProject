<template>
    <el-dialog :title="dialogInfo.title" :visible.sync="dialogInfo.visible" width="900px" v-dialogDrag class="custom">
        <el-scrollbar style="height:350px;" class="loncom_scrollbar">
            <div class="loncom_public_table">
                <el-form :model="initParams" :rules="rules" ref="ruleForm" label-width="100px" class="demo-initParams">
                    <el-row :gutter="40">
                        <el-col :span="12">
                            <el-form-item label="角色名称" prop="name" size="small">
                                <el-input v-model="initParams.name"></el-input>
                            </el-form-item>
                        </el-col>  
                        <el-col :span="12" v-if="dialogInfo.id!='1'">
                            <el-form-item label="" prop="type" size="small">
                                <el-select v-model="initParams.type">
                                    <el-option label="管理员" value="1"></el-option>
                                    <el-option label="普通用户" value="2"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12" v-if="dialogInfo.id!='1'">
                            <el-form-item label="是否启用" prop="state" size="small">
                                <el-radio-group v-model='initParams.state'>
                                    <el-radio label='1'>启用</el-radio>
                                    <el-radio label='0'>禁用</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item  label="描述">
                                <el-input v-model="initParams.details" size="small"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="24" v-if="dialogInfo.id!='1'">
                            <el-form-item label="权限">
                                <el-tree
                                    ref="tree"
                                    show-checkbox
                                    :data="tree_data"
                                    @check-change="changeCheck"
                                    node-key="id"
                                    >
                                </el-tree>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
        </el-scrollbar>
        <dialogBtnInfo v-bind:dialogInfobtn="dialogInfo" v-on:dialogSure="dialogSure()"></dialogBtnInfo>
    </el-dialog>
</template>

<script>
import dialogBtnInfo from '@/components/dialogBtnInfo.vue'
export default {
    created () {
        if(this.dialogInfo.id){
            this.getInfo(this.dialogInfo.id)
        }else{
            this.getTree();
        }
    },
    mounted() {

    },
    data() {
        return {
            initParams:{
                id:"",
                name:'',
                type:'',
                state:'1',
                details:'',
            },
            rules:{
                name:[
                    { required: true, message: "非空项", trigger: 'blur' },
                ],
                type:[
                    { required: true, message: "非空项", trigger: 'change' },
                ],
            },
            tree_data:[],
            treechecked:[],
            treehalfchecked:[],
        }
    },
    methods:{
        async getInfo(id){
            this.loading=true;
            await this.getTree();
            await this.getRole();
            this.$api.post("/role/details",{id:id},r=>{
                this.loading=false;
                
                if(r.err_code=='0'){
                    this.initParams=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        getRole:function(){
            this.$api.post('/role/rolemenu',{id:this.dialogInfo.id},r=>{
                
                if(r.err_code=="0"){
                    if(r.data &&r.data.length>0 && this.dialogInfo.id!='1'){
                        this.$refs.tree.setCheckedKeys(r.data);
                    }
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        getTree:function(){
            this.$api.post('/role/sysmenutree',{},r=>{
                
                if(r.err_code=="0"){
                    this.tree_data=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
            })
        },
        changeCheck:function(){
            this.treechecked=this.$refs.tree.getCheckedNodes();
            this.treehalfchecked  = this.$refs.tree.getHalfCheckedNodes();
        },
        dialogSure:function(){
            this.$refs['ruleForm'].validate((valid) => {
                if(valid){ //验证通过
                    this.loading=true;
                    let url=this.dialogInfo.id?"/role/update":"/role/add";
                    let item=[];
                    for(let i=0;i<this.treechecked.length;i++){
                        if(this.treechecked[i].map&& this.treechecked[i].map.vids){
                            if(item.length>0){
                                let itemBoolean = false;
                                item.map(item1=>{
                                    if(item1.sysmenuid===this.treechecked[i].map.vid){
                                        let arr1 =item1.roperid.split(',');
                                        arr1.push(this.treechecked[i].map.vids);
                                        arr1 = arr1.filter(i=>i);
                                        item1.roperid = arr1.join(',');
                                        itemBoolean = true;
                                    }
                                })
                                if(!itemBoolean){
                                    item.push({
                                        sysmenuid: this.treechecked[i].map.vid,
                                        roperid: this.treechecked[i].map.vids,
                                        half: false
                                    });
                                }
                            }else{
                                item.push({
                                    sysmenuid: this.treechecked[i].map.vid,
                                    roperid: this.treechecked[i].map.vids,
                                    half: false
                                });
                            }

                        }else if(this.treechecked[i].map&& this.treechecked[i].map.code){
                            item.push({
                                sysmenuid: this.treechecked[i].id,
                                roperid: this.treechecked[i].map.vid,
                                half: false
                            });
                        }else{
                            item.push({sysmenuid:this.treechecked[i].id,roperid:"",half:false});
                        }
                    }
                    for(let i=0;i<this.treehalfchecked.length;i++){
                        if(item.length>0){
                            if(item.filter(it=>it.sysmenuid===this.treehalfchecked[i].id).length===0){
                                item.push({sysmenuid:this.treehalfchecked[i].id,roperid:"",half:true});
                            }
                        }else{
                            item.push({sysmenuid:this.treehalfchecked[i].id,roperid:"",half:true});
                        }

                    }
                    this.initParams.item=item;

                    this.$api.post(url,this.initParams, r => {
                        
                        this.loading=false;
                        if(r.err_code=="0"){
                            this.dialogInfo.visible=false;
                            this.$emit("backInfo");
                            this.$message.success(r.err_msg);
                        }else{
                            this.$message.warning(r.err_msg);
                        }
                    });
                }
            })
        },

    },
    watch:{
    },
    components:{dialogBtnInfo},
    props:["dialogInfo"]
}
</script>

