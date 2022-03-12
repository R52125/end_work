package Main;

import org.java_websocket.WebSocketImpl;
import websocket.WsServer;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        WebSocketImpl.DEBUG = false;
        int port = 8887; // 端口
        WsServer s = new WsServer(port);
        s.start();
    }
}
