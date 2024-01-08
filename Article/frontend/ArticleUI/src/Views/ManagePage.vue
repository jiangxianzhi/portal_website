<script>
import controlArticle from "../Components/controlArticle.vue";
import leaveMessgaeManage from "../Components/leavePart/leaveMessgaeManage.vue";
import pageComponet from "@/Components/pagePart/pageComponet.vue";
export default {
  components: {
    controlArticle,
    leaveMessgaeManage,
    pageComponet,
  },
};
</script>

<template>
  <div>
    <!-- 控制页面 -->

    <!-- 文章部分 -->
    <div>
      <controlArticle></controlArticle>
    </div>

    <!-- 留言部分 -->
    <div>
      <leaveMessgaeManage></leaveMessgaeManage>
    </div>

    <!-- 页面部分 -->
    <div>
      <pageComponet></pageComponet>
    </div>
  </div>
</template>

<style></style>
