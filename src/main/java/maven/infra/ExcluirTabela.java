package maven.infra;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExcluirTabela {
	
	public static void excluir(String usuario) throws SQLException {
		
		String sql = "DROP TABLE  IF EXISTS controle_de_contas.cdc_" + usuario;

		PreparedStatement pstm = Conexao.getConexao().prepareStatement(sql);
		pstm.execute(sql);
		pstm.close();
	}

}
