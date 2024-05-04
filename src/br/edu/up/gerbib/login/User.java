package br.edu.up.gerbib.login;

import java.util.HashMap;

public class User extends Login
{
	private HashMap<String, String> cadastro_user;

	public User(String user, String senha)
	{
		super(user, senha);
		cadastro_user = new HashMap<String, String>();
	}
	
	public boolean autenticar_user(String user, String senha)
	{
		if (cadastro_user.containsKey(user))
		{
			String senhaCadastrada = cadastro_user.get(user);
			return senhaCadastrada.equals(senha);			
		}
		else
		{
			return false;			
		}
	}
	
	public void add_user(String user, String senha)
	{
		cadastro_user.put(user, senha);
	}
	
	
}
