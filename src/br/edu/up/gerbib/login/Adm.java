package br.edu.up.gerbib.login;

import java.util.HashMap;

public class Adm extends Login
{
	private HashMap<String, String> cadastro_adm;

	public Adm(String adm, String senha)
	{
		super(adm, senha);
		cadastro_adm = new HashMap<String, String>();
	}
	
	public boolean autenticar_adm(String adm, String senha)
	{
		if (cadastro_adm.containsKey(adm))
		{
			String senhaCadastrada = cadastro_adm.get(adm);
			return senhaCadastrada.equals(senha);
			
		}else {
			return false;
		}
		
	}
	public void add_adm(String adm, String senha)
	{
		cadastro_adm.put(adm, senha);
	}
	
	
	
	

}
