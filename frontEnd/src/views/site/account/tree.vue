<template>
    <el-dialog title="管理域" :visible.sync="dialogInfo.visible" width="600px" v-dialogDrag class="custom" :append-to-body="true">
        <el-scrollbar style="height:350px;" class="loncom_scrollbar" v-loading="loading">
            <div class="loncom_public_table">
                <el-form :model="ruleForm" :rules="formRules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-tree show-checkbox ref="tree" node-key="id" 
                    :data="treedata" 
                    :props="{
                    children: 'children',
                    label: 'name'
                    }"
                    @check-change="selsetchangeTree"></el-tree>
                    <el-form-item prop="addrorrole">
                        <el-input v-model="ruleForm.addrorrole" size="small" style="display:none;"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </el-scrollbar>
        <dialogBtnInfo v-bind:dialogInfobtn="dialogInfo" v-on:dialogSure="dialogSure()"></dialogBtnInfo>
    </el-dialog>
</template>

<script>
import dialogBtnInfo from '@/components/dialogBtnInfo.vue'
import store from '@/store/index'
export default {
    created () {
        this.getArea();
    },
    mounted() {

    },
    data() {
        return {
            loading:false,
            treedata:[],
            ruleForm:{
                addrorrole:"",
                addrname_list:[],
            },
            formRules:{
                addrorrole:[
                    { required: true, message: '请选择', trigger: 'change' },
                ]
            },
            
        }
    },
    methods:{
        getArea:function(){
            this.loading=true;
            this.$api.get("/service/tree",{},r =>{
                console.log(r)
                this.loading=false;
                if(r.err_code=="0"){
                    this.treedata=r.data;
                    this.$nextTick(function(){
                        this.$refs.tree.setCheckedKeys(this.dialogInfo.addrorrole.split(","));
                    })
                }
            });
        },
        selsetchangeTree:function(data,node){
            this.ruleForm.addrname_list=this.$refs.tree.getCheckedNodes(true);
            this.ruleForm.addrorrole=this.$refs.tree.getCheckedKeys(true).toString();
        },
        // setCheckedKeys:function() {
        //     this.$refs.tree.setCheckedKeys(this.get_list);
        // },
        //保存的操作
        dialogSure:function(){
            this.$refs['ruleForm'].validate((valid) => {
                if (valid) {
                    this.dialogInfo.addrorrole=this.ruleForm.addrorrole;
                    let addrname=[];
                    for(let i=0;i<this.ruleForm.addrname_list.length;i++){
                        addrname.push(this.ruleForm.addrname_list[i].map.topname+"_"+this.ruleForm.addrname_list[i].name);
                    }
                    this.dialogInfo.addrname=addrname.toString();
                    this.dialogInfo.visible=false;
                } 
            });
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

