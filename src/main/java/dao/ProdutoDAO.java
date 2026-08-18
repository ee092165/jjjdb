package dao;

import connection.Conexao;
import model.Produto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProdutoDAO
{
	// CREATE
	public void inserir(Produto produto)
	{
		String sql = "INSERT INTO produtos(nome, preco) VALUES (?, ?)";

		try (Connection conn = Conexao.conectar();
			 PreparedStatement stmt = conn.prepareStatement(sql))
		{

			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());

			stmt.executeUpdate();

			System.out.println("Produto cadastrado com sucesso!");

		}
		catch (Exception e)
		{
			System.out.println("Erro ao inserir produto.");
			e.printStackTrace();
		}
	}

	// READ
	public void listar()
	{
		String sql = "SELECT * FROM produtos";

		try (Connection conn = Conexao.conectar();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery())
		{

			System.out.println("\n===== LISTA DE PRODUTOS =====");

			while (rs.next())
			{
				System.out.println(rs.getInt("id") + " | " +
						rs.getString("nome") + " | R$ " +
						rs.getDouble("preco"));

			}

		}
		catch (Exception e)
		{
			System.out.println("Erro ao listar produtos.");
			e.printStackTrace();
		}
	}

	public JDialog listarSwing()
	{
		String sql = "SELECT * FROM produtos";
		Object[][] o = {{}};
		Object[] colunas = {"ID", "Nome", "Preço"};

		try (Connection conn = Conexao.conectar();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			List<Object[]> data = new ArrayList<>();
			while (rs.next())
			{
				data.add(new Object[]{
						rs.getInt("id"),
						rs.getString("nome"),
						rs.getDouble("preco")
				});
			}
			o = data.toArray(new Object[data.size()][]);
		}
		catch (Exception e)
		{
			System.out.println("Erro ao listar produtos.");
			e.printStackTrace();
		}

		DefaultTableModel modelo = new DefaultTableModel(o, colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		JDialog dialog = new JDialog();
		dialog.setTitle("Produtos");
		dialog.setBounds(100, 100, 480, 300);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.setLayout(new BorderLayout(0, 0));
		dialog.add(scrollPane, BorderLayout.CENTER);

		return dialog;
	}

	// UPDATE
	public void atualizar(int id, String nome, double preco)
	{
		String sql = "UPDATE produtos SET nome=?, preco=? WHERE id=?";

		try (Connection conn = Conexao.conectar();
			 PreparedStatement stmt = conn.prepareStatement(sql))
		{

			stmt.setString(1, nome);
			stmt.setDouble(2, preco);
			stmt.setInt(3, id);

			stmt.executeUpdate();

			System.out.println("Produto atualizado!");

		}
		catch (Exception e)
		{
			System.out.println("Erro ao atualizar.");
			e.printStackTrace();
		}
	}

	// DELETE
	public void excluir(int id)
	{
		String sql = "DELETE FROM produtos WHERE id=?";

		try (Connection conn = Conexao.conectar();
			 PreparedStatement stmt = conn.prepareStatement(sql))
		{

			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println("Produto excluído!");

		}
		catch (Exception e)
		{
			System.out.println("Erro ao excluir.");
			e.printStackTrace();
		}
	}
}
