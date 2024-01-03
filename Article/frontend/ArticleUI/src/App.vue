<script>
import topBar from "@/Components/topNavigator/topBar.vue";
import footPart from "./Components/homePart/footPart.vue";
import footPart2 from "@/Components/homePart/footPart2.vue"
export default {
  components: {
    topBar,
    footPart,
    footPart2
  },
  beforeMount() {
    // 提前加载 有什么自定义页面
    this.$store.dispatch("fetchPages");
  },
};
</script>

<template>
  <div>
    <topBar></topBar>

    <el-backtop class="backTop">
 
     
 <el-icon ><Top /></el-icon>

 
</el-backtop>
    <router-view></router-view>

    <!-- <footPart></footPart> -->
<footPart2></footPart2>

  </div>
</template>

<style>
* {
  margin: 0;
  padding: 0;
}
.backTop{
  opacity: 0.5; 
  transition: opacity 0.3s;
}
.backTop:hover{
  opacity: 1;
}
</style>
