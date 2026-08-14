package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.event.*;

public class Formulario implements Runnable
{
	public void run()
	{
		ProdutoDAO dao = new ProdutoDAO();

		JFrame frame = new JFrame("Crud JDBC");
		frame.setSize(480, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		JButton[] buttons = {
			new JButton("Cadastrar Produto"),
			new JButton("Listar Produtos"),
			new JButton("Atualizar Produto"),
			new JButton("Excluir Produto"),
			new JButton("Sair")
		};

		int width = 100, height = 80;
		for (int i = 0; i < buttons.length; i++)
		{
			JButton but = buttons[i];
			but.setBounds(240 - width, 48 + (48 * i), width, height);

			switch (i)
			{
				case 0:
					but.addActionListener(e ->
					{
						String nome = JOptionPane.showInputDialog(null, "Digite o nome", "Nome do produto", JOptionPane.QUESTION_MESSAGE);
						double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço", "Preço do produto", JOptionPane.QUESTION_MESSAGE));

						Produto produto = new Produto(nome, preco);

						dao.inserir(produto);
					});
					break;

				case 1:
					but.addActionListener(e -> dao.listar());
					break;

				case 2:
					but.addActionListener(e ->
					{
						int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID", "ID do produto", JOptionPane.QUESTION_MESSAGE));
						String nome = JOptionPane.showInputDialog(null, "Digite o nome", "Nome do produto", JOptionPane.QUESTION_MESSAGE);
						double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço", "Preço do produto", JOptionPane.QUESTION_MESSAGE));

						dao.atualizar(id, nome, preco);
					});
					break;

				case 3:
					but.addActionListener(e ->
					{
						int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID", "ID do produto", JOptionPane.QUESTION_MESSAGE));

						dao.excluir(id);
					});
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
