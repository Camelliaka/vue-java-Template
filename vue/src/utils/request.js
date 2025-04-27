//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({baseURL:baseURL,timeout:5000})


//添加请求拦截器
instance.interceptors.request.use(
    config => {
        // 可以在这里添加请求头等信息
        // 例如：config.headers['Authorization'] = 'Bearer your-token';
        return config;
    })

//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        //判断业务状态码 0，1
            return result.data
    }
)



export default instance;
