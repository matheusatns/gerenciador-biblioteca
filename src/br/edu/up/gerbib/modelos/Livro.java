package br.edu.up.gerbib.modelos;


import java.util.ArrayList;
import java.util.List;

public class Livro
{
	private String nomeLivro;
	private Autor autor;
	private Genero genero;
	private Editora editora;
	private List<Livro> listaLivroBanco;
	//private List<Livro> listaLivroUser;	
	
	
	public Livro() {
		
	}

	public Livro(Autor autor, Genero genero, Editora editora, String nomeLivro)
	{
		this.autor = autor;
		this.genero = genero;
		this.editora = editora;
		this.nomeLivro = nomeLivro;
		listaLivroBanco = new ArrayList<Livro>();
	}
	
	
	
	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public Autor getAutor()
	{
		return autor;
	}

	public void setAutor(Autor autor)
	{
		this.autor = autor;
	}

	public Genero getGenero()
	{
		return genero;
	}

	public void setGenero(Genero genero)
	{
		this.genero = genero;
	}

	public Editora getEditora()
	{
		return editora;
	}

	public void setEditora(Editora editora)
	{
		this.editora = editora;
	}

	public void add_livro_adm(String nomeLivro)
	{
		listaLivroBanco.add(new Livro(getAutor(), getGenero(), getEditora(), nomeLivro));				
	}

	@Override
	public String toString()
	{
		return "Livro [autor=" + getAutor() + ", genero=" + getGenero() + ", editora=" + getEditora() + "]";
	}
	
	
	
	
	
	
	
	
}
