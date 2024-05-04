package br.edu.up.gerbib.login;


/*Fiz Login como superclasse e as classes User e Adm como subclasses.
 * Fiz isso pra conseguir criar 2 listas de cadastro de usuário normal e administrador
 * e não precisar criar atributos para cada classe*/

public  class Login
{
	private static String senhaMestra = "123456";	
	private String login;
	private String senha;
	
	public Login(String login, String senha)
	{
		this.login = login;
		this.senha = senha;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public static String getSenhaMestra()
	{
		return senhaMestra;
	}

	public String getSenha()
	{
		return senha;
	}
	
	public static boolean autenticar_senha_mestra(String senha)
	{
		if (senha == getSenhaMestra())
		{
			return true;			
		}
		else				
		{
			return false;			
		}		
	}
	
	
	
	
}
