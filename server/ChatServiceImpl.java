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
	private static Integer nextID = 1;
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

}

