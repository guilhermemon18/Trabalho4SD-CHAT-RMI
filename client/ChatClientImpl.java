package client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.ChatService;
import view.ViewChat;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ChatService server;
	private ViewChat tela;

    public ChatClientImpl(String name, ChatService server) throws RemoteException {
        this.name = name;
        this.server = server;
        this.server.registerClient(this);
        this.tela = new ViewChat(this, this.name);
       
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(name + " received message: " + message);
        this.tela.adicionaMensagem(message);
    }
//    @Override
//	public String getName() {
//		return name;
//	}

    @Override
    public void receivePrivateMessage(String message, ChatClient sender) {
//        System.out.println("[Mensagem Privada de " + sender.getName() + "]: " + message);
//        this.tela.adicionaMSGPrivada(message, sender.getName());
    }

	@Override
	public void fecharChat() throws RemoteException {
		// TODO Auto-generated method stub
		server.unregisterClient(this);
	}

	@Override
	public void sendMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		this.server.broadcastMessage(message);
		
	}

	@Override
	public void sendPrivateMessage(String message, String receiver) throws Exception {
		// TODO Auto-generated method stub
		this.server.privateMessage(message,this , receiver);
		
	}


    
}
