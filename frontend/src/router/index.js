import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import store from '@/store';


Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/main',
    name: 'Main',
    component: () => import('../views/Main.vue'),
    beforeEnter: requireLogin,
  },

  {
    path: '/notice',
    name: 'Notice',
    component: () => import('../views/Notice.vue')
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
    beforeEnter: requireLogin,
  },
  {
    path: '/account/changePwd',
    name: 'ChangePwd',
    component: () => import('../views/MainMenu/Account/ChangePwd.vue'),
    beforeEnter: requireLogin,
  },
  {
    path: '/createList',
    name: 'CreateListForm',
    component: () => import('../views/CreateList/WebCam.vue'),
    beforeEnter: requireLogin,
  },
  {
    path: '/createList2',
    name: 'CreateListForm',
    component: () => import('../views/CreateList/DjangoCam.vue'),
  },
  {
    path: '/manageList',
    name: 'ManageListForm',
    component: () => import('../views/developing.vue'),
    beforeEnter: requireLogin,
  },
  {
    path: '/userSetting',
    name: 'UserSettingForm',
    component: () => import('../views/developing.vue'),
    beforeEnter: requireLogin,
  },
  {
    path: '/voiceReg/record',
    name: 'RegRecord',
    component: () => import('../views/MainMenu/Voice/RegRecord.vue'),
  },
  {
    path: '/createList/voice',
    name: 'VoiceRecognition',
    component: () => import('../views/CreateList/VoiceRecognition.vue'),
    beforeEnter: requireLogin,
  },
  {
    path: '/visitor',
    name: 'VisitorList',
    component: () => import('../views/MainMenu/Account/Visitor.vue'),
    beforeEnter: requireLogin,
  },
  
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

function requireLogin(to, from, next) {
  store.state.id == '' ?next() : next('/'); // 지금은 임시로 == 해놓음 ~
}

export default router