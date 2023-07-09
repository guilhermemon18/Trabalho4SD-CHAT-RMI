package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receiveMessage(String message) throws RemoteException;
    void receivePrivateMessage(String message, ChatClient sender) throws RemoteException;
//    String getName();
    void fecharChat() throws RemoteException;
    void sendMessage(String message) throws RemoteException;
    void sendPrivateMessage(String message, String receiver) throws Exception;
}
