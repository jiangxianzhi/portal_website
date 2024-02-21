<template>
  <div class="footPartFather">
    <div class="subFootPart">
      <p>Copyright© {{ currentYear }} +companyName All Rights Reserved</p>
    </div>
    <div class="subFootPart">Powered By Author JJKLJJ</div>
  </div>
</template>
<script>
export default {
  mounted() {
    this.currentYear = new Date().getFullYear();
  },
  data(){
    return{
        currentYear:null
    }
  }
};
</script>

<style>
.footPartFather {
  display: flex;
  justify-content: space-between;
  margin-left: 20vw;
  margin-right: 20vw;
  height: 100px;
}
.subFootPart {
    margin-top: 40px;
  line-height: 20px; /* Set the line height equal to the height of the div */
  text-align: center; /* Center the text horizontally */
  font-family:
    Open Sans,
    sans-serif;
  font-size: 85%;
  font-weight: 100;
  color:#40aaff;
}
</style>
