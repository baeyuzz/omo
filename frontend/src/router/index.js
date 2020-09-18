import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/main',
    name: 'Main',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import( /* webpackChunkName: "about" */ '../views/Main.vue')
  },

  {
    path: '/notice',
    name: 'Notice',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import( /* webpackChunkName: "about" */ '../views/Notice.vue')
  },

  {
    path: '/voiceReg',
    name: 'VoiceRegForm',
    component: () => import('../views/MainMenu/Voice/VoiceRegForm.vue'),
  },
  {
    path: '/account',
    name: 'Account',
    component: () => import('../views/MainMenu/Account.vue'),
  },
  {
    path: '/account/changePwd',
    name: 'ChangePwd',
    component: () => import('../views/MainMenu/Account/ChangePwd.vue'),
  },
  {
    path: '/createList',
    name: 'CreateListForm',
    component: () => import('../views/CreateList/WebCam.vue'),
  },
  {
    path: '/manageList',
    name: 'ManageListForm',
    component: () => import('../views/developing.vue'),
  },
  {
    path: '/userSetting',
    name: 'UserSettingForm',
    component: () => import('../views/developing.vue'),
  },
  {
    path: '/voiceReg/record',
    name: 'RegRecord',
    component: () => import('../views/MainMenu/Voice/RegRecord.vue'),
  },
  
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router