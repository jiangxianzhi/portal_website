<template>
  <div class="home">
   <carousl></carousl>
    <!--  <linkPart></linkPart> -->
    <div class="News">
      <div class="subNews">News</div>
      <ShowPage></ShowPage>
    </div>
  </div>
</template>

<script>
import carousl from "./homePart/carousl.vue";
import linkPart from "./homePart/linkPart.vue";
import ShowPage from "../Views/ShowPage.vue";
export default {
  components: {
    carousl,
    linkPart,
    ShowPage,
  },
};
</script>

<style>
.home {
  background-color: #071727;
}
.News {
  background-color: white;
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.subNews {
  margin-top: 20px;
  font-size: large;
  font-weight: bold;
  text-align: center;
  line-height: 100%;
}
</style>
