import {WS_ADDRESS} from "../configs";
import store from "../store";

function useWebSocket(){
    const ws = new WebSocket(WS_ADDRESS);

    const init = () => {
        bindEvent();
    }

    init();

    function bindEvent(){
        ws.addEventListener('open', handleOpen, false);
        ws.addEventListener('close', handleClose, false);
        ws.addEventListener('error', handleError, false);
        ws.addEventListener('message', handleMessage, false);
    }

    function handleOpen(e){
        console.log('FE: WebSocket open', e);
        websocketSendUser();
    }
    function handleClose(e){
        console.log('FE: WebSocket close', e);
    }
    function handleError(e){
        console.log('FE: WebSocket error', e);
    }
    function handleMessage(callBack){
        var e = JSON.parse(callBack.data);
        console.log('e', e);
        // console.log('FE: WebSocket message', e);
        switch (e.event_id){
            case 100:
                websocketUserConnect(e);
                break;
            case 101:
                websocketWriteNodeDB(e);
                break;
            case 102:
                websocketReadNodeDB(e);
                break;
            case 103:
                websocketReadModuleMes(e);
                break;
            case 104:
                websocketAddModule(e);
                break;
            case 105:
                websocketDeleteModule(e);
                break;
        }
    }

    function websocketSendUser(){
        store.dispatch('sendUser');
    }
    function websocketUserConnect(newdata){
        store.dispatch('handleUserConnect', newdata);
    }
    function websocketWriteNodeDB(newdata){
        store.dispatch('handleWriteNodeDB', newdata);
    }
    function websocketReadNodeDB(newdata){
        store.dispatch('handleReadNodeDB', newdata);
    }
    function websocketReadModuleMes(newdata){
        store.dispatch('handleReadModuleMes', newdata);
    }
    function websocketAddModule(newdata){
        store.dispatch('handleAddModule', newdata);
    }
    function websocketDeleteModule(newdata){
        store.dispatch('handleDeleteModule', newdata);
    }

    return ws;
}

export default useWebSocket;