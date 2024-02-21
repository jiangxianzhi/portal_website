<template>
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
    <div>展示部分</div>
  </div>
</template>

<script>
import axios from 'axios'
export default{
    methods: {
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
        console.log('1')
      const formData = new FormData()
      //  this.$refs.file.files[0]就是这个图片
      formData.append('image', this.$refs.fileInput.files[0])
      try {
        const response = await axios.post('http://localhost:8080/carouselPicture/PictureUpload', formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
          onUploadProgress: (progressEvent) => {
            const Percent = parseInt(Math.round((progressEvent.loaded * 100) / progressEvent.total))
            // console.log('progress is ' + Percent)
            // Update the progressPercent data property
            this.progressPercent1 = Percent
            // console.log("count is");
            // console.log(this.count++);
            // console.log('progress is ' + this.progressPercent1)
          }
        })
        console.log(response)
      
      } catch (error) {
        console.error(error)
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
