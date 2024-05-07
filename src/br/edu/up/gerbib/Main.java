package br.edu.up.gerbib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.gerbib.fileman.FileManager;
import br.edu.up.gerbib.login.Adm;
import br.edu.up.gerbib.login.User;
import br.edu.up.gerbib.modelos.Autor;
import br.edu.up.gerbib.modelos.Editora;
import br.edu.up.gerbib.modelos.Genero;
import br.edu.up.gerbib.modelos.Livro;


public class Main {

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		List<Livro> listaLivroAdm = new ArrayList<Livro>();
		File dir = new File("C:\\Banco_de_dados");
		final String DEFAULT_FILE_NAME_ADM = "biblioteca_adm.txt";
		final String DEFAULT_FILE_NAME_USER = "biblioteca_user.txt";
		FileManager.createDir(dir);		
		FileManager.createFileAdm(dir, DEFAULT_FILE_NAME_ADM);
		FileManager.createFileUser(dir, DEFAULT_FILE_NAME_USER);
		// List<Livro> listaLivroUser = new ArrayList<Livro>();

		/*
		 * Login log = new Login(); log.setAdm("adm"); log.setSenha("123");
		 * log.addAdm(); log.setAdm("adm_u"); log.setSenha("123"); log.addAdm();
		 * log.setAdm("adm_p"); log.setSenha("123"); log.addAdm();
		 */

		/*
		 * for (Map.Entry<String, String> entry : log.getCadastro_adm().entrySet()) {
		 * System.out.println(entry.getKey() + ": " + entry.getValue());
		 * 
		 * System.out.println(log.autenticar_adm("adm", "1234")); }
		 */

		do {
			System.out.println("Usuário - 1\n" + "Administrador - 2\n" + "Cadastrar novo usuário - 3\n"
					+ "Cadastrar novo administrador - 4");
			var op_1 = sc.nextInt();
			sc.nextLine();
			switch (op_1) {
			case 1:
				System.out.print("Login user: ");
				String user = sc.nextLine();
				System.out.print("Senha user: ");
				String senhaUser = sc.nextLine();
				User user_object = new User(user, senhaUser);
				if (user_object.autenticar_user(user,
						senhaUser)) /* Testa se o usuário existe e se a senha e o login estão corretos */
				{
					System.out.println("Bem vinda à sua biblioteca virtual!");
					System.out.println("Escolha uma das opções: ");
					System.out.println("1 - Adicionar um livro");
					System.out.println("2 - remover um livro");
					System.out.println("3 - Pesquisar um livro");
					System.out.println("4 - Listar todos os seus livros");
					System.out.println("5 - Voltar para o menu incial");
					var op_2 = sc.nextInt();

					switch (op_2) {
					case 1:

						break;
					case 2:
						
						break;
					case 3:
						
						break;
					case 4:
						
						break;
					default:
						break;
					}

				} else {
					System.out.println("Usuário inexistente");
				}
				break;
			case 2:
				System.out.println("Login adm: ");
				String adm = sc.nextLine();
				System.out.println("Senha adm: ");
				String senha_adm = sc.nextLine();

				Adm adm_object = new Adm(adm, senha_adm);
				adm_object.add_adm(adm, senha_adm);/*
													 * Fiz essa adição direta só pra testar, esse método só é pra ser
													 * usado quando for CRIAR um adm
													 */
				if (adm_object.autenticar_adm(adm,
						senha_adm))/* Mesma coisa que o teste do usuário, só que pra administrador */
				{
					int op_3;
					do {
						System.out.println("Gerenciamento do banco de dados da bibliota virtual");
						System.out.println("Escolha uma das opções: ");
						System.out.println("1 - Adicionar um livro");
						System.out.println("2 - remover um livro");
						System.out.println("3 - Pesquisar um livro");
						System.out.println("4 - Listar todos os livros cadastrados");
						System.out.println("5 - Voltar para o menu inicial");
						op_3 = sc.nextInt();
						sc.nextLine();

						switch (op_3) {
						case 1:

							char op_voltar;
							do {

								System.out.print("Digite o nome do Livro: \n");
								String nomeLivroAdm = sc.nextLine();
								System.out.print("Digite o Autor: \n");
								String autorAdm = sc.nextLine();
								System.out.print("Digite a editora: \n");
								String editoraAdm = sc.nextLine();
								System.out.print("Digite o gênero: \n");
								String generoAdm = sc.nextLine();
								listaLivroAdm.add(new Livro(new Autor(autorAdm), new Genero(generoAdm),
										new Editora(editoraAdm), nomeLivroAdm));
								
								FileManager.writeToFile("C:\\Banco_de_dados\\biblioteca_adm.txt", listaLivroAdm);								
								System.out.println("Livro adicionado ao banco de dados");
								System.out.println("Deseja voltar ao menu anterior?(s/n)");
								op_voltar = sc.next().charAt(0);
								sc.nextLine();
							} while (op_voltar == 'n');
							break;
						case 2:
							System.out.println("Qual livro deseja remover?");
							String livroRemover = sc.nextLine();
							if (listaLivroAdm.size() == 0) {
								System.out.println("Livro não existe no banco de dados");
							}
							for (Livro livro : listaLivroAdm) {
								if (livro.getNomeLivro().equals(livroRemover)) {
									listaLivroAdm.remove(livro);
									System.out.println();
									break;
								} else {
									System.out.println("Livro não encontrado no banco de dados");
									break;
								}
							}
							break;
						case 3:
							System.out.println("Qual livro você deseja buscar?");
							String livroBuscar = sc.nextLine();
							
							if (listaLivroAdm.size() == 0) {
								System.out.println("Livro não existe no banco de dados");
							}
							for (Livro livro : listaLivroAdm) {
								if (livro.getNomeLivro().equals(livroBuscar)) {
									System.out.println(livro.getNomeLivro().toString());
									break;
								} else {
									System.out.println("Livro não existe no banco de dados");
									break;
								}
							}
							break;
						case 4:
							if (listaLivroAdm.size() == 0) {
								System.out.println("Não existe livros no banco de dados");
							}
							for (Livro livro : listaLivroAdm) {
								System.out.println(livro.getNomeLivro());
							}
							break;
						default:
							break;
						}

					} while (op_3 != 5);

				}
			case 3:

				break;
			case 4:

				break;

			default:
				break;
			}

		} while (true);

	}

}
