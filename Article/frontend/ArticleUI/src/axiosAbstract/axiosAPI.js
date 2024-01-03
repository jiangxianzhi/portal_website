import axios from "axios";
import router from '../router/index.js'; 
// Create a new Axios instance
const axiosInstance = axios.create();

// 所有axiosInstance添加了头部 accessKey

// Add a request interceptor to the Axios instance
axiosInstance.interceptors.request.use(
  (config) => {
    // Get the access key from local storage
    const accessKey = localStorage.getItem("accessKey");
    // console.log("accessKey is " + accessKey);
    // Add the access key to the request headers
    if (accessKey) {
      config.headers["Authorization"] = `Bearer ${accessKey}`;
    } else {
      // Redirect the user to the login page
      router.push("/loginPage");
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
export default axiosInstance; 
