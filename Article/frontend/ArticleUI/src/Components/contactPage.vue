<template>
  <div>
    <!-- 图片 -->
    <div class="pic">
      <img
        src="https://c.pxhere.com/photos/6d/9a/mountain_top_summit_peak_sky_horizon_nature_rock_view-681270.jpg!d"
        srcset="
          https://c.pxhere.com/photos/6d/9a/mountain_top_summit_peak_sky_horizon_nature_rock_view-681270.jpg!d
        "
        alt="景观, 性质, 岩, 地平线, 山, 云, 天空, 日出, 日落, 早上, 冒险, 视图, 黎明, 峰, 山脉, 黄昏, 晚间, 高, 自由, 高度, 地形, 山顶, 首脑, 阿尔卑斯山, 最佳, 成功, 成就, 地形, 成功, 地理特征, 大气现象, 山地地貌, 图片素材 In PxHere"
      />
    </div>
    <!-- 内容 -->
    <div class="wrapp2">
      <div class="content">
        <div class="title">Contact Us</div>
        <div class="subContent">
          <div>
            地址： aabbcc
          </div>

          <div>电话： 1663 +852 6762 1615</div>

          <div>邮箱：666@qq.com</div>
        </div>
      </div>
    </div>

    <!-- 留言 -->
    <leaveMessage></leaveMessage>
  </div>
</template>

<script>
import leaveMessage from '@/Components/leavePart/leaveMessage.vue'
export default{
    components:{
        leaveMessage
    }
}
</script>

<style>
.pic {
  display: flex;
  justify-content: center;
  height: auto;
}
.pic img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.content {
  display: flex;
  justify-content: space-between;
  width: 62vw;
  margin-top: 10%;
}
.content div {
  height: 200px;
}
.wrapp2 {
  display: flex;
  justify-content: center;
  background-color: #dbf3fa;
}
.title {
  font-weight: bold;
  color: black;
  width: 10%;
}
.subContent {
  width: 50%;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
}
.subContent div{
    margin-top:10px;
}
</style>
