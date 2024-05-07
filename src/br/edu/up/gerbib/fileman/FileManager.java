package br.edu.up.gerbib.fileman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

	public static void writeToFile(String path, List<Livro> livros) throws IOException {
				
		BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));		
		
		for (Livro livro : livros) {
			String line = livro.getNomeLivro() + "," + livro.getAutor().getNome() + "," + livro.getGenero().getGen() + "," + livro.getEditora().getEditora() + ";";
			writer.write(line);
			writer.newLine();
			writer.flush();
			continue;
		}
		writer.close();

	}

	public static void fileReader(String path, List<Livro> livros) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] parte = line.split(";");

			if (parte.length >= 4) {
				String nomeLivro = parte[0].trim();
				String genero = parte[1].trim();
				String autor = parte[2].trim();
				String editora = parte[3].trim();

				Livro livro = new Livro(new Autor(autor), new Genero(genero), new Editora(editora), nomeLivro);
				livros.add(livro);
			} else {

			}
		}
		reader.close();
	}

}
