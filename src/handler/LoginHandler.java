package handler;

import messageManagement.Manager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {

        BufferedReader dataReader = new BufferedReader(new InputStreamReader(request.getRequestBody()));

        String payload = dataReader.readLine();
        dataReader.close();


        String[] bodyItems = payload.split(":");

        String response = "ERR";

        if(bodyItems[0].equals("cName")) {
            System.out.println(bodyItems[1] + " checks in!");
            Manager.checkinClient(bodyItems[1]);
            response = "OK";
        }

        request.sendResponseHeaders(200, response.length());
        OutputStream os = request.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
