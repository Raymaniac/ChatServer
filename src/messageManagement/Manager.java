package messageManagement;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static List<Message> messageStorage = new ArrayList<Message>();
    private static List<String> onlineClients = new ArrayList<String>();

    public static void storeMessage(Message message) {
        Manager.messageStorage.add(message);
    }

    public static List<Message> getMessages(String requestorName, String chatPartner) {
        List<Message> returnList = new ArrayList<Message>();

        for(Message msg : Manager.messageStorage) {
            if(msg.recipient.equals(requestorName) && msg.sender.equals(chatPartner)) {
                returnList.add(msg);
            }
        }

        return returnList;
    }


    public static void checkinClient(String clientName) {
        Manager.onlineClients.add(clientName);
    }

    public static boolean checkoutClient(String clientName) {
        if(Manager.onlineClients.contains(clientName)) {
            Manager.onlineClients.remove(clientName);
            return true;
        }else {
            return false;
        }
    }

    public static List<String> getOnlineClients() {
        return Manager.onlineClients;
    }

}
