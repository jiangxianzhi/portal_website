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
            '../../static/carouselPic/landscape-horizon-mountain-snow-cloud-sky-1102604-pxhere.com.jpg',
            import.meta.url
          ).href,
          title: 'a'
        },
        {
          name: 'logo',
          src: new URL(
            '../../static/carouselPic/sea-ocean-horizon-mountain-snow-cloud-7497-pxhere.com.jpg',
            import.meta.url
          ).href,
          title: 'b'
        },
        {
          name: 'logo',
          src: new URL(
            '../../static/carouselPic/water-snow-winter-light-cloud-sky-1201501-pxhere.com.jpg',
            import.meta.url
          ).href,
          title: 'c'
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