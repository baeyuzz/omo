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
    return instance.delete(`users/${cookies.get('code')}`, {
        headers: { 'Authorization' : 'Bearer ' + cookies.get("token") },
    });
}
function emailCheck(data) {
    return instance.post('auth/emailCheck', data);
}
function getEmp() {
    return instance.get('employee', {
        headers: { 'Authorization' : 'Bearer ' + cookies.get("token") },
    });
}
function deleteEmp(id) {
    return instance.delete(`employee/${id}`, {
        headers: { 'Authorization' : 'Bearer ' + cookies.get("token") },
    });
}

export {
  loginUser,
  createUser,
  deleteUser,
  emailCheck,
  getEmp,
  deleteEmp
}