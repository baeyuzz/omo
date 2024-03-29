import Vue from "vue";
import Vuex from "vuex";
import http from "@/http-common.js";
import constants from './lib/constants.js'
import cookies from 'vue-cookies';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id : '',
    name : '',
    code : '',
    addr : '',
    phone : '',
    token: cookies.get('token'),
  },
  actions: {
    [constants.METHODS.LOGIN_USER]: (_store, payload) => {
      let url = "";
      http
        .post(url, {
          email: payload.email,
          password: payload.password,
        })
        .then(() => {
          console.log("Success");
          this.$router.push({
            name: "Main",
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },


  },
  mutations: {
    setName(state, name) {
      state.name = name;
    },
    setAddr(state, addr) {
      state.addr = addr;
    },
    setPhone(state, phone) {
      state.phone = phone;
    },
    setCode(state, code) {
      if(state.toke!='')
        cookies.set('code',code)
      else
        cookies.set('code','')
      state.code = code;
    },
    clearMember(state) {
      state.phone = '';
      state.addr = '';
      state.name = '';
    },
    logout(state){
      state.code = '';
    },
    setToken(state, token) {
      state.token = token;
      cookies.set('token', token);
    },
    setEmail(state,id) {
      state.id = id
    }
  },
 
});