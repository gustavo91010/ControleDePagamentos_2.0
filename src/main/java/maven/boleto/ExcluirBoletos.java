package maven.boleto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import maven.infra.Conexao;

public class ExcluirBoletos {

	
	public void excluirBoleto(String usuario, int id) throws SQLException {
		String sql = "DELETE FROM controle_de_contas.CDC_" + usuario + " WHERE (id= ?)";
		PreparedStatement stmt =Conexao.getConexao().prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		
		
	}

}
