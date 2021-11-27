import axios from 'axios';
//设置默认超时时间和请求头,
const instance = axios.create(
    {
        baseURL: process.env.VUE_APP_BASE_API,
        timeout: 10000,
        headers: {'Content-Type': 'application/json;charset=UTF-8'},
    })

//response拦截器 处理异常pm
instance.interceptors.response.use(
    response => {
        const result = response.data;
        if (result.code == 0) {
            return response.data
        }

        return Promise.reject(response)
    },
    error => {

        return Promise.reject(error)
    })

export default instance;

