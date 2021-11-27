import instance from './http';


const apiUtil = {
    baseQuery: (url, data) => {
        return new Promise(((resolve, reject) => {
            instance.get(url, {
                params: data,
            },)
                .then(res => {
                    resolve(res.data)
                })
                .catch(err => {
                    reject(err.data)
                })
        }))
    },
}


export default apiUtil;
