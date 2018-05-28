package handler;

import messageManagement.Manager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;

public class LogoutHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {

        BufferedReader dataReader = new BufferedReader(new InputStreamReader(request.getRequestBody()));

        String payload = dataReader.readLine();
        dataReader.close();

        System.out.println("Payload: " + payload);

        String[] bodyItems = payload.split(":");

        String response = "ERR";

        if(bodyItems[0].equals("cName")) {
            System.out.println(bodyItems[1] + " checks out!");
            Manager.checkoutClient(bodyItems[1]);
            response = "OK";
        }

        request.sendResponseHeaders(200, response.length());
        OutputStream os = request.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
