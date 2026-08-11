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
			System.out.println("\n==============================");
			System.out.println(" CRUD DE PRODUTOS");
			System.out.println("==============================");
			System.out.println("1 - Cadastrar Produto");
			System.out.println("2 - Listar Produtos");
			System.out.println("3 - Atualizar Produto");
			System.out.println("4 - Excluir Produto");
			System.out.println("5 - Sair");
			System.out.print("Escolha uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao)
			{
				case 1:
					System.out.print("Nome: ");
					String nome = sc.nextLine();

					System.out.print("Preço: ");
					double preco = sc.nextDouble();

					Produto produto = new Produto(nome, preco);

					dao.inserir(produto);
					break;

				case 2:
					dao.listar();
					break;

				case 3:
					System.out.print("ID do produto: ");
					int id = sc.nextInt();
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