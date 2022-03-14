package maven.infra;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Tabela {
	
	public static void criar(String usuario) throws SQLException {

		Conexao.getConexao();
		String criarDBC = "CREATE DATABASE IF NOT EXISTS controle_de_contas";
		Statement stmt = Conexao.getConexao().createStatement();
		stmt.execute(criarDBC);
		stmt.close();

		String nomeBD = "CDC_" + usuario;
		String criarTabelas = "create table IF NOT EXISTS controle_de_contas." + nomeBD
				+ "(id INT AUTO_INCREMENT PRIMARY KEY," + " descricao VARCHAR(45) NOT NULL," + " valor DOUBLE,"
				+ " vencimento DATE NOT NULL," + "Paga TINYINT UNSIGNED NULL)";

		PreparedStatement pstm = Conexao.getConexao().prepareStatement(criarTabelas);
		pstm.execute(criarTabelas);
		pstm.close();

	}

}
