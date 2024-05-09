package br.edu.up.gerbib.login;

import java.util.HashMap;

public class User extends Login {
	private static HashMap<String, String> cadastro_user;

	public User(String user, String senha) {
		super(user, senha);
		if (cadastro_user == null) {
			cadastro_user = new HashMap<>();
		}
	}

	public User() {
		if (cadastro_user == null) {
			cadastro_user = new HashMap<>();
		}

	}

	public boolean autenticar_user(String user, String senha) {
		if (cadastro_user == null || !cadastro_user.containsKey(user)) { 
			System.out.println("Usuário não existe.");
			return false;
		}

		String senhaCadastrada = cadastro_user.get(user);
		return senhaCadastrada.equals(senha);
	}

	public void add_user() {
		cadastro_user.put(super.getLogin(), super.getSenha());
	}

}
