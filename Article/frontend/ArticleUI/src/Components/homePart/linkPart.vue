<template>
  <div>
    <div class="link">
      <div class="subLink">
         <!-- 图片 -->
         <div>
            <img src="../../static/linkPic/1-230320150U6437.jpg" alt="">
         </div>
         <!-- 文字 -->
         <div class="link-word">
             <div class="link-title">公司简介</div>
  
             <div class="link-abstract">
              氘能公司于2019年2月12日在广州南沙成立，注册资本人民币5000万元
             </div>
         </div>
      </div>
      <div class="subLink">
           <!-- 图片 -->
           <div>
            <img src="../../static/linkPic/1-23032014262AD.jpg" alt="">
         </div>
            <!-- 文字 -->
            <div class="link-word">
             <div class="link-title">DTL优势</div>
  
             <div class="link-abstract">
              国内处置退役民航飞机架次最多，价值规模最大的民营企业
             </div>
         </div>
      </div>
      <div class="subLink">
           <!-- 图片 -->
           <div>
            <img src="../../static/linkPic/1-230320151311Q1.jpg" alt="">
         </div>
            <!-- 文字 -->
            <div class="link-word">
             <div class="link-title">公司使命</div>
  
             <div class="link-abstract">
              成为中国领先退役飞机及发动机处置公司，立足于可持续性发展，致力于成为得力的飞行助手，让世界变得更美好
             </div>
         </div>
      </div>
    </div>
    <!-- <div class="underLine"></div> -->
  </div>
</template>

<style>
.link {
  display: flex;
  width: 100vw;
  justify-content: space-between;
  height: 22vw;
  overflow:hidden;
  color:#1754a3;
}
.subLink {
  width: 33%;
  height:inherit;
  /* background-color: #ccc; */
  /* height: inherit; */
  position:relative;
}

.subLink img {
    width:100%;
    height:100%;
    object-fit:cover;
    transition: transform 0.3s ease; /* Add transition for smooth size change with ease effect */

}

.subLink:hover img{
  transform: scale(1.1); /* Increase the size of the image on hover */
}

.underLine{
    height: 12px;
    width:100vw;
    border: 2px solid black;
}
.link-word {
  position: absolute;
  height: 100%;
  width: 100%;
  left:0;
  top:0;
  display: flex;
  justify-content: center;
  flex-direction: column;
  transition: transform 0.3s ease;
}
.subLink:hover .link-word{
  background-color: rgba(0, 0, 0, 0.5);
  transform: scale(1.1);
}

.link-title{
  font-size: 20px;
  display: flex;
  justify-content: center;
  font-weight: bold;
}


/* Default styles for link-abstract */
.link-abstract {
  display: block; /* Show the link-abstract by default */
}

/* Media query for screens with width less than or equal to 768px */
@media (max-width: 768px) {
  .link-abstract {
    display: none; /* Hide the link-abstract when the screen width is <= 768px */
  }
  .link-word {
    font-size: 12px; /* Set the font size of link-word to be smaller */
  }
}
</style>
