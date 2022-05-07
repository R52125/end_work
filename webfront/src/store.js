import Vue from 'vue'
import Vuex from 'vuex'

//持久化store里面的数据
import createPersistedState from 'vuex-persistedstate'

import router from './router'
import {useWebSocket} from "./hooks";
import {use} from "element-ui";

Vue.use(Vuex)

// const ws = useWebSocket();

export default new Vuex.Store({
    state:{
        //节点类型
        nodeType: 0,
        //节点内容
        nodeContent: '',
        //节点名称
        nodeName: '',
        //增添新节点
        addNode: 0,
        //话术集合(包括普通话术和结束语)
        normalCount: [],
        //模板集合
        moduleCount:[],
        //模块名
        moduleName:'',
        //websocket连接
        ws: '',
        //用户名
        userName: '',
        //密码
        password: '',
        //模板个数
        moduleNum: 0,
        //模板描述
        description: '',
    },
    mutations:{
        delModule(state){
            this.state.ws.send(JSON.stringify({
                event_id: 5,
                data:{
                    "moduleName": this.state.moduleName,
                    "userName": this.state.userName,
                }
            }))
        },
        moduleMes(state){
            this.state.ws.send(JSON.stringify({
                event_id: 3,
                data:{
                    "userName": this.state.userName,
                }
            }));
            router.push('/moduleMes');
        },
        check_id(state){
            if(this.state.userName==''){
                alert('用户名未输入')
            }
            else{
                this.state.ws = useWebSocket();
            }
        },
        sendUserName(state){
            this.state.ws.send(JSON.stringify({
                event_id: 0,
                data:{
                    "userName": this.state.userName,
                    "password": this.state.password,
                }
            }))
        },
        //设置用户名
        setUserName(state, newUserName){
            this.state.userName = newUserName;
        },
        //设置密码
        setPassWord(state, newPassWord){
            this.state.password = newPassWord;
        },
        add_normal(state){
            if (this.state.nodeType==0)
                alert('未选择节点类型');
            else if(this.state.nodeName=='')
                alert('节点名称不允许为空');
            else if (this.state.nodeContent=='')
                alert('话术内容不允许为空');
            else if(this.state.moduleName=='')
                alert('模块名不允许为空');
            else{
                console.log(this.state.nodeName);
                console.log(this.state.nodeContent);
                console.log(this.state.nodeType);
                console.log(this.state.moduleName);

                this.state.normalCount.push({
                    "name": this.state.nodeName,
                    "content": this.state.nodeContent,
                    "type": this.state.nodeType,
                    "sureNext": '',
                    "denyNext": '',
                    "unknownNext": '',
                    "visible1": false,
                    "visible2": false,
                    "visible3": false,
                    "moduleName": this.state.moduleName,
                    "userName": this.state.userName,
                });
                this.state.nodeName = '';
                this.state.nodeType = 0;
                this.state.nodeContent = ''
            }
        },
        //设置节点类型
        setNodeType(state, newNodeType){
            this.state.nodeType = newNodeType;
        },
        //设置节点名称
        setNodeName(state, newNodeName){
            this.state.nodeName = newNodeName;
        },
        //设置话术内容
        setNodeContent(state, newNodeContent){
            this.state.nodeContent = newNodeContent;
        },
        setAddNode(state, newAddNode){
            this.state.addNode = newAddNode;
        },
        //向normalCount压入一个数据
        setNormalCount(state, newNormalCount) {
            this.state.normalCount = newNormalCount;
        },
        //设置节点Index
        setNormalIndex(state, newIndex){
            this.state.normalIndex = newIndex;
        },
        //设置模块名
        setModuleName(state, newModuleName){
            this.state.moduleName = newModuleName;
        },
        //设置模块数量
        setModuleNum(state, newModuleNum){
            this.state.moduleNum = newModuleNum;
        },
        //设置模块信息
        setModuleCount(state, newModuleCount){
            this.state.moduleCount = newModuleCount;
        },
        //设置模板描述
        setDescription(state, newDescription){
            this.state.description = newDescription;
        },
        //将normalCount信息发送给服务器
        sendNormalCount(state){
            this.state.ws.send(JSON.stringify({
                event_id: 1,
                data: {
                    "normalCount": this.state.normalCount
                }
            }))
        },
        //获取对应模块的节点信息
        getNormalCount(state){
            this.state.ws.send(JSON.stringify({
                event_id: 2,
                data:{
                    "userName": this.state.userName,
                    "moduleName": this.state.moduleName,
                }
            }));
            router.push('/edit_view');
        },
        //处理 (event_id: 100) 的响应
        userConnect(state, newdata){
            if(newdata.data.ack==1){
                //身份验证通过
                this.state.moduleNum = newdata.data.moduleNum;
                router.push('/myself');
            }
            else{
                alert('身份验证失败，该用户不存在');
            }
        },
        writeNodeDB(state, newdata){
            if(newdata.data.ack==1){
                alert('成功写入数据库');
            }
            else{
                alert('写入数据库失败');
            }
        },
        readNodeDB(state, newdata){
            this.state.normalCount='';
            this.state.normalCount=newdata.data.normalCount;
        },
        readModuleMes(state, newdata){
            this.state.moduleCount='';
            this.state.moduleCount=newdata.data.moduleCount;
        },
        addModule(state, newdata){
            this.state.ws.send(JSON.stringify({
                event_id: 3,
                data:{
                    "userName": this.state.userName,
                }
            }))
        },
        deleteModule(state, newdata){
            this.state.ws.send(JSON.stringify({
                event_id: 3,
                data:{
                    "userName": this.state.userName,
                }
            }))
        },
        createModule(state){
            if(this.state.moduleName=='')
                alert('模块名不能为空')
            else{
                this.state.ws.send(JSON.stringify({
                    event_id: 4,
                    data:{
                        "userName": this.state.userName,
                        "moduleName": this.state.moduleName,
                        "description": this.state.description,
                    }
                }))
                router.push('/edit_view');
            }
        }
    },
    actions:{
        sendUser(context){
            context.commit('sendUserName');
        },
        handleUserConnect(context, newdata){
            context.commit('userConnect', newdata);
        },
        handleWriteNodeDB(context, newdata){
            context.commit('writeNodeDB', newdata);
        },
        handleReadNodeDB(context, newdata){
            context.commit('readNodeDB', newdata);
        },
        handleReadModuleMes(context, newdata){
            context.commit('readModuleMes', newdata);
        },
        handleAddModule(context, newdata){
            context.commit('addModule', newdata);
        },
        handleDeleteModule(context, newdata){
            context.commit('deleteModule', newdata);
        }
    }
})
