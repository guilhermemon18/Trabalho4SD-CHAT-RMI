package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receiveMessage(String message) throws RemoteException;
    String getName();
    Integer getId();
    void setId(Integer id) throws Exception;
}
