//package DB;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
///**
// * Created by qcl on 2017/11/18.
// * 数据库连接
// * 已废弃
// */
//public class DB {
//    public static void main(String[] args) {
//        Connection con;
//        String driver="com.mysql.cj.jdbc.Driver";
//        //这里我的数据库是qcl
//        String url="jdbc:mysql://120.76.41.212:3306/test";
//        String user="root";
//        String password="MyNewPass4!";
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, user, password);
//            if (!con.isClosed()) {
//                System.out.println("数据库连接成功");
//            }
//            Statement statement = con.createStatement();
//            String sql = "select * from Table1;";//我的表格叫home
//            ResultSet resultSet = statement.executeQuery(sql);
//            String LastName, FirstName;
//            while (resultSet.next()) {
//                LastName = resultSet.getString("LastName");
//                FirstName = resultSet.getString("FirstName");
//                System.out.println("姓名：" + FirstName + ' ' + LastName);
//            }
//            resultSet.close();
//            con.close();
//        } catch (ClassNotFoundException e) {
//            System.out.println("数据库驱动没有安装");
//
//        } catch (SQLException e) {
//            System.out.println("数据库连接失败");
//        }
//    }
//}
