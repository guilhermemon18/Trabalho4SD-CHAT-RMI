package client;
import java.rmi.Naming;

import server.ChatService;

public class ChatClientApp {
    public static void main(String[] args) {
        try {
        	ChatService chatService = (ChatService) Naming.lookup("rmi://localhost/chat");

            ChatClient client1 = new ChatClientImpl("Client 1");
            chatService.registerClient(client1);

            ChatClient client2 = new ChatClientImpl("Client 2");
            chatService.registerClient(client2);

            chatService.broadcastMessage("Hello everyone!");

            chatService.unregisterClient(client1);
            chatService.unregisterClient(client2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
