import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:9004/api/auth/'
  });


function loginUser(data) {
    return instance.post('signin', data);
}
function createUser(data) {
    return instance.post('signup', data);
}
function deleteUser(data) {
    return instance.post('delete', data);
}
function emailCheck(data) {
    return instance.post('emailCheck', data);
}

export {
  loginUser,
  createUser,
  deleteUser,
  emailCheck
}