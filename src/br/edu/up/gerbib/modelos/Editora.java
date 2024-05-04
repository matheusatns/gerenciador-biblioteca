package br.edu.up.gerbib.modelos;

public class Editora
{
	private String editora;

	public Editora(String editora)
	{
		this.editora = editora;
	}

	public String getEditora()
	{
		return editora;
	}

	public void setEditora(String editora)
	{
		this.editora = editora;
	}

	@Override
	public String toString()
	{
		return "Editora [editora=" + editora + "]";
	}
	
	
}
