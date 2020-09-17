import Vue from "vue";
import Vuex from "vuex";
import http from "@/http-common.js";
import constants from './lib/constants.js'

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {

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
  mutations: {},
  getters: {},
});