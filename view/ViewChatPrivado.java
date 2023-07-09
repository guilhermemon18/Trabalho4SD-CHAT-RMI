package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.EmissorDeMensagem;
import server.Pacote;

public class ViewChatPrivado extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String nomeOrigem;
	private final String nomeDestino;
	private final JFrame frame;
	private final JPanel panel;
	private final JScrollPane scrollPane;
	private final JTextArea fieldChat;
	private final JLabel label1;
	private final JTextField textField;
	private final JButton button;
	private final ViewChat chat;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnLimpar;
	private JPanel panel_3;
	private JLabel lblchatGeral;

	public ViewChatPrivado (Window window, String nomeOrigem, String nomeDestino, ViewChat chat) {
		super(window);
		
		this.nomeOrigem = nomeOrigem;
		this.chat = chat;
		this.nomeDestino = nomeDestino;


		this.frame = new JFrame( nomeOrigem);
		this.frame.setLocationRelativeTo(window);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Entrou janela fechando a conversa privada!");
				LocalTime agora = LocalTime.now();
				//emissorDeMensagem.envia( "msg;" + agora + ";" + "Desconectado" + ";" + nome);
				textField.setText("");
				//ChatPrivado.this.EXIT_ON_CLOSE);
				//ChatPrivado.this.frame.hide();
				chat.removeConversaPrivada(ViewChatPrivado.this);
			}
		});
		this.panel = new JPanel();
		this.fieldChat = new JTextArea(10, 60);
		this.fieldChat.setEditable(false);
		this.scrollPane = new JScrollPane(this.fieldChat);


		this.frame.setContentPane(this.panel);
		panel.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		this.textField = new JTextField(60);
		panel_1.add(textField, BorderLayout.CENTER);
		textField.setForeground(Color.BLACK);
		this.label1 = new JLabel("Digite uma mensagem + Enter...");
		panel_1.add(label1, BorderLayout.WEST);

		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.EAST);
		this.button = new JButton("Enviar");
		panel_2.add(button);


		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldChat.setText("");
			}
		});
		panel_2.add(btnLimpar);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){  
					try {
						enviarMensagem();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});


		this.panel.add(this.scrollPane);

		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		lblchatGeral = new JLabel("Chat para : " + nomeDestino);
		lblchatGeral.setHorizontalAlignment(SwingConstants.CENTER);
		lblchatGeral.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblchatGeral, BorderLayout.SOUTH);

		class EnviaMensagemListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				try {
					enviarMensagem();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		this.button.addActionListener(new EnviaMensagemListener());
		this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.frame.setSize(700, 300);
		this.frame.setVisible(true);

	}


	public String getNomeOrigem() {
		return nomeOrigem;
	}

	public String getNomeDestino() {
		return nomeDestino;
	}


	public void adicionaMensagem(String mensagem) {
		String[] aux = mensagem.split(" ");
		if(aux[0].equalsIgnoreCase(nomeOrigem)) {
			this.fieldChat.append(mensagem.replace(aux[0], "Você") + "\n");
		}else {
			this.fieldChat.append(mensagem + "\n");
		}

		this.frame.toFront();
	}

	private void enviarMensagem() throws IOException {//arrumar isso ainda.
		LocalTime agora = LocalTime.now();

		String msg = textField.getText();
		Pacote p = new Pacote(this.idOrigem,this.idDestino,this.nomeOrigem,this.nomeDestino,msg,agora);//new Pacote(this.idOrigem, this.nomeOrigem, msg, agora);
		System.out.println("enviando msg do cliente: " + p.getMessage());
		emissorDeMensagem.envia(p);
		this.chat.get
		adicionaMensagem(p.getMessage().toString());
		textField.setText("");
	}

	public void close() {
		this.close();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		ViewChatPrivado p = (ViewChatPrivado) obj;
		return this.nomeDestino.equalsIgnoreCase(p.nomeDestino) && this.nomeOrigem.equalsIgnoreCase(p.nomeOrigem);
	}

	



}



