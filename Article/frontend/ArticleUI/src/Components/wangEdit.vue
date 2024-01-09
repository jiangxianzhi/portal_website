<template>
  <div>
      <!-- 保存按钮 -->
      <el-button
      type="primary"
      @click="updateArticle()"
      ref="uploadButton"
      class="toggleButton"
    >
      update<el-icon class="el-icon--right"></el-icon>
    </el-button>
    <!-- 标题 -->
    <div style="display: flex; justify-content: center">
      <el-input
        v-model="title1"
        placeholder="请输入标题"
        ref="input1"
        @keydown.tab.prevent="handleTabPress"
        clearable
      />
    </div>

    

    <!-- 内容 -->
    <div style="border: 1px solid #ccc">
      <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editorRef"
        :defaultConfig="toolbarConfig"
        :mode="mode"
      />
      <Editor
        style="height: 500px; overflow-y: hidden"
        v-model="valueHtml"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="handleCreated"
      />
    </div>
  
  </div>
</template>

<script>
import "@wangeditor/editor/dist/css/style.css"; // 引入 css

import { onBeforeUnmount, ref, shallowRef, watch, onBeforeUpdate } from "vue";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import axios from "axios";
import { ElMessage } from "element-plus";
import axiosInstance from "../axiosAbstract/axiosAPI";
export default {
  props: ["article"],
  // 接收父传子

  components: { Editor, Toolbar },
  setup(props) {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef();

    // 监听aritcle
    watch(
      () => props.article,
      (newArticle, oldArticle) => {
        valueHtml.value = props.article.content;
        title1.value = props.article.title;
        id.value = props.article.id;
        // console.log("New article value:", newArticle);
      }
    );

    // 内容 HTML
    const valueHtml = ref("<p></p>");

    // 标题
    const title1 = ref("");

    // id
    const id = ref(0);
    //  初始化
    valueHtml.value = props.article.content;
    title1.value = props.article.title;
    id.value = props.article.id;

    const article1 = {
      title: title1.value,
      content: valueHtml.value,
      id: id.value,
    };
    console.log("article1 id = "+article1.id);

    const toolbarConfig = {};
    // 上传图片
    const editorConfig = {  MENU_CONF: {} ,placeholder: "请输入内容..." };
    editorConfig.MENU_CONF['uploadImage'] = {
      server: 'http://localhost:8080/api/PictureUpload',
     
      fieldName: 'picture',

      onBeforeUpload(file) {
        console.log('onBeforeUpload', file)

        return file // will upload this file
        // return false // prevent upload
      },
      onProgress(progress) {
        console.log('onProgress', progress)
      },
      onSuccess(file, res) {
        console.log('onSuccess', file, res)
      },
      onFailed(file, res) {
        alert(res.message)
        console.log('onFailed', file, res)
      },
      onError(file, err, res) {
        alert(err.message)
       

        console.log("file is",file)
        console.log("err is ",err)
        console.log("res is ",res)
      },

   
    }
    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value;
      if (editor == null) return;
      editor.destroy();
    });

    const handleCreated = (editor) => {
      editorRef.value = editor; // 记录 editor 实例，重要！
    };
    // 上传修改
    const updateArticle = async () => {
      try {
        article1.title = title1.value;
        article1.content = valueHtml.value;
        article1.id = id.value;
        const response = await axiosInstance.post(
          "http://localhost:8080/article/update",
          article1
        );
        // console.log("respons is " + response.data);
        if (response.data === 1) {
          open5();
          valueHtml.value =   "<p></p>";
          title1.value = " ";
          id.value = null;
        } else {
          open6();
   
        }
      } catch (error) {
        open6();
        throw error;
      }
    };

    // 显示成功上传
    const open5 = () => {
      ElMessage.success("update Successful");
    };
    // 显示上传失败
    const open6 = () => {
      ElMessage.warning("update failed");
    };

    return {
      editorRef,
      valueHtml,
      mode: "default", // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated,
      title1,
      updateArticle,
      open5,
      open6,
      article1,
    };
  },
};
</script>
