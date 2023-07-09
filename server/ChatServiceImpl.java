package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import client.ChatClient;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ChatClient> clients;

    public ChatServiceImpl() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void registerClient(ChatClient client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public void unregisterClient(ChatClient client) throws RemoteException {
        clients.remove(client);
    }

    @Override
    public void broadcastMessage(String message) throws RemoteException {
        for (ChatClient client : clients) {
            client.receiveMessage(message);
        }
    }
    
    @Override
    public void privateMessage(String message, ChatClient sender, String receiverName) throws Exception {
//        for (ChatClient client : clients) {
//            if (client.getName().equals(receiverName)) {
//                client.receivePrivateMessage(message, sender);
//                return;
//            }
//        }
//        sender.receiveMessage("O destinatário não está disponível ou não existe: " + receiverName, null);
        throw new Exception("O destinátário não está disponível ou foi desconectado");
    }


}

