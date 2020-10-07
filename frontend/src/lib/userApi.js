import axios from 'axios';
import cookies from 'vue-cookies';

const instance = axios.create({
    baseURL: 'http://localhost:9004/api/'
  });


function loginUser(data) {
    return instance.post('auth/signin', data);
}
function createUser(data) {
    return instance.post('auth/signup', data);
}
function deleteUser() {
    const username = cookies.get('code')
    const token = 'Bearer '+ cookies.get("token")
    console.log(token)
    return instance.delete(`users/${username}`, {'Authorization' : token,}, 
    { headers : {
        'Authorization' : token,
    }});
}
function emailCheck(data) {
    return instance.post('auth/emailCheck', data);
}

export {
  loginUser,
  createUser,
  deleteUser,
  emailCheck
}