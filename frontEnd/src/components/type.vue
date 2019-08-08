<template>
    <div class="btnbox">
        <ul>
            <li @click="change('all')" :class="{'active':typeInfo.roomtype=='all'}">所有</li>
            <li @click="change('classroom')" :class="{'active':typeInfo.roomtype=='classroom'}">教室</li>
            <li @click="change('officeroom')" :class="{'active':typeInfo.roomtype=='officeroom'}">办公室</li>
        </ul>
    </div>
</template>

<script>

export default {
    created () {
        
    },
    mounted() {
        
    },
    data() {
        return {
            
        }
    },
    methods:{
        change:function(type){
            this.typeInfo.roomtype=type;
            this.typeInfo.visible=false;
        }
    },
    watch: {
        'typeInfo.visible':function(val){
            if(val){
                this.$el.style.display="block";
            }else{
                this.$el.style.display="none";
            }
            this.$el.style.transition="all 0.4s ease-in";
        }
    },
    props:["typeInfo"]  
}
</script>
<style lang="less" scoped>
    .btnbox{
        position:absolute;
        right:10px;
        display: none;
        z-index: 999;
        background:#1D2340;
        line-height:32px;
        font-size: 16px;
        text-align:left;
        padding: 10px 0;
        min-width: 100px;
        &:before{
            content: "";
            position: absolute;
            top: -20px;
            right: 10px;
            width: 0;
            height: 0;
            border-style: solid;
            border-width: 10px;
            border-color: transparent transparent #1D2340 transparent;
        }
        li{
            padding: 0 20px;
            cursor:pointer;
        }
        li:hover,li.active{
            color:#4A78FF;
        }
    }
</style>
