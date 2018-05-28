package handler;

import com.google.gson.Gson;
import messageManagement.Manager;
import messageManagement.Message;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class GetMessageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {

        URI uri = request.getRequestURI();
        String[] query = uri.getRawQuery().split("&");

        String response = "No Messages!";
        String userName = "";
        String chatPartner = "";

        if(query[0].contains("cName")) {
            //Who does the request
            userName = query[0].split("=")[1];
            chatPartner = query[1].split("=")[1];

        }else if(query[0].contains("chatPartner")) {
            //from whom does the client want to get the messages?
            userName = query[1].split("=")[1];
            chatPartner = query[0].split("=")[1];
        }

        System.out.println(userName + " requests Message query for partner " + chatPartner);

        Response resp = new Response();
        resp.messages = Manager.getMessages(userName, chatPartner);

        Gson gson = new Gson();

        response = gson.toJson(resp);

        request.sendResponseHeaders(200, response.length());
        OutputStream os = request.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
