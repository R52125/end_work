package DB;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author ：R52125
 * @date ：Created in 2022/3/22 0:05
 * @description：构建一个数据库连接池
 * @modified By：
 * @version: 1.0$
 */
public class SQLServerPool implements DBConnection{
    //最小连接数
    private static final int minCount = 1;
    //最大连接数
    private static final int maxCount = 15;
    //连接池
    private static final LinkedList<Connection> pools = new LinkedList<Connection>();
    SQLServerHandler handler = new SQLServerHandler();

    //单例模式
    private SQLServerPool(){}
    private static SQLServerPool sqlServerPool = null;
    public static SQLServerPool getSqlServerPool(){
        if(sqlServerPool==null)
            sqlServerPool = new SQLServerPool();
        return sqlServerPool;
    }
    /**
     * @author R52125
     * @date Created in 2022/3/22 0:08
     * Description: 重写init
     */
    public void init() {
        Connection conn = null;
        try{
            for(int i=0; i<minCount; i++) {
                conn = handler.buildConnection();
                pools.add(conn);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author R52125
     * @date Created in 2022/3/22 0:10
     * Description: 重写getConnection
     */
    public synchronized Connection getConnection() {
        Connection conn = null;
        if(pools.size() == 0) {
            conn = handler.buildConnection();
        }
        else {
            conn = pools.remove(0);
        }
        return conn;
    }

    /**
     * @author xiaoD
     * @date Created in 2022/3/22 0:13
     * Description: 重写close
     */
    public synchronized void close(Connection conn) {
        if(pools.size() < maxCount) {
            pools.add(conn);
        }
        System.out.println(pools);
    }
}