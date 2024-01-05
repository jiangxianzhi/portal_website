<template>
  <div class="common-layout" >
    <!-- 标题 -->
    <div class="leaveMessageTitle">留言
      <div style="font-weight: lighter; font-size: 25px;">一人一天只能留一次哦</div>
    </div>

    <!-- 填写部分 -->
    <div class="leaveMessageContent">
      <div class="necessaryMessage">
        <div>
          <div class="input-container">邮箱</div>
          <el-input
            v-model="email"
            placeholder=" "
            ref="input1"
            @keyup.enter="$refs.input2.focus()"
          />
        </div>
        <div>
          <div class="input-container">名字</div>
          <el-input
            v-model="name"
            placeholder=" "
            ref="input2"
            @keyup.enter="$refs.input3.focus()"
          />
        </div>

        <div>
          <div class="input-container">电话</div>
          <el-input
            v-model="phone"
            placeholder=" "
            ref="input3"
            @keyup.enter="$refs.input4.focus()"
          />
        </div>
      </div>

      <div class="ContentMessage">
        <div class="input-container">想说的话</div>
        <el-input
          class="leaveMessageInput"
          type="textarea"
          v-model="leaveMessage"
          ref="input4"
          @keyup.enter="$refs.input1.focus()"
          :rows="6"
        ></el-input>
      </div>
    </div>

    <!-- 按钮 -->
    <div>
      <button class="button_submit" @click="submit()">send</button>
    </div>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
import axios from "axios";
import { ElLoading } from "element-plus";
export default {
  mounted() {
    //问，我的ip是什么

    axios
      .get("http://api.ipify.org")
      .then((response) => {
        this.ipAddress = response.data;
      })
      .catch((error) => {
        console.log("Error1 fetching IP address:", error);
      
      })
      
  },
  data() {
    return {
      email: null,
      name: null,
      phone: null,
      leaveMessage: null,
      ipAddress: null,
    };
  },
  methods: {
    // 提交后内容清0
    deleteAllContent() {
      this.email = null;
      this.name = null;
      this.phone = null;
      this.leaveMessage = null;
    },
    // 提示内容
    open(alertContent) {
      ElMessage(alertContent);
    },

    // 检查是否空
    isNotEmpty() {
      // console.log("this.name is "+this.name);
      if (this.email && this.name && this.phone && this.leaveMessage) {
        // 这里判断要更加完善，空格什么之类的额，还要防止注入sql
        return true;
      } else {
        this.open("内容没填完整");
        return false;
      }
    },
    // 邮箱验证
    validateEmail(email) {
      const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

      if (!regex.test(email)) {
        this.open("send failed because wrong email format");
        return false;
      }
      return true;
    },
    // 提交内容
    submit() {
      //   邮箱验证通过，个都不是空三
      if (this.isNotEmpty() && this.validateEmail(this.email)) {
        // loading图标
        var a = ElLoading.service({
          lock: true,
          text: "Submitting",
          background: "rgba(0, 0, 0, 0.7)",
        });

        axios
          .post("http://localhost:8080/leaveMessage", {
            name: this.name,
            email: this.email,
            phone: this.phone,
            message: this.leaveMessage,
            ip: this.ipAddress,
          })
          .then((response) => {
            if (response.data === 1) {
              this.deleteAllContent();
              ElMessage.success("submit success");
            } else {
              this.open("提交失败");
            }
          })
          .catch((error) => {
            console.log(error);
            this.open("提交失败");
          })
          .finally(() => {
            // 结束loading
            a.close();
          });
      }
    },
  },
};
</script>

<style>
.common-layout {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  height: 400px; 
  background-color: #f0f8ff;
  padding-left: 20%;
  padding-right: 20%;
}
.leaveMessageTitle {
  margin-top: 30px;
  font-size: 36px;
  font-weight: bold;
}

.button_submit {
  border: 0;
  background-color: #8dabbe;
  color: white;
  border-radius: 6px;
  width: 200px;
  height: 52px;
  font-size: 28px;
}
.button_submit:hover{
  background-color: #098ee7;
}
.leaveMessageContent {
  display: flex;
  justify-content: space-between;
}
.necessaryMessage {
  padding-right: 20px;
}
.input-container {
  position: relative;
}

.input-container::after {
  content: "*";
  color: red;
  position: absolute;
  top: 50%;
  right: 5px;
  transform: translateY(-50%);
}
</style>
