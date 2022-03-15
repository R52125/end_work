import Vue from 'vue'
import Vuex from 'vuex'

//持久化store里面的数据
import createPersistedState from 'vuex-persistedstate'

import router from './router'
import {useWebSocket} from "./hooks";

Vue.use(Vuex)

const ws = useWebSocket();

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
        //普通话术集合
        normalCount: [],
        //普通话术节点Index
        normalIndex: 0,
        //结束语集合
        endCount: [],
    },
    mutations:{
        check_id(state){
            router.push('/myself')
        },
        add_normal(state){
            if (this.state.nodeType==0)
                alert('未选择节点类型');
            else if(this.state.nodeName=='')
                alert('节点名称不允许为空')
            else if (this.state.nodeContent=='')
                alert('话术内容不允许为空')
            else{
                this.state.normalCount.push({
                    "name": this.state.nodeName,
                    "content": this.state.nodeContent,
                    "type": this.state.nodeType
                });
                this.state.nodeName = '';
                this.state.nodeType = 0;
                this.state.nodeContent = ''
            }
            // ws.send(JSON.stringify({
            //     event_id: 1,
            //     data:{
            //         name: "node1",
            //         content: "hello",
            //         nodeType: 1,
            //     }
            // }))
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
        }
    },
    actions:{

    }
})
