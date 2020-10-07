import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
// import store from '@/store';
import cookies from 'vue-cookies';


Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'Home',
    component: Home,
    beforeEnter: requireLogout,
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
    path: '/video/mask/:roomName',
    name: 'OnlyMask',
    component: () => import('../views/CreateList/onlyMask.vue'),
    props: route => ({roomName: String(route.params.roomName)})
  },
  {
    path: '/video/:roomName',
    name: 'Room',
    component: () => import('../views/CreateList/room.vue'),
    props: route => ({roomName: String(route.params.roomName)})
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
  {
    path: '/members',
    name: 'Members',
    component: () => import('../views/MainMenu/Account/Members.vue'),
    beforeEnter: requireLogin,
  },
  {
    path: '/service',
    name: 'Introduce',
    component: () => import('../views/Introduce.vue'),
  },
  
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

function requireLogin(to, from, next) {
  cookies.get('token') != null ?next() : next('/'); // 지금은 임시로 == 해놓음 ~
}

function requireLogout(to, from, next) {
  cookies.get('token') == null ? next() : next('/main');
}

export default router