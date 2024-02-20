<template>
  <div>
 
    <!-- 新建页面的弹窗 -->
    <div>
      <div>
        <el-button size="large" @click="toggleDailog"
          >新建<el-icon><Plus /></el-icon
        ></el-button>
      </div>
      <el-dialog v-model="dailogValue" width="90%">
        <pageUpload></pageUpload>
      </el-dialog>
    </div>
    <!-- 管理页面部分 -->
    <div>
      <!-- 浏览部分 -->
      <el-table :data="$store.state.pageData" style="width: 100%">
        <el-table-column
          label="ID"
          fixed
          prop="id"
          width="150"
        ></el-table-column>
        <el-table-column
          label="title"
          prop="title"
          width="150"
        ></el-table-column>
        <el-table-column
          label="parentId"
          prop="parentId"
          width="150"
        ></el-table-column>
        <el-table-column fixed="right" label="Operations" width="120">
          <!-- 编辑按钮 -->
          <template #default="{ row }">
            <el-button link type="primary" size="small" @click="updatePage(row)"
              >Edit</el-button
            >
            <!-- 删除按钮 -->
            <el-button link type="primary" size="small" @click="deletePage(row)"
              >Delete</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- 树状结构 -->
      <pageTreeStructure class="pageTreeStructureStyle"></pageTreeStructure>
    </div>

    <!-- 修改页面的弹窗 -->
    <div>
      <el-dialog v-model="updateDialogValue" width="90%">
        <pageUpdate :page="choosedPage"></pageUpdate>
      </el-dialog>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import pageUpload from "./pageUpload.vue";
import pageUpdate from "./pageUpdate.vue";
import { useStore } from "vuex";
import axiosInstance from "../../axiosAbstract/axiosAPI";
import { ElMessage } from "element-plus";
import pageTreeStructure from "@/Components/pagePart/pageTreeStructure.vue";
const store = useStore();

const dailogValue = ref(false);

const updateDialogValue = ref(false);

const choosedPage = ref({});

function toggleDailog() {
  dailogValue.value = !dailogValue.value;
  //每次打开新建 头节点初始化为1，因为修改部分可能会将他变为其他
  if (dailogValue.value === true) {
    store.commit("changeHeadNode", 1);
  }
}
function deletePage(item) {
  store.dispatch("deletePage", item.id);
}

function open9(id) {
  ElMessage.success("delete id : " + id + "  page success");
}
function open10(id) {
  ElMessage.warning("delete page failed");
}
// 修改文章
function updatePage(row) {
  this.choosedPage = row;

  updateDialogValue.value = !updateDialogValue.value;
}
</script>

<style>
.pageTreeStructureStyle  {
  width: 66vw;
  margin-top: 20px;
 
}
.pageTreeStructureStyle > div {
  border: 1px solid #ccc !important;
}
</style>
