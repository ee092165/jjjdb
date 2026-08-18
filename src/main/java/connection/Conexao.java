package connection;

import javax.swing.*;
import java.sql.*;

public class Conexao
{
	private static final String URL = "jdbc:mysql://localhost:3306/loja";
	private static final String USUARIO = "root";
	private static final String SENHA = ""; // Troque pela sua senha

	public static Connection conectar()
	{
		try
		{
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		}
		catch (SQLException e)
		{
			System.out.println("Erro ao conectar com o banco!");
			e.printStackTrace();
			return null;
		}
	}

	public static Connection swingTestar()
	{
		try
		{
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,
					"Não pode conectar ao banco SQL na primeira tentativa.",
					"Erro",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
}