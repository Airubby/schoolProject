<template>
  <div id="app" ref="app">
  <!--
    <transition name="rotate-fall">
      <router-view v-if="isRouterAlive"/>
    </transition>
    -->
    <router-view v-if="isRouterAlive"/>
  </div>
</template>
<script>
import store from './store/index'
import Vue from 'vue'
import { mapGetters } from 'vuex'
  export default {
    name: 'app',
    provide(){
      return{
        reload:this.reload
      }
    },
    created () {
        
    },
    computed:{
    },
    mounted() {
        this.$tool.wsConnection("",function(result){
          // console.log(result)
          store.dispatch('setwsData',JSON.parse(result.data));
        })

    },
    data(){
      return{
        isRouterAlive:true,
      }
    },
    methods:{
      reload(){
        this.isRouterAlive=false;
        this.$nextTick(function(){
          this.isRouterAlive=true;
        })
      },
    },
    watch:{
     
    }
  }
</script>
<style lang="less">

</style>
