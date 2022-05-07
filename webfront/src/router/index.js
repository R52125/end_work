import Vue from 'vue'
import VueRouter from 'vue-router'

import Main from "../views/Main";
import Myself from "../views/Myself";
import Create from "../views/Create";
import Edit_view from "../views/Edit_view";
import ModuleMes from "../views/ModuleMes";

//安装路由
Vue.use(VueRouter);


//配置导出路由
export default new VueRouter({
    routes:[
        {
            //路由路径
            path:'/',
            name:'main',
            //跳转的路径
            component:Main,
        },
        {
            path:'/myself',
            name:'myself',
            component:Myself
        },
        {
            path:'/create',
            name:'create',
            component:Create
        },
        {
            path:'/edit_view',
            name:'edit_view',
            component:Edit_view
        },
        {
            path:'/moduleMes',
            name:'moduleMes',
            component:ModuleMes,
        },
    ]
});
