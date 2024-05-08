package br.edu.up.gerbib.fileman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import br.edu.up.gerbib.modelos.Autor;
import br.edu.up.gerbib.modelos.Editora;
import br.edu.up.gerbib.modelos.Genero;
import br.edu.up.gerbib.modelos.Livro;

public final class FileManager {

	public static void createDir(File dir) {
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

	public static void createFileAdm(File dir, String fileName) throws IOException {
		File fileAdm = new File(dir, fileName);
		if (!fileAdm.exists()) {
			fileAdm.createNewFile();
		}
	}

	public static void createFileUser(File dir, String fileName) throws IOException {
		File fileUser = new File(dir, fileName);
		if (!fileUser.exists()) {
			fileUser.createNewFile();
		}

	}

	public static void writeToFile(String path, Livro livro) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
		String line = livro.getNomeLivro() + "," + livro.getAutor().getNome() + "," + livro.getGenero().getGen() + ","
				+ livro.getEditora().getEdit() + ";";
		writer.write(line);

		writer.newLine();
		writer.flush();
		writer.close();

	}

	public static void removeFromFile(String path, Livro livro) throws IOException {
		// Cria uma lista temporária para armazenar as linhas do arquivo
		List<String> linhasTemporarias = new ArrayList<>();

		// Abre o arquivo original para leitura
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String linha;

		// Lê linha por linha e adiciona à lista temporária
		while ((linha = reader.readLine()) != null) {
			linhasTemporarias.add(linha);
		}

		reader.close(); 
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));

		// Variável para indicar se o livro foi removido
		boolean livroRemovido = false;

		// Percorre todas as linhas temporárias
		for (String linhaTemporaria : linhasTemporarias) {
			// Divide a linha temporária em partes separadas por ","
			String[] partes = linhaTemporaria.split(",");

			// Verifica se a linha contém o livro
			if (!partes[0].trim().equals(livro.getNomeLivro())) {
				// Se não for o livro a ser removido, escreve no arquivo
				writer.write(linhaTemporaria);
				writer.newLine();
			} else {
				livroRemovido = true;
			}
		}

		writer.close(); // Fecha o escritor

		
		if (livroRemovido) {
			System.out.println("Livro removido com sucesso!");
		} else {
			System.out.println("Livro não encontrado no banco de dados");
		}
	}

	public static void listarLivros(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;

		while ((line = reader.readLine()) != null) {
			String[] partes = line.split(",");

			if (partes.length >= 4) {
				String nomeLivro = partes[0].trim();
				String autor = partes[1].trim();
				String genero = partes[2].trim();
				String editora = partes[3].trim();

				
				Livro livro = new Livro(new Autor(autor), new Genero(genero), new Editora(editora), nomeLivro);

				
				System.out.println("Nome do Livro: " + livro.getNomeLivro());
				System.out.println("Autor: " + livro.getAutor().getNome());
				System.out.println("Gênero: " + livro.getGenero().getGen());
				System.out.println("Editora: " + livro.getEditora().getEdit());
				System.out.println("---------------------------------------");
			}
		}

		reader.close();
	}

	public static void buscarLivro(String path, String nomeLivro) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		boolean livroEncontrado = false;

		while ((line = reader.readLine()) != null) {
			String[] partes = line.split(",");

			if (partes.length >= 4) {
				String nomeLivroEncontrado = partes[0].trim();
				String autor = partes[1].trim();
				String genero = partes[2].trim();
				String editora = partes[3].trim();

				// Se o nome do livro encontrado corresponder ao nome do livro buscado
				if (nomeLivroEncontrado.equalsIgnoreCase(nomeLivro)) {
					
					Livro livro = new Livro(new Autor(autor), new Genero(genero), new Editora(editora),
							nomeLivroEncontrado);

					
					System.out.println("Livro encontrado:");
					System.out.println("Nome do Livro: " + livro.getNomeLivro());
					System.out.println("Autor: " + livro.getAutor().getNome());
					System.out.println("Gênero: " + livro.getGenero().getGen());
					System.out.println("Editora: " + livro.getEditora().getEdit());

					livroEncontrado = true;
					break;
				}
			}
		}

		reader.close();

		if (!livroEncontrado) {
			System.out.println("Livro não encontrado.");
		}
	}
	private static boolean verificarLivroExistente(String path, String nomeLivro) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		
		while ((line = reader.readLine()) != null) {
			String[] partes = line.split(",");
			
			if (partes.length >= 1) {
				String nomeLivroEncontrado = partes[0].trim();
				
				if (nomeLivroEncontrado.equalsIgnoreCase(nomeLivro)) {
					reader.close();
					return true;
				}
			}
		}
		
		reader.close();
		return false;
	}
	
	private static void copiarLivroParaUsuario(String pathAdm, String pathUser, String nomeLivro) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(pathAdm));
		BufferedWriter writer = new BufferedWriter(new FileWriter(pathUser, true));
		String line;
		
		while ((line = reader.readLine()) != null) {
			String[] partes = line.split(",");
			
			if (partes.length >= 1) {
				String nomeLivroEncontrado = partes[0].trim();
				
				if (nomeLivroEncontrado.equalsIgnoreCase(nomeLivro)) {
					writer.write(line);
					writer.newLine();
				}
			}
		}
		
		reader.close();
		writer.close();
	}
	public static void adicionarLivroUser(String pathAdm, String pathUser, String nomeLivro) throws IOException {
	    // Verifica se o livro existe no arquivo do administrador
	    boolean livroEncontrado = verificarLivroExistente(pathAdm, nomeLivro);
	    
	    if (livroEncontrado) {
	        // Se o livro existe, copia para o arquivo do usuário
	        copiarLivroParaUsuario(pathAdm, pathUser, nomeLivro);
	        System.out.println("Livro adicionado com sucesso!");
	    } else {
	        System.out.println("Livro não encontrado no banco de dados do administrador. Não foi possível adicionar.");
	    }
	}
	
	public static void removerLivroUser(String pathUser, String nomeLivro) throws IOException {
	    boolean livroRemovido = removeFromFile(pathUser, nomeLivro);
	    if (livroRemovido) {
	        System.out.println("Livro removido com sucesso!");
	    } else {
	        System.out.println("Livro não encontrado na sua biblioteca. Nada foi removido.");
	    }
	}

	public static boolean removeFromFile(String path, String nomeLivro) throws IOException {
	    List<String> linhasTemporarias = new ArrayList<>();

	    BufferedReader reader = new BufferedReader(new FileReader(path));
	    String linha;

	    boolean livroRemovido = false;

	    while ((linha = reader.readLine()) != null) {
	        String[] partes = linha.split(",");

	        if (partes.length >= 1) {
	            String nomeLivroEncontrado = partes[0].trim();

	            if (!nomeLivroEncontrado.equalsIgnoreCase(nomeLivro)) {
	                linhasTemporarias.add(linha);
	            } else {
	                livroRemovido = true;
	            }
	        }
	    }

	    reader.close();

	    BufferedWriter writer = new BufferedWriter(new FileWriter(path));

	    for (String linhaTemporaria : linhasTemporarias) {
	        writer.write(linhaTemporaria);
	        writer.newLine();
	    }

	    writer.close();

	    return livroRemovido;
	}

	public static void pesquisarLivroUser(String pathUser, String nomeLivro) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader(pathUser));
	    String line;
	    boolean livroEncontrado = false;

	    while ((line = reader.readLine()) != null) {
	        String[] partes = line.split(",");

	        if (partes.length >= 1) {
	            String nomeLivroEncontrado = partes[0].trim();

	            if (nomeLivroEncontrado.equalsIgnoreCase(nomeLivro)) {
	                System.out.println("Livro encontrado:");
	                System.out.println(line);
	                livroEncontrado = true;
	                break;
	            }
	        }
	    }

	    reader.close();

	    if (!livroEncontrado) {
	        System.out.println("Livro não encontrado na sua biblioteca.");
	    }
	}

	public static void listarLivroUser(String pathUser) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader(pathUser));
	    String line;

	    while ((line = reader.readLine()) != null) {
	        System.out.println(line);
	    }

	    reader.close();
	}





	public static void adicionarLivroUser() {

	}

	public static void removerLivroUser() {

	}

	public static void pesquisarLivroUser() {

	}

	public static void listarLivroUser() {

	}

}
