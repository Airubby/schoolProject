<template>
    <div class="loncom_content">
        <web-index v-if="!$store.getters.getDevType"></web-index>
        <mobile-index v-if="$store.getters.getDevType"></mobile-index>
    </div>
</template>

<script>
import webIndex from './webIndex.vue'
import mobileIndex from './mobileIndex.vue'
export default {
    created () {
        // if(/Android|webOS|iPhone|iPad|BlackBerry/i.test(navigator.userAgent)){
        //     this.$router.push({path:'/mobile'});
        // }else{
        //     this.$router.push({path:'/web'});
        // }
    },
    mounted() {
            
    },
    destroyed() {
        
    },
    data() {
        return {
            
        }
    },
    methods:{
        
    },
    watch: {
        
    },
    components:{webIndex,mobileIndex}

 }
</script>


