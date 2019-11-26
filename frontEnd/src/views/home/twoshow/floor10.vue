<template>
    <div class="floor_right_map" id="centermap">
        <img :src="'images/2-10F.png'" usemap="#map" id="mapimg">
        <map name="map" id="map">
            
        </map>
    </div>
</template>

<script>
import map from '../mixin/map'
export default {
    mixins:[map],
    props:["info"],
    created () {
        
    },
    mounted() {
            
    },
    data() {
        return {
            floorData:[],
        }
    },
    methods:{
        
    },
    watch: {
        
    },
    components:{}

 }
</script>


