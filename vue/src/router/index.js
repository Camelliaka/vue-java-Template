import {createRouter, createWebHistory} from "vue-router";
import login from '@/views/login/Login.vue'
import index from "@/views/index.vue"
import info from '@/views/user/UserInfo.vue'
import avatar from '@/views/user/UserAvatar.vue'
import password from '@/views/user/UserResetPassword.vue'
import course from '@/views/data/course.vue'
import exam from '@/views/data/exam.vue'
import scores from '@/views/data/scores.vue'
import subject from '@/views/data/subject.vue'
import sysMenu from '@/views/data/sysmenu.vue'
import test from '@/components/test.vue'
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {path: '/login', component: login}, //登录页一级路由
        {
            path: '/',   //主页一级路由
            component: index,
            redirect: '/sysMenu', //重定向到sysMenu一级路由
            //子路由
            children: [
                {path:'/sysMenu',component:sysMenu,name:'sysMenu'},
                {path:'/course',component:course},
                {path:'/exam',component:exam},
                {path:'/scores',component:scores},
                {path:'/subject',component:subject},
                {path:'/test',component:test},
                
                {path: '/user/info', component: info},
                {path: '/user/password', component: password}
            ]
        },

    ]
})


export default router
