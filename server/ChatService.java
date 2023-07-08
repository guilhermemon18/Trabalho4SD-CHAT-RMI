package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.ChatClient;

public interface ChatService extends Remote {
    void registerClient(ChatClient client) throws RemoteException;
    void unregisterClient(ChatClient client) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
}
