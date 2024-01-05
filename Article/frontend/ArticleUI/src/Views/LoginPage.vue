<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginData"
      :rules="rules"
      label-width="80px"
    >
      <el-form-item label="Username" prop="username">
        <el-input v-model="loginData.username"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input type="password" v-model="loginData.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">Login</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";
import JSEncrypt from "jsencrypt";


export default {
  beforeMount() {
    this.getPublicKey();
  },
  data() {
    return {
      publicKey: "",
      // 为什么不设置两个值，而是一个对象包含两个属性？
      loginData: {
        username: "",
        password: "",
      },
      tempPassword: "",
      rules: {
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
          { min: 3, max: 15, message: "长度在3-15之间", trigger: "blur" },
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          { min: 3, max: 15, message: "长度在3-15之间", trigger: "blur" },
        ],
      },
    };
  },

  methods: {
    login() {
      this.encode();
      // 要加个逻辑，完成usrname+password检验
      axios
        .post("http://localhost:8080/PublicKey/key1", {
          // 这里要补一个公钥加密
          username: this.loginData.username,
          password: this.tempPassword,
        })
        .then((response) => {
         
         if(!response.data){
            // 如果返回内容是空，重新登录
         }
          // 在localStoreage,写入fake JWT
          const data = response.data;

          // Check if the access key already exists in local storage and delete it
          if (localStorage.getItem("accessKey")) {
            localStorage.removeItem("accessKey");
          }

          // Store the new access key in local storage with a 4-hour lifetime
          const currentTime = new Date().getTime();
          const expirationTime = currentTime + 4 * 60 * 60 * 1000; // 4 hours in milliseconds
          localStorage.setItem("accessKey", data);
          localStorage.setItem("accessKeyExpiration", expirationTime);
        //   跳转controlPage
        this.$router.push('/managePage');

        })
        .catch((error) => {
          // Handle error
          console.error(error);
          // 网络原因登录失败
        });
    },
    // 获取公钥
    getPublicKey() {
      axios
        .get("http://localhost:8080/PublicKey/key1")
        .then((response) => {
          // Handle response
          // console.log("recieve public key successful");
          // console.log(response);
          this.publicKey = response.data;
          // 这里怎么打印不出来呢，java中是publickey,是不是要转成什么格式？,16进制
        })
        .catch((error) => {
          // Handle error
          console.error("get PublicKey failed \n" + error);
          // 输出网络加载失败
        });
    },

    encode() {
      // Public key obtained from the backend
      const publicKey = this.publicKey;

      // Password to be encrypted,目前使用一个例子，我还没有用 this.loginData.password代替
      const password = this.loginData.password;

      // Create a new instance of JSEncrypt
      const encryptor = new JSEncrypt();

      // Set the public key
      encryptor.setPublicKey(publicKey);

      // Encrypt the password using the public key
      const encryptedPassword = encryptor.encrypt(password);

      this.tempPassword = encryptedPassword;

      // console.log("encryptedPassword is " + encryptedPassword);
    },
  },
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}
</style>
