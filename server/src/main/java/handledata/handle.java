package handledata;

import DB.DBOption;
import org.java_websocket.WebSocket;
import org.json.*;
import websocket.WsPool;

/**
 * @author ：R52125
 * @date ：Created in 2022/3/22 21:35
 * @description：处理数据
 * @modified By：
 * @version: 1.0$
 */
public class handle {
    public void analbag(WebSocket conn, String jsonStr){
        JSONObject jsonObj = new JSONObject(jsonStr);
        int event_id = jsonObj.getInt("event_id");
        //身份验证
        if(event_id==0){
            //添加连接
            WsPool.addUser(jsonObj.getJSONObject("data").getString("userName"), conn);
            //身份验证
            int[] req = analUser(jsonObj.getJSONObject("data"));
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("ack", req[0]);
            jsonObject1.put("moduleNum", req[1]);
            jsonObject.put("event_id", 100);
            jsonObject.put("data", jsonObject1);
            WsPool.sendMessageToUser(conn, jsonObject.toString());
        }
        else if(event_id==1){
            //将data写入数据库，分析数据
            int ack = analNodeData(jsonObj.getJSONObject("data"));
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("ack", ack);
            jsonObject.put("event_id", 101);
            jsonObject.put("data", jsonObject1);
            WsPool.sendMessageToUser(conn, jsonObject.toString());
        }
        else if(event_id==2){
            //将对应模块名的所有节点信息提取出来
            JSONArray jsonArray = analModuleName(jsonObj.getJSONObject("data"));
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("normalCount", jsonArray);
            jsonObject.put("event_id", 102);
            jsonObject.put("data", jsonObject1);
            WsPool.sendMessageToUser(conn, jsonObject.toString());
        }
        else if(event_id==3){
            String userName = analUserName(jsonObj.getJSONObject("data"));
            JSONArray jsonArray = DBOption.getModuleCount(userName);
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("moduleCount", jsonArray);
            jsonObject.put("event_id", 103);
            jsonObject.put("data", jsonObject1);
            WsPool.sendMessageToUser(conn, jsonObject.toString());
        }
        else if(event_id==4){
            String userName = analUserName(jsonObj.getJSONObject("data"));
            String moduleName = analModuleNameStr(jsonObj.getJSONObject("data"));
            String description = analDescription(jsonObj.getJSONObject("data"));
            int ack = DBOption.addModule(userName, moduleName, description);
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("ack", ack);
            jsonObject.put("event_id", 104);
            jsonObject.put("data", jsonObject1);
            WsPool.sendMessageToUser(conn, jsonObject.toString());
        }
        else if(event_id==5){
            String userName = analUserName(jsonObj.getJSONObject("data"));
            String moduleName = analModuleNameStr(jsonObj.getJSONObject("data"));
            int ack1 = DBOption.deleteModuleFtest(moduleName, userName);
            int ack2 = DBOption.deleteModuleFmoduleInfo(moduleName, userName);
            int ack = 0;
            if(ack1==1 && ack2==1)
                ack=1;
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("ack", ack);
            jsonObject.put("event_id", 105);
            jsonObject.put("data", jsonObject1);
            WsPool.sendMessageToUser(conn, jsonObject.toString());
        }
    }
    public int analNodeData(JSONObject jsonObj){
        JSONArray jsonArr = jsonObj.getJSONArray("normalCount");
        int flag=0;
        for(int i=0; i<jsonArr.length(); i++){
            JSONObject obj = jsonArr.getJSONObject(i);
            int type = obj.getInt("type");
            String name = obj.getString("name");
            String content = obj.getString("content");
            String sureNext = obj.getString("sureNext");
            String denyNext = obj.getString("denyNext");
            String unknownNext = obj.getString("unknownNext");
            String moduleName = obj.getString("moduleName");
            boolean visible1 = obj.getBoolean("visible1");
            boolean visible2 = obj.getBoolean("visible2");
            boolean visible3 = obj.getBoolean("visible3");
            String userName = obj.getString("userName");
            //先将test表中对应的模板信息全部删除
            if(flag==0){
                DBOption.deleteModuleFtest(moduleName, userName);
                flag=1;
            }
            //插入信息
            int ack = DBOption.setNode(type, name, content, sureNext, denyNext, unknownNext, moduleName, visible1, visible2, visible3, userName);
            if(ack==0)
                return 0;
        }
        return 1;
    }
    public JSONArray analModuleName(JSONObject jsonObj){
        String moduleName = jsonObj.getString("moduleName");
        String userName = jsonObj.getString("userName");
        return DBOption.getNodeFModule(moduleName, userName);
    }
    public int[] analUser(JSONObject jsonObj){
        String userName = jsonObj.getString("userName");
        String password = jsonObj.getString("password");
        return DBOption.checkAndGetNum(userName, password);
    }
    public String analUserName(JSONObject jsonObj){
        return jsonObj.getString("userName");
    }
    public String analModuleNameStr(JSONObject jsonObj){
        return jsonObj.getString("moduleName");
    }
    public String analDescription(JSONObject jsonObj){
        return jsonObj.getString("description");
    }
}
