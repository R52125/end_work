package DB;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.DuplicateKeyException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：R52125
 * @date ：Created in 2022/3/22 18:12
 * @description：对数据库的操作
 * @modified By：
 * @version: 1.0$
 */
public class DBOption {
    static DBConnection SQLServer = SQLServerPool.getSqlServerPool();

    public static int setNode(int type, String name, String content, String sureNext, String denyNext, String unknownNext, String moduleName, boolean visible1, boolean visible2, boolean visible3, String userName) {
        /**
         * create by: R52125
         * description: 将数据写入数据库内
         * create time: 2022/3/22 20:38
         *
         * @Param: sql
         * @return void
         */
        Connection conn = null;
        try {
            conn = SQLServer.getConnection();
            String sql = "insert into test (name, content, type, sureNext, denyNext, unknownNext, moduleName, visible1, visible2, visible3, userName) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, content);
            pst.setInt(3, type);
            pst.setString(4, sureNext);
            pst.setString(5, denyNext);
            pst.setString(6, unknownNext);
            pst.setString(7, moduleName);
            pst.setBoolean(8, visible1);
            pst.setBoolean(9, visible2);
            pst.setBoolean(10, visible3);
            pst.setString(11, userName);
            //执行sql语句
            pst.executeUpdate();
            pst.close();
            System.out.println("数据库插入完毕");
            return 1;
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("主键重复，该数据写入数据库失败");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            SQLServer.close(conn);
        }
    }
    
    public static JSONArray getNodeFModule(String moduleName, String userName){
        /**
         * create by: R52125
         * description: 根据模块名将数据从数据库取出
         * create time: 2022/3/22 19:54
         * 
          * @Param: sql
         * @return void
         */
        Connection conn = null;
        JSONArray jsonArray = new JSONArray();
        try {
            conn = SQLServer.getConnection();
            String sql = "select * from test where moduleName = ? and userName = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, moduleName);
            pst.setString(2, userName);
            //执行sql语句
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int type = rs.getInt("type");
                String name = rs.getString("name");
                String content = rs.getString("content");
                String sureNext = rs.getString("sureNext");
                String denyNext = rs.getString("denyNext");
                String unknownNext = rs.getString("unknownNext");
                boolean visible1 = rs.getBoolean("visible1");
                boolean visible2 = rs.getBoolean("visible2");
                boolean visible3 = rs.getBoolean("visible3");
                Map<String, Object> map = new HashMap<>();
                map.put("type", type);
                map.put("name", name);
                map.put("content", content);
                map.put("sureNext", sureNext);
                map.put("denyNext", denyNext);
                map.put("unknownNext", unknownNext);
                map.put("moduleName", moduleName);
                map.put("visible1", visible1);
                map.put("visible2", visible2);
                map.put("visible3", visible3);
                map.put("userName", userName);
                jsonArray.put(map);
            }
            pst.close();
            return jsonArray;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLServer.close(conn);
        }
        return jsonArray;
    }

    public static int[] checkAndGetNum(String userName, String password){
        /**
         * create by: R52125
         * description: TODO
         * create time: 2022/4/30 17:25
         *
          * @Param: userName
         * @Param: password
         * @return int[]
         */
        Connection conn = null;
        JSONArray jsonArray = new JSONArray();
        int[] ans = new int[2];
        try {
            conn = SQLServer.getConnection();
            //第一条sql
            String sql = "select password from userInfo where userName = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);

            //执行sql语句
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String truePassword = rs.getString("password");
                if(truePassword.equals(password)){
                    ans[0]=1;
                }
                else{
                    ans[0]=0;
                }
            }
            pst.close();

            //第二条sql
            sql = "select count(moduleName) as moduleNum from (select distinct moduleName from moduleInfo where userName = ?) as b;";
//            sql = "select * from test where userName = ? group by moduleName";
            PreparedStatement pst1 = conn.prepareStatement(sql);
            pst1.setString(1, userName);
            //执行sql语句
            ResultSet rs1 = pst1.executeQuery();
//            System.out.println("userName: "+ userName);
//            while (rs1.next()){
//                System.out.println(rs1.getString("moduleName"));
//            }
            if (rs1.next()){
                int num = rs1.getInt("moduleNum");
                ans[1] = num;
            }

            pst1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLServer.close(conn);
        }
        return ans;
    }

    public static JSONArray getModuleCount(String userName){
        /**
         * create by: R52125
         * description: 根据用户名，将该用户的全部模板及描述显示出来
         * create time: 2022/5/3 20:05
         *
          * @Param: UserName
         * @return org.json.JSONArray
         */
        Connection conn = null;
        JSONArray jsonArray = new JSONArray();
        try {
            conn = SQLServer.getConnection();
            String sql = "select moduleName,description from moduleInfo where userName = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            //执行sql语句
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String moduleName = rs.getString("moduleName");
                String description = rs.getString("description");
                Map<String, Object> map = new HashMap<>();
                map.put("moduleName", moduleName);
                map.put("description", description);
                jsonArray.put(map);
            }
            pst.close();
            return jsonArray;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLServer.close(conn);
        }
        return jsonArray;
    }


    public static int deleteModuleFmoduleInfo(String moduleName, String userName){
        /**
         * create by: R52125
         * description: 删除模板
         * create time: 2022/5/5 20:48
         *
         * @Param: moduleName
         * @Param: userName
         * @return int
         */
        Connection conn = null;
        try {
            conn = SQLServer.getConnection();

            //从moduleInfo删除该模块的信息
            String sql = "delete from moduleInfo where moduleName = ? and userName = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, moduleName);
            pst.setString(2, userName);
            //执行sql语句
            pst.executeUpdate();
            pst.close();
            System.out.println("模板信息已删除");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            SQLServer.close(conn);
        }
    }
    public static int deleteModuleFtest(String moduleName, String userName){
        /**
         * create by: R52125
         * description: 删除模板
         * create time: 2022/5/5 20:48
         *
          * @Param: moduleName
         * @Param: userName
         * @return int
         */
        Connection conn = null;
        try {
            conn = SQLServer.getConnection();

            //从test删除该模块的所有节点名
            String sql = "delete from test where moduleName = ? and userName = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, moduleName);
            pst.setString(2, userName);
            //执行sql语句
            pst.executeUpdate();
            pst.close();
            System.out.println("模板信息已删除");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            SQLServer.close(conn);
        }
    }

    public static int addModule(String userName, String moduleName, String description){
        /**
         * create by: R52125
         * description: 新增模板
         * create time: 2022/5/5 20:58
         *
          * @Param: userName
         * @Param: moduleName
         * @Param: description
         * @return int
         */
        Connection conn = null;
        try {
            conn = SQLServer.getConnection();
            String sql = "insert into moduleInfo (userName, moduleName, description) values(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, userName);
            pst.setString(2, moduleName);
            pst.setString(3, description);
            //执行sql语句
            pst.executeUpdate();
            pst.close();
            System.out.println("新增模板成功");
            return 1;
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("主键重复，新增模板失败");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            SQLServer.close(conn);
        }
    }
}
