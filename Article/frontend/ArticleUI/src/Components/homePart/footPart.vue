<template>
  <div>
    <div class="father">
      <div class="wrapper">
        <div class="inner">
          <div></div>
          <div class="innerPic">
            <img src="../../static/logo/element-plus-logo.svg" alt="" />
          </div>
          <button
            class="beautiful-button"
            @click="routeTo('')"
          >
            Contact Us
          </button>
        </div>

        <div class="inner">
          <div></div>
          <div class="innerHead">homePage</div>
          <div class="innerSub">About</div>
          <div class="innerSub">a</div>
          <div class="innerSub">b</div>
          <div class="innerSub">c</div>
        </div>
        <div class="inner">
          <div></div>
          <div class="innerHead">a</div>
          <div>b</div>
          <div>c</div>
          <div>d</div>
          <div>e</div>
        </div>
        <div class="inner"></div>
      </div>
    </div>

    <div class="footer-info">
      <span
        >Copyright © {{ currentYear }}  版权所有
      </span>

      <span
        >备案号:<a
          href="https://beian.miit.gov.cn/"
          rel="nofollow"
          target="_blank"
          >-1</a
        ></span
      >
    </div>
  </div>
</template>
<script  >
import { useRouter } from "vue-router";
export default{
mounted(){
  this.currentYear = new Date().getFullYear();
}
  ,
  methods:{
      routeTo(path){
        this.$router.push(path)
      }
  },

  data(){
    return {
      currentYear: null,

    }
  }
}

</script>
<style>
.beautiful-button {
  background-color: rgba(
    0,
    0,
    0,
    0
  ); /* Change this to your preferred color and opacity */
  color: white; /* Change this to your preferred text color */
  border: 2px solid #0278ca;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
  border-radius: 12px;
  height: 58px;
}

.beautiful-button:hover {
  background-color: rgba(
    0,
    0,
    0,
    0.8
  ); /* Change this to your preferred hover color and opacity */
  color: white;
}
* {
  margin: 0;
  padding: 0;
}

.father {
  margin-top: 50px;
  width: 100vw;
  background-color: #071727;
  display: flex;
  justify-content: center;
}

.wrapper {
  background-color: #071727;
  width: 62%;
  height: auto;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  flex-wrap: wrap;
}

.inner {
  width: 200px;
  height: 300px;
  background-color: #071727;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.inner div {
  text-align: center;
  height: 20%;
  line-height: 100%;
}

.innerPic {
  height: 100px;
  width: 200px;
}
.innerPic img {
  height: 100%;
  width: 100%;
  object-fit: cover;
}

.footer-info {
  background-color: #071727;
  color: white;
  height: 51px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  padding-left: var(--wp--preset--spacing--edge-space, 80px);
  padding-right: var(--wp--preset--spacing--edge-space, 80px);

  height: 100px; /* Set a specific height for the container to see the vertical centering */
}

.footer-info a {
  color: white;
}

.innerHead {
  font-size: large;
  font-weight: bold;
}
.innerSub {
  font-weight: lighter;
}
</style>
