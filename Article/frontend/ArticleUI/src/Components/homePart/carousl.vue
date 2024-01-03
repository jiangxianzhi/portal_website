<template>
   

     <div>
        <!-- 响应式布局 -->
        <el-carousel :height="fitHeight">
            <el-carousel-item v-for="item in imgList" :key="item">
      <img :src="item.src"  ref="imageRef" />
    </el-carousel-item>
    </el-carousel>
     </div>
 
  </template>

  <script>

export default {
    // 实现轮播图等比缩放
    mounted(){
        window.addEventListener('resize',this.updateWidth)
    },
    unmounted(){
        window.removeEventListener('resize', this.updateWidth);
    },
 methods:{
    updateWidth(){
        this.fitHeight =document.body.clientWidth*(626/1782)+'px'
        // 这里我还想写成获取图片的长高
    
    }
 },
   
    data() {
    return {
       
        fitHeight: null,
        
      imgList: [
        {
          name: 'lj',
          src: new URL(
            '../../static/carouselPic/pexels-irewolede-1389781.jpg',
            import.meta.url
          ).href,
          title: 'a'
        },
        {
          name: 'logo',
          src: new URL(
            '../../static/carouselPic/pexels-kelly-2382849.jpg',
            import.meta.url
          ).href,
          title: 'bg'
        },
        {
          name: 'logo',
          src: new URL(
            '../../static/carouselPic/轮播图.png',
            import.meta.url
          ).href,
          title: '轮播图.png'
        },
      ]
    }
  }
  
 
}
</script>
<style scoped>
.demonstration {
  color: var(--el-text-color-secondary);
}


img{
    width:100%;
    height:inherit;
}
</style>