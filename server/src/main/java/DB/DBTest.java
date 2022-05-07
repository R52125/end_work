package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ：R52125
 * @date ：Created in 2022/3/22 0:09
 * @description：测试数据库
 * @modified By：
 * @version: 1.0$
 */
public class DBTest {
    static DBConnection SQLServer = SQLServerPool.getSqlServerPool();
    public static void main(String args[]) {
        SQLServer.init();
        long start = System.currentTimeMillis();
        for(int i=0; i<1; i++) {
            test();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    private static void test() {
        try{
            Connection conn = SQLServer.getConnection();
            String sql = "select * from Table1";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            String LastName, FirstName;
            while (rs.next()) {
                LastName = rs.getString("LastName");
                FirstName = rs.getString("FirstName");
                System.out.println("姓名：" + FirstName + ' ' + LastName);
            }
            pst.close();
            SQLServer.close(conn);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
