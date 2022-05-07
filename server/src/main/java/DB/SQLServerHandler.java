package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ：R52125
 * @date ：Created in 2022/3/22 0:02
 * @description：创建数据库连接
 * @modified By：
 * @version: 1.0$
 */
public class SQLServerHandler {
    private Connection con = null;
    private String driver="com.mysql.cj.jdbc.Driver";
    //这里我的数据库是end_work
    private String url="jdbc:mysql://120.76.41.212:3306/end_work"+"?useServerPrepStmts=true&cachePrepStmts=true";
    private String user="root";
    private String password="MyNewPass4!";
    public Connection buildConnection(){
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this.con;
    }
}
