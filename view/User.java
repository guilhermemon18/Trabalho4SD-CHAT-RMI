package view;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	
	public User(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		User u = (User) obj;
		return u != null && this.nome.equalsIgnoreCase(u.nome);
	}
	
	
}
