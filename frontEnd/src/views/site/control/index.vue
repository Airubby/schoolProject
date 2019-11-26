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
        
    },
    mounted() {
        
    },
    data() {
        return {
            
        }
    },
    methods:{
        
    },
    watch:{
        
    },
    components:{
        webIndex,mobileIndex
    },
}
</script>

<style scoped>
    
</style>