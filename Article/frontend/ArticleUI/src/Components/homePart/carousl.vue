<template>
  <div>
    <!-- 响应式布局 -->
    <el-carousel :height="fitHeight">
      <el-carousel-item v-for="item in imgList" :key="item">
        <img :src="item.name" ref="imageRef" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import axios from "axios";

export default {
  // 实现轮播图等比缩放
  mounted() {
    window.addEventListener("resize", this.updateWidth);
  },
  unmounted() {
    window.removeEventListener("resize", this.updateWidth);
  },
  beforeMount() {
    // 加载图片
    this.get_All_carousel_Picture_Which_Status_Equal_To_1();
  },

  methods: {
    updateWidth() {
      this.fitHeight = document.body.clientWidth * (626 / 1782) + "px";
      // 这里我还想写成获取图片的长高
    },
    // 得到所有status = 1的图片
    get_All_carousel_Picture_Which_Status_Equal_To_1() {
      axios
        .get("http://localhost:8080/carouselPicture")
        .then((response) => {
           // 加载所有图片
          //  有正在使用的图片
          let carouselPicture_status_equal_to_1 = response.data.filter(
            (item) => item.status === 1
          );
          if (carouselPicture_status_equal_to_1.length > 0) {
           
            // 格式调整
         
            this.imgList = carouselPicture_status_equal_to_1.map((item) => {
              return {
                ...item,
                name: `http://localhost:8080/carouselImages/${item.name}`,
              };
            });
          }
        })
        .catch((error) => {
          console.error("Error fetching carousel picture", error);
        });
    },
  },

  data() {
    return {
      fitHeight: null,
// 连接不到就用前端自己的
      imgList: [
        {
          name: new URL(
            "../../static/carouselPic/landscape-horizon-mountain-snow-cloud-sky-1102604-pxhere.com.jpg",
            import.meta.url
          ).href,
        },
        {
          name: new URL(
            "../../static/carouselPic/sea-ocean-horizon-mountain-snow-cloud-7497-pxhere.com.jpg",
            import.meta.url
          ).href,
        },
        {
          name: new URL(
            "../../static/carouselPic/water-snow-winter-light-cloud-sky-1201501-pxhere.com.jpg",
            import.meta.url
          ).href,
        },
      ],
    };
  },
};
</script>
<style scoped>
.demonstration {
  color: var(--el-text-color-secondary);
}

img {
  width: 100%;
  height: inherit;
}
</style>
