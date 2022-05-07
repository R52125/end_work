package Main;

import DB.DBConnection;
import DB.SQLServerPool;
import org.java_websocket.WebSocketImpl;
import websocket.WsServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.IOException;

public class main {
    static DBConnection SQLServer = SQLServerPool.getSqlServerPool();
    public static void main(String[] args) throws IOException {
        SQLServer.init();
        WebSocketImpl.DEBUG = false;
        int port = 8887; // 端口
        WsServer s = new WsServer(port);
        s.start();

    }
}
