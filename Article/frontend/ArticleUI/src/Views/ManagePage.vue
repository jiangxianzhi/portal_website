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
        <router-link to="/managePage/controlArticle" >
       
            Control Article
          
        </router-link>
      </el-menu-item>
      <el-menu-item class="manageComponentSideNavigatorItem" index="2">
        <router-link to="/managePage/leaveMessgaeManage">Leave Message Manage</router-link>
      </el-menu-item>
      <el-menu-item class="manageComponentSideNavigatorItem" index="3">
        <router-link to="/managePage/pageComponet">Page Component</router-link>
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
</style>
