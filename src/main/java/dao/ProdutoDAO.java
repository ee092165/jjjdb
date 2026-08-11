package dao;

import connection.Conexao;
import model.Produto;

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
