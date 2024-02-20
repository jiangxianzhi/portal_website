<template>
  <div>

    <!-- 显示留言 -->
    <div>
        <el-table :data="leaveMessageList" style="width:100%">
        <el-table-column label="ID" fixed prop="id" width="150"></el-table-column>
    <el-table-column label="name" prop="name" width="150"></el-table-column>
    <el-table-column label="phone" prop="phone" width="150"></el-table-column>
    <el-table-column label="email" prop="email" width="150"></el-table-column>
    <el-table-column label="message" prop="message" width="150"></el-table-column>

    <el-table-column fixed="right" label="Operations" width="120">
        <template #default="{ row }">
       
          <!-- 文章删除按钮 -->
          <el-button
            link
            type="primary"
            size="small"
            @click="deleteSingleLeaveMessage(row.id)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
</el-table>
    </div>
  </div>
</template>

<script>

import axiosInstance from "../../axiosAbstract/axiosAPI";
import { ElMessage } from "element-plus";

export default {
  data() {
    return {
      leaveMessageList: null,
    };
  },
  beforeMount() {this.getAllLeaveMessage();},
  methods: {
 
    // 删除成功
    open9(id) {
      ElMessage.success("delete id : " + id + "   success");
    },
    // 删除失败
    open10() {
      ElMessage.warning("delete this failed");
    },
    // 失败获取提示
    open8() {
      ElMessage.warning("get leaveMessage failed");
    },
    // 成功获取提示
    open7() {
      ElMessage.success("get leaveMessage success");
    },
    getAllLeaveMessage() {
      axiosInstance
        .get("http://localhost:8080/leaveMessage")
        .then((response) => {
          this.leaveMessageList = response.data;
          if (this.leaveMessageList) {
            this.open7();
          } else {
            this.open8();
          }
        })
        .catch((error) => {
          console.error("Error happened when get leaveMessage", error);
          this.open8();
        });
    },
    deleteSingleLeaveMessage(id) {
      axiosInstance
        .get("http://localhost:8080/leaveMessage/" + id)
        .then((response) => {
          if (response.data === 1) {
            this.open9(id);
            this.leaveMessageList = this.leaveMessageList.filter(
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
  },
};
</script>
