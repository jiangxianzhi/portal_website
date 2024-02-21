<script>
import axios from "axios";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      articleList: [],
      specificArticle: {},
      dialogVisible: false,
      searchInput: "",
      tmpArticleList: [],
      searchOption: false
    };
  },
  beforeMount() {
    axios
      .get("http://localhost:8080/article")
      .then((response) => {
        this.articleList.value = response.data;
        console.log(response.data);
        // 暂存方便之后过滤（搜索） deepCopy
        this.tmpArticleList = JSON.parse(
          JSON.stringify(this.articleList.value)
        );
        // console.log("tmp is ", this.tmpArticleList);
        if (this.articleList) {
        } else {
          this.open8();
        }
      })
      .catch((error) => {
        console.error("Error fetching articles", error);
        this.open8();
      });
  },
  methods: {
    changeDialogVisble() {
      this.dialogVisible = !this.dialogVisible;
    },
    open8() {
      ElMessage.warning("get All Articles failed");
    },
    // 传递文章值
    setSpecificArticle(article) {
      this.specificArticle = article;
    },
    // 从html格式到string
    parseHtml(htmlString) {
      const parser = new DOMParser();
      const html = parser.parseFromString(htmlString, "text/html");
      return html.body.textContent || "";
    },
    //advance 搜索方法，判断'ac' 是否在'abbbc'中
     subSet(strA, strB) {
  let arrA = Array.from(strA);
  let arrB = Array.from(strB);

  return arrA.every(char => arrB.includes(char));
}
  },

  watch: {
    searchInput(n, o) {
      console.log("printLn is " + n);
// 这里应该要写判断n的内容


      // 输入变化的时候自动触发过滤
      if (n !== null) {
        console.log(this.searchOption)
      if(!this.searchOption) { 
        let b = this.tmpArticleList.filter((item) => {
          // 转一层格式判断，不然<p></p>就会被判断有p
          let tmpContent = this.parseHtml(item.content);

          // 标题和内容都含有
          return tmpContent.includes(n) || item.title.includes(n);
        });
      
       
        this.articleList.value = b;}
        else{
          var beginTime = +new Date();
          console.log(this.searchOption)
          let b = this.tmpArticleList.filter((item) => {
          // 转一层格式判断，不然<p></p>就会被判断有p
          let tmpContent = this.parseHtml(item.content);

          // 标题和内容都含有
          return this.subSet(n,tmpContent) || this.subSet(n,item.title);
        });
        var endTime = +new Date();
        console.log("排序用时共计"+(endTime-beginTime)+"ms");
        this.articleList.value = b;

        }
      }

      if (n == null) {
        this.articleList.value = this.tmpArticleList;
      }
    },
  },
};
</script>

<template>
  <div>
    <!-- 搜索栏 -->
    <div class="SearchBarFather">
       <!-- 高级搜索开关 -->
      <div class="searchOption">
        <el-checkbox-button v-model="searchOption" label="Advance"   size="large" />
        
      </div>
      <div class="SearchBar">
         
        <el-input v-model="searchInput" class="w-50 m-2" placeholder="search">
          <template #prefix>
            <el-icon class="el-input__icon"><search /></el-icon>
          </template>
        
        </el-input>
       
       
      
          
      </div>
       
    </div>
<!-- 文章显示 部分 -->
    <div class="fatherDiv">
      <!-- 文章目录 -->
      <div class="showArticlePart">
        <div
          v-for="article in articleList.value"
          :key="article.id"
          class="article beautiful-div"
          @click="
            setSpecificArticle(article);
            changeDialogVisble();
          "
        >
          <div>
            <h2>{{ article.title }}</h2>
          </div>
        </div>
      </div>

      <!-- 文章具体页面 -->
      <el-dialog v-model="dialogVisible" width="80%" top="1vw">
        <div>
          <div class="articleSpecificTitle">{{ specificArticle.title }}</div>
          <div  class="articleSpecificContent" v-html="specificArticle.content"></div>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<style scoped>
.fatherDiv {
  display: flex;
  justify-content: center;
}
.showArticlePart {
  display: flex;
  flex-flow: row wrap;
  width: 80%;
}
.article {
  margin-top: 10px;
  flex: 100%;
  border: 1px #ccc;
  transition: transform 0.3s ease;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0); 
}
.article:hover{
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3); 
}
.beautiful-div {
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
  /* Add more styles as needed */
}

.SearchBarFather {
  margin-top:20px;
  display: flex;
  flex-direction: row-reverse;
  
}
.SearchBar {
  width: 20%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  
}
.searchOption{
  margin-right: 20px;
  margin-left: 20px;
  width:10%;
}
.articleSpecificTitle {
  height: 100px;
  font-size: large;
  color: black;
  font-weight: bolder;
  text-align: center;
  line-height: 100%;
}
.articleSpecificContent{
  overflow: hidden;
} 



</style>
