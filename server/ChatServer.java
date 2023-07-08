package server;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ChatService chatService = new ChatServiceImpl();
            LocateRegistry.createRegistry(1099); // Porta do RMI Registry
            Naming.rebind("chat", chatService);
            System.out.println("Chat server is running.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
