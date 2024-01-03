<template>
  <div>
    <!-- 文章显示 -->
    <el-table :data="articleList" style="width: 100%">
      <el-table-column fixed prop="id" label="ID" width="150" />
      <el-table-column prop="title" label="title" width="120" />
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="{ row }">
         <!-- 文章编辑按钮 -->
          <el-button
            link
            type="primary"
            size="small"
            @click="updateArticle(row)"
            >Edit</el-button
          >
          <!-- 文章删除按钮 -->
          <el-button
            link
            type="primary"
            size="small"
            @click="deleteSingleArticle(row.id)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 文章修改弹窗页面 -->
    <el-dialog v-model="dialogVisible" width="80%">
      <wangEditVue :article="choosedArticle"></wangEditVue>
      <div>
        预览
        <div>{{ choosedArticle.title }}</div>
        <div v-html="choosedArticle.content"></div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
import axios from "axios";
import { watch } from "vue";

import wangEditVue from "./wangEdit.vue";
import axiosInstance from "../axiosAbstract/axiosAPI";
export default {
  // 关闭 dailog时从新得到所有文章
  props: ["reGetAllArticle"],

  components: {
    wangEditVue,
  },
  // 挂载前得到所有文章
  beforeMount() {
    this.getAllArticles();
  },

  data() {
    return {
      articleList: [],
      dialogVisible: false,
      choosedArticle: { title: "a", content: "b" },
    };
  },
  watch: {
    reGetAllArticle(newValue, oldValue) {
      // console.log("reGetAllArticle is changed");
      this.getAllArticles();
    },
    // 关闭的时候从新获取文章
    dialogVisible(newValue, oldValue) {
      if (newValue === false) {
        this.getAllArticles();
      }
    },
  },
  methods: {
    // 获取所有文章
    getAllArticles() {
      axios
        .get("http://localhost:8080/article")
        .then((response) => {
          this.articleList = response.data;
          // console.log("response.data is " + response.data);
          if (this.articleList) {
            this.open7();
          } else {
            this.open8();
          }
        })
        .catch((error) => {
          console.error("Error fetching articles", error);
          this.open8();
        });
    },
    // 修改文章
    // 1.得到 文章
    // 2.文章到upload页面显示+按钮自动移动
    updateArticle(row) {
      this.choosedArticle = row;
      // console.log("row");
      // console.log(this.choosedArticle);
      this.dialogVisible = true;
    },

    // 成功获取所有文章
    open7() {
      ElMessage.success("get All Articles success");
    },
    // 获取所有文章失败
    open8() {
      ElMessage.warning("get All Articles failed");
    },
    deleteSingleArticle(id) {
      // 通过id删除文章，两点，本地删除+调用接口删除（就不用重新加载了）
      axiosInstance
        .get("http://localhost:8080/article/" + id)
        .then((response) => {
          if (response.data === 1) {
            this.open9(id);
            // 后端删除成功后，前端进行删除
            this.articleList = this.articleList.filter(
              (item) => item.id !== id
            );
          } else {
            this.open10();
          }
        })
        .catch((error) => {
          console.log(error);
          this.open10();
        });
    },
    open9(id) {
      ElMessage.success("delete id : "+id+"  article success");
    },
    open10() {
      ElMessage.warning("delete this article failed");
    },
  },
};
</script>
