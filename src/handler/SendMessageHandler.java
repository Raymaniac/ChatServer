package handler;

import com.google.gson.Gson;
import messageManagement.Manager;
import messageManagement.Message;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class SendMessageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {

        BufferedReader requestInput = new BufferedReader(new InputStreamReader(request.getRequestBody()));
        String payload = requestInput.readLine();
        requestInput.close();

        Gson gson = new Gson();

        Message msg = gson.fromJson(payload, Message.class);

        System.out.println(msg.sender + "sends message to " + msg.recipient + ":");
        System.out.println(msg.messageText);

        Manager.storeMessage(msg);

        String response = "OK";
        request.sendResponseHeaders(200, response.length());
        OutputStream os = request.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


}
