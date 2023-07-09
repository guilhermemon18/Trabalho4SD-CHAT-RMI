package client;
import java.rmi.Naming;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import server.ChatService;

public class ChatClientApp {
    public static void main(String[] args) {
        try {
        	ChatService chatService = (ChatService) Naming.lookup("rmi://localhost/chat");
        	
        	
        	JLabel lblMessage = new JLabel("Criando Cliente!");
    		//txtIP = new JTextField();
    		JLabel lblinserirnome = new JLabel("Insira o nome!");
    		JTextField txtNome = new JTextField();
    		Object[] texts = {lblMessage, lblinserirnome,txtNome };
    		JOptionPane.showMessageDialog(null, texts);
    		ChatClientImpl client1 = new ChatClientImpl(txtNome.getText(), chatService);

        	
            
            //chatService.registerClient(client1);

//            ChatClient client2 = new ChatClientImpl("Client 2", chatService);
//            chatService.registerClient(client2);
//
//            chatService.broadcastMessage("Hello everyone!");
//
//            chatService.unregisterClient(client1);
//            chatService.unregisterClient(client2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
