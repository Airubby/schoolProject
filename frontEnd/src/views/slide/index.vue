<template>
    <div class="loncom_content">
        <home-top></home-top>
        <div class="loncom_index_con" v-loading="loading">
            <router-link to="/" style="position:absolute;top:-5px;left:30px;">返回首页</router-link>
            <div id="swiper-container" class="swiper-container">
                <div class="swiper-wrapper" id="swiper-wrapper">
               
                    <div class="swiper-slide" style="background: #141835;"><slide-one :topInfo="topData"></slide-one></div>
                    <div class="swiper-slide" style="background: #141835;"><slide-two :topInfo="topData"></slide-two></div>
                    <div class="swiper-slide" style="background: #141835;"><slide-three></slide-three></div>
                    <template  v-for="item in slideData">
                    <div class="swiper-slide swiper-slide-add" style="background: #141835;position: relative;">
                        <img :src="'static/upload/'+item.url">
                    </div>
                    </template>
                </div>
                <!-- 如果需要导航按钮 
                <div>
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-button-next"></div>
                </div>-->
                <!-- 如果需要分页器 
                <div class="swiper-pagination"></div>-->
            </div>
        </div>
    </div>
</template>

<script>
import slideOne from './slideOne.vue'
import slideTwo from './slideTwo.vue'
import slideThree from './slideThree.vue'
import homeTop from '@/components/homeTop.vue'
export default {
    created () {
        let _this=this;
        this.getList();
        
    },
    mounted() {
        this.getSlide();
        let _this=this;
        this.swiper=new this.$Swiper('#swiper-container', {
            autoplay: 0,
            spaceBetween: 0,
            slidesPerView: 1,
            speed: 3000,
            effect: 'fade',
        })
        this.slidetimer=setInterval(function(){
            let slide=_this.$el.querySelectorAll('.swiper-slide');
            for(let i=0;i<slide.length;i++){
                slide[i].style.transition= "all 0.5s ease-in-out";
                slide[i].style.opacity=0;
            }
            let random = Math.floor(Math.random()*(slide.length));
            slide[random].style.transition= "all 1.2s ease-in-out";
            slide[random].style.opacity=1;
        },30000)
    },   
    destroyed() {
        clearInterval(this.slidetimer);
        this.slidetimer='';
        clearTimeout(this.timer);
        this.timer='';
        clearTimeout(this.stimer);
        this.stimer='';
    }, 
    data() {
        return {
            loading:false,
            topData:{},
            slideLength:3,
            slideData:[],
            swiper:'',
            slidetimer:'',
            timer:'',
            stimer:'',
        }
    },
    methods:{
        getSlide:function(){
            this.$api.get('/slide/query', {}, r => {
                
                if(r.err_code=="0"){
                    this.slideData=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
                let _this=this;
                this.stimer=setTimeout(function(){
                    _this.getSlide();
                },300000)
            });
        },
        getList:function(){
            // this.search=[this.$tool.Format("yyyy-MM-dd 00:00:00",new Date()),this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date())]
            // let startTime=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date(new Date().getTime()-3600*1000*24*1));
            let startTime=this.$tool.Format("yyyy-MM-dd 00:00:00",new Date());
            let endTime=this.$tool.Format("yyyy-MM-dd hh:mm:ss",new Date());
            this.loading=true;
            this.$api.post('/service/topInfo', {startTime:startTime,endTime:endTime}, r => {
                this.loading=false;
                if(r.err_code=="0"){
                    this.topData=r.data;
                }else{
                    this.$message.warning(r.err_msg);
                }
                let _this=this;
                this.timer=setTimeout(function(){
                    _this.getList();
                },15000)
            })
        },
        enterHome:function(){
            this.$router.push({path:'/'});
        }
    },
    watch: {
        slideData:function(val,oldval){
            this.$nextTick(function(){
                /*现在数据已经渲染完毕*/
                this.swiper.init();
            })
        },
    },
    components:{slideOne,slideTwo,slideThree,homeTop}

 }
</script>


<style scoped lang="less">
    .loncom_index_con{
        padding-top: 26px;
    }
    .swiper-container{
        width: 100%;
        height:100%;
    }
    .swiper-slide-add{
        height: calc(100% - 26px) !important;
    }
    .swiper-slide-add img{
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: auto;
        max-width: 100%;
        max-height: 100%;
    }
    
</style>
