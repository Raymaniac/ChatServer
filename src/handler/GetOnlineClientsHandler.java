package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import messageManagement.Manager;
import messageManagement.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class GetOnlineClientsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {

        ClientList list = new ClientList();

        list.clients = Manager.getOnlineClients();

        Gson gson = new Gson();

        String response = gson.toJson(list);

        request.sendResponseHeaders(200, response.length());
        OutputStream os = request.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}

class ClientList {
    public List<String> clients = new ArrayList<String>();
}
