import dao.ProdutoDAO;
import model.Produto;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		ProdutoDAO dao = new ProdutoDAO();

		int opcao;

		do
		{
			System.out.print(
				"\n==============================" +
				"\n CRUD DE PRODUTOS" +
				"\n==============================" +
				"\n1 - Cadastrar Produto" +
				"\n2 - Listar Produtos" +
				"\n3 - Atualizar Produto" +
				"\n4 - Excluir Produto" +
				"\n5 - Sair" +
				"\nEscolha uma opção: "
			);

			opcao = sc.nextInt();
			sc.nextLine();

			int id;
			String nome;
			double preco;

			switch (opcao)
			{
				case 1:
					System.out.print("Nome: ");
					nome = sc.nextLine();

					System.out.print("Preço: ");
					preco = sc.nextDouble();

					Produto produto = new Produto(nome, preco);

					dao.inserir(produto);
					break;

				case 2:
					dao.listar();
					break;

				case 3:
					System.out.print("ID do produto: ");
					id = sc.nextInt();
					sc.nextLine();

					System.out.print("Novo nome: ");
					nome = sc.nextLine();

					System.out.print("Novo preço: ");
					preco = sc.nextDouble();

					dao.atualizar(id, nome, preco);
					break;

				case 4:
					System.out.print("ID do produto: ");
					id = sc.nextInt();

					dao.excluir(id);
					break;

				case 5:
					System.out.println("Programa encerrado!");
					break;

				default:
					System.out.println("Opção inválida!");
			}
		}
		while (opcao != 5);

		sc.close();
	}
}