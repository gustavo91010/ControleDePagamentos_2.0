package maven.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConexao() {

		final String url = "jdbc:mysql://localhost:3306?verifyServerCertificate=false&useSSL=true&useTimezone=true&serverTimezone=UTC";
		final String usuario = "root";
		final String senha = "nn8ft85f";
		final String drive = "com.mysql.jdbc.Driver";

		try {
			Class.forName(drive);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return conexao;

	}

}
