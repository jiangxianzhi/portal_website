<template>
  <div>
    <!-- 图片 -->
    <div class="pic">
      <img :src="randomImagePath" alt="Random Image" />
    </div>
    <!-- 内容 -->

    <div class="FatherContent1">
      <div v-for="item in $store.state.pageData" :key="item.id">
        <div v-if="item.id == $route.params.id">
          <div class="pageContentPart">
            <div class="pageContentPartTitle">{{ item.title }}</div>
            <div v-html="item.content" class="pageContentPartSubContent"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      candidatePaths: [
        "https://c.pxhere.com/photos/84/67/wing_plane_flying_airplane_sky_airplanes_clouds-1245082.jpg!d",
        "https://c.pxhere.com/photos/5d/c2/cloud_sky_cloudscape_cloudy_mid_air-19505.jpg!d",
        "https://c.pxhere.com/photos/38/64/table_wall_minimalist-6910.jpg!d",
      ], // Add more candidate paths as needed
      randomImagePath: "",
    };
  },
  created() {
    this.randomImagePath = this.getRandomImagePath();
  },
  beforeUpdate() {
    this.randomImagePath = this.getRandomImagePath();
  },
  methods: {
    // 随机切换图片
    getRandomImagePath() {
      const randomIndex = Math.floor(
        Math.random() * this.candidatePaths.length
      );
      return this.candidatePaths[randomIndex];
    },
  },
};
</script>

<style>
.FatherContent1{
  display: flex;
  justify-content: center;
}
.pageContentPart {
  display: flex;
  justify-content: space-between;
  width: 80vw;
  margin-left: 5vw;
  margin-right: 5vw;
}
@media (max-width: 900px) {
  .pageContentPart {
    flex-direction: column; /* Change flex direction to column */
  }
  .pageContentPartTitle {
    border-right: none !important;
    border-bottom: 1px solid #ccc;
  }
}
.pageContentPartTitle {
  flex: 1;
  text-align: center;
  border-right: 1px solid #ccc; /* Gray divider line */
  font-size: 24px; /* Adjust the font size as needed */
  font-weight: bold; /* Make the font bold */
}
.pageContentPartSubContent {
  flex: 3;

}

.pic {
  display: flex;
  justify-content: center;
  height: 30vw;
}
.pic img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}


</style>
