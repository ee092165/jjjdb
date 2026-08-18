package view;

import connection.Conexao;
import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.event.*;

public class Formulario implements Runnable
{
	public void run()
	{
		ProdutoDAO dao = new ProdutoDAO();

		// Criar janela
		JFrame frame = new JFrame("Crud JDBC");
		frame.setSize(254, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);

		// Tentar conectar ao banco SQL uma vez para verificar se funciona. Se não funcionar um diálogo irá aparecer
		// Lembrando que se o banco ir online após essa mensagem, o aplicativo irá funcionar ainda
		Conexao.swingTestar();

		JButton[] buttons = {
			new JButton("Cadastrar Produto"),
			new JButton("Listar Produtos"),
			new JButton("Atualizar Produto"),
			new JButton("Excluir Produto"),
			new JButton("Sair")
		};

		// Adicionar botões em um loop
		int width = 192, height = 24;
		for (int i = 0; i < buttons.length; i++)
		{
			JButton but = buttons[i];
			but.setBounds(120 - (width / 2), 12 + (48 * i), width, height);

			// Funções dos botões
			switch (i)
			{
				case 0:
					but.addActionListener(e ->
					{
						try {
							String nome = JOptionPane.showInputDialog(null, "Digite o nome", "Nome do produto", JOptionPane.QUESTION_MESSAGE);
							double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço", "Preço do produto", JOptionPane.QUESTION_MESSAGE).replace(',', '.'));

							Produto produto = new Produto(nome, preco);

							dao.inserir(produto);
						}
						catch (Exception erro)
						{
							// Usuário provavelmente só fechou a janela.
						}
					});
					break;

				case 1:
					but.addActionListener(e -> dao.listarSwing());
					break;

				case 2:
					but.addActionListener(e ->
					{
						try {
							int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do produto para atualizar", "ID do produto", JOptionPane.QUESTION_MESSAGE));
							String nome = JOptionPane.showInputDialog(null, "Digite o novo nome", "Nome do produto", JOptionPane.QUESTION_MESSAGE);
							double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o novo preço", "Preço do produto", JOptionPane.QUESTION_MESSAGE).replace(',', '.'));

							dao.atualizar(id, nome, preco);

							new JDialog().setTitle("Successo!");
						}
						catch(Exception erro) {}
					});
					break;

				case 3:
					but.addActionListener(e ->
					{
						try {
							int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do produto para excluir", "ID do produto", JOptionPane.QUESTION_MESSAGE));
							dao.excluir(id);
						}
						catch(Exception erro) {}
					});
					break;

				case 4:
					but.addActionListener(e -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
					break;
			}

			frame.add(but);
		}

		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Formulario());
	}
}
