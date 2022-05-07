package DB;
import java.sql.Connection;
/**
 * @author ：R52125
 * @date ：Created in 2022/3/21 23:37
 * @description：定义一个接口，更换数据库是方便
 * @modified By：
 * @version: 1.0$
 */
public interface DBConnection {
    //初始化
    public void init();
    //取得连接
    public  Connection getConnection();
    //关闭
    public void close(Connection conn);
}
