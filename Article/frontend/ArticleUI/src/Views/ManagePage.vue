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


  <!-- <div>

    <div>
      <controlArticle></controlArticle>
    </div>

    <div>
      <leaveMessgaeManage></leaveMessgaeManage>
    </div>

    <div>
      <pageComponet></pageComponet>
    </div>
  </div> -->

<template>
  <div>
   <el-container>
<el-aside width="200px">
  <el-menu>
      <el-menu-item class="manageComponentSideNavigatorItem " index="1" >
        <router-link to="/managePage/controlArticle" class="inheritHeightAndWidth" >
       
            Control Article
          
        </router-link>
      </el-menu-item>
      <el-menu-item class="manageComponentSideNavigatorItem" index="2">
        <router-link to="/managePage/leaveMessgaeManage" class="inheritHeightAndWidth">Leave Message Manage</router-link>
      </el-menu-item>
      <el-menu-item class="manageComponentSideNavigatorItem" index="3">
        <router-link to="/managePage/pageComponet" class="inheritHeightAndWidth">Page Component</router-link>
      </el-menu-item>
<!-- 图片管理 -->
      <el-menu-item class="manageComponentSideNavigatorItem" index="4">
        <router-link to="/managePage/pictureComponet" class="inheritHeightAndWidth">Picture Componet</router-link>
      </el-menu-item>
    </el-menu>
</el-aside>
<el-main>
    <div>
      <router-view></router-view>
    </div>
    </el-main>
   </el-container>
  </div>
</template>

<style>

.manageComponentSideNavigatorItem{
  border-bottom: 1px solid #ccc;
 
}
a {
  text-decoration: none;
  color: black;
}
 
.router-link-active {
  text-decoration: none;
}

.inheritHeightAndWidth{
  height: 100%;
  width:100%;
}
</style>
