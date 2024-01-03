<template>
  <!-- 文章新建上传页面 -->
  <div>
        <!-- 提交按钮 -->
        <div class="flex_container">
      <el-button
        type="primary"
        @click="uploadArticle()"
        ref="uploadButton"
        class="toggleButton"
      >
        Upload<el-icon class="el-icon--right"><Upload /></el-icon>
      </el-button>
    </div>
    <!-- 标题 -->
    <div style="display: flex; justify-content: center">
      <el-input
        v-model="title1"
        ref="refInput"
        @keydown.tab.prevent="handleTabPress"
        placeholder="请输入标题"
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
        ref="refEditor"
      />
    </div>


    <!-- <button @click="getFocus">聚焦</button> -->
  </div>
</template>

<script>
import "@wangeditor/editor/dist/css/style.css"; // 引入 css
import {
  onBeforeUnmount,
  ref,
  shallowRef,
  onMounted,
  watch,
  nextTick,
} from "vue";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import axios from "axios";
import { ElMessage } from "element-plus";
import { Upload } from "@element-plus/icons-vue";
import axiosInstance from "../axiosAbstract/axiosAPI";
export default {
  components: { Editor, Toolbar },
  setup() {
    // 点开聚焦标题
    const refInput = ref(null);
    const getFocus = () => {
      nextTick(() => {
        refInput.value.focus();
      });
    };
    onMounted(() => {
      getFocus();
    });
    // tab切换  放弃1205
    const refEditor = ref(null);
    const handleTabPress = (event) => {
      console.log("tab");
      nextTick(() => {
        refInput.value.focus();
      });
    };
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef();

    // 内容 HTML
    const valueHtml = ref("<p></p>");
    const title1 = ref("");

    const toolbarConfig = {};

// 上传
const editorConfig = { MENU_CONF: {} , placeholder: "请输入内容..."}
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

    // 监听newValue，改变了就赋值
    watch(valueHtml, (newValue, oldValue) => {
      article.content = newValue;
      console.log(article);
    });
    // 监听title1，改变了就赋值
    watch(title1, (newValue, oldValue) => {
      article.title = newValue;
    });

    const article = {
      title: "",
      content: "",
    };

    const uploadArticle = async () => {



      if (article.title && article.content) {
        try {
          console.log(article);
          const response = await axiosInstance.post(
            "http://localhost:8080/article",
            article
          );
          // console.log("response.data is" + response.data);
          if (response.data === 1) {
            open5();
            // 上传成功就去除写的标题+内容
            valueHtml.value = "<p></p>";
            title1.value = "";
          } else {
            open6();
          }
          return;
        } catch (error) {
          open6();
          console.error("Error uploading article:", error);
          throw error;
        }
      } else {
        // 判断表单空，输出
        open4();
      }
    };
    // 显示标题+内容为空
    const open4 = () => {
      ElMessage.error("title/content is empty");
    };
    // 显示成功上传
    const open5 = () => {
      ElMessage.success("upload Successful");
    };
    // 显示上传失败
    const open6 = () => {
      ElMessage.warning("upload failed");
    };
    return {
      editorRef,
      valueHtml, //内容
      mode: "simple", // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated,
      uploadArticle,
      title1, //标题
      open4,
      open5,
      open6,
      handleTabPress,
      refInput,
      getFocus,
      refEditor,
    };
  },
};
</script>

<style scoped>
.flex_container {
  display: flex;
  align-items: center; /* This centers the content vertically */
  justify-content: flex-end; /* This centers the content horizontally */
}
.toggleButton {
  left: 20px;
}
</style>
