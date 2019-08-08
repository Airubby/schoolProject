<template>
    
</template>

<script>

export default {
    created () {
        if(/Android|webOS|iPhone|iPad|BlackBerry/i.test(navigator.userAgent)){
            this.$router.push({path:'/mobile'});
        }else{
            this.$router.push({path:'/web'});
        }
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
    components:{}

 }
</script>


