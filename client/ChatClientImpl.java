package client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer id;

    public ChatClientImpl(String name) throws RemoteException {
        this.name = name;
        this.id = null;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(name + " received message: " + message);
    }
    @Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) throws Exception {
		if(this.id != null) {
			if(id != null) {
				this.id = id;
			}else {
				throw new Exception("id não pode ser null");
			}
		}else {
			throw new Exception("O ID já foi setado");
		}
	}
    
}
