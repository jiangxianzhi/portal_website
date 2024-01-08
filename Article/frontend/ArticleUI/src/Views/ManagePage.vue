<script>
import { Search } from "@element-plus/icons-vue";
import { Edit } from "@element-plus/icons-vue";
import uploadPage from "../Components/uploadPage.vue";
import controlPage from "../Components/controlPage.vue";
import leaveMessgaeManage from "../Components/leavePart/leaveMessgaeManage.vue";
import pageComponet from '@/Components/pagePart/pageComponet.vue'
export default {
  components: {
    uploadPage,
    controlPage,
    leaveMessgaeManage,
    pageComponet
  },
  watch:{
dailogValue(newValue,oldValue){
  console.log(newValue)
  if(newValue === false){
    this.fatherPageReGetAllArticle = !this.fatherPageReGetAllArticle;
  }
}  },

  data() {
    return {
      dailogValue: false,
      fatherPageReGetAllArticle:false
    };
  },
  methods: {
    toggleDailog() {
      this.dailogValue = !this.dailogValue;
    },
  
  },
};
</script>

<template>
  <div>
      <!-- 控制页面 -->

<!-- 文章部分 -->
<div>
      <!-- 上传文章按钮 -->
      <div>  this is article part</div>
    <div>
  
      <el-button size="large"  @click="toggleDailog"
        >新建文章<el-icon><Plus /></el-icon
      ></el-button>
    </div>

    <controlPage :reGetAllArticle="fatherPageReGetAllArticle"></controlPage>
        <!-- 上传新文章页面 -->
        <div>
      <el-dialog v-model="dailogValue" width="90%">
        <uploadPage></uploadPage>
      </el-dialog>
    </div>
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
