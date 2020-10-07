import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:9004/api/'
  });


function loginUser(data) {
    return instance.post('auth/signin', data);
}
function createUser(data) {
    return instance.post('auth/signup', data);
}
function deleteUser(data) {
    return instance.delete('users', null, {headers : data});
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