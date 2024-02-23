<template>
  <!-- 图片上传大小在yaml里面设置 -->
  <div>
   
    <div>
     
      <div class="dragSquare" @drop="handleDrop" @dragover.prevent>
        <div>
            <div class="uploadIcon">
                <el-icon :size="100" color="#ccc"><UploadFilled /></el-icon>
            </div>
       <div  >
        拖拽到此处也可上传
       </div>
       <div class="uploadPicButton" @click="uploadFile">
  上传图片
  <input type="file" ref="fileInput" style="display: none"  accept="image/*" @change="handleFileUpload">
  <!-- @change只有图片改变的时候才触发 -->
</div>
        </div>
      </div>
    </div>

    <div>
      
    
    
        <!-- 图片显示 -->
        <el-table :data="carouselPictureList" style="width: 100%">
      <el-table-column fixed prop="id" label="ID" width="100" />
      <el-table-column prop="name" label="name" width="100" />
      <el-table-column prop="preivew" label="preview" width="120">
      <template #default="{ row }">
        <span v-if="row.name">
          <img :src="`http://localhost:8080/carouselImages/${row.name}`" alt="Preview" style="max-width: 100px; max-height: 100px;" />
        </span>
        <span v-else>No preview available</span>
      </template>
    </el-table-column>
      <el-table-column prop="status" label="status" width="120">
      <template #default="{ row }">
        <span v-if="row.status === 0">未选择</span>
        <span v-else>正在使用</span>
      </template>
    </el-table-column>
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="{ row }">
          <!-- 图片修改状态按钮 -->
          <el-button
            link
            type="primary"
            size="small"
            @click="changeStatus(row)"
            >Edit Status</el-button
          >
          <!-- 图片删除按钮 -->
          <el-button
            link
            type="primary"
            size="small"
            @click="deleteSingleCarouselPicture(row.id)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    
    
    </div>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
import axios from 'axios'
export default{
    // 挂载前得到所有carouselPicture
    beforeMount() {
    this.getAllcarouselPicture();
  }
  ,
  data(){
    return{
      carouselPictureList: null,
    }
  }
  ,
    methods: {  
      // 改变图状态
      changeStatus(row){
        let tmp ;
        if(row.status ===1){
         tmp =0;
        }else{
         tmp =1;
        }
        axios
        .get("http://localhost:8080/carouselPicture/changeStatusById/"+row.id+"?status="+tmp)
        .then((response) => {
         if(response.data===1){
         
          ElMessage.success("改变成功")
          
          this.getAllcarouselPicture();
         }else{
          ElMessage.warning("改变失败")
         }
       
         
        })
        .catch((error) => {
          console.error("Error fetching articles", error);
          ElMessage.warning("改变失败")
        });
      },
      
  
      deleteSingleCarouselPicture(id){
        axios
        .get("http://localhost:8080/carouselPicture/"+id)
        .then((response) => {
         if(response.data===1){
          ElMessage.success("删除成功")
          // 删除成功，刷新
          this.getAllcarouselPicture();
         }else {
          ElMessage.warning("删除失败")
         }
        
          // console.log("response.data is " + response.data);
         
        })
        .catch((error) => {
          ElMessage.warning("删除失败")
          console.error("Error fetching articles", error);
         
        });
    }
      ,
      // 获取所有图
    getAllcarouselPicture() {
      axios
        .get("http://localhost:8080/carouselPicture")
        .then((response) => {
          this.carouselPictureList = response.data;
          // console.log("response.data is " + response.data);
          ElMessage.success("获取所有成功")
        })
        .catch((error) => {
          console.error("Error fetching articles", error);
          ElMessage.warning("获取所有失败")
         
        });
    },
// 点击按钮上传
  uploadFile() {
    this.$refs.fileInput.click();
  },
//   拖拽上传
handleDrop(event) {
      event.preventDefault();
      
      this.$refs.fileInput.files = event.dataTransfer.files;
      this.handleFileUpload();
    }
,

      // 上传图片
      async handleFileUpload() {
        console.log('trigger drag upload function')
      const formData = new FormData()
      //  this.$refs.file.files[0]就是这个图片
      formData.append('image', this.$refs.fileInput.files[0])
      try {
        const response = await axios.post('http://localhost:8080/carouselPicture/PictureUpload', formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
          // 进度条
          onUploadProgress: (progressEvent) => {
            const Percent = parseInt(Math.round((progressEvent.loaded * 100) / progressEvent.total))
        
            this.progressPercent1 = Percent
      
          }
        })
        ElMessage.success("上传成功")
        // 刷新
        this.getAllcarouselPicture();
      
      } catch (error) {
        console.error(error)
        ElMessage.warning("上传失败")
      }
    }
}
}
</script>

<style scoped>
.dragSquare {
  height: 200px;
  width: 200px;
  border: 2px dashed #ccc;

  display: flex;
  justify-content: center;
  align-items: center;
}
.dragSquare  div {
    margin: 20px;
}
.uploadPicButton{
    height: 50px;
  width: 100px;
 
  display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 161, 214, 1);
    color: white;
    cursor: pointer;
}
.uploadIcon{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50px;

   
}
</style>
