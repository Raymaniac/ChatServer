package program;

import com.sun.net.httpserver.HttpServer;
import handler.*;

import java.net.InetSocketAddress;

public class ChatServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0);

        server.createContext("/login", new LoginHandler());
        server.createContext("/getOnlineClients", new GetOnlineClientsHandler());
        server.createContext("/getMessages", new GetMessageHandler());
        server.createContext("/sendMessage", new SendMessageHandler());
        server.createContext("/logout", new LogoutHandler());

        server.setExecutor(null);

        server.start();

        System.out.println("Server runs on " + server.getAddress().getAddress());

    }
}
