package maven.servicos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import maven.entidades.Boleto;
import maven.infra.Conexao;

public class ExcluirBoletos {

	
	public void excluirBoleto(String usuario, int id) throws SQLException {
		LocalDate hoje = LocalDate.now();
		BoletosAPagar aPagar= new BoletosAPagar();
		List<Boleto> boletos= new ArrayList<Boleto>();
		boletos= aPagar.vizualizar(usuario,hoje.getMonthValue() );
		
		int indice= boletos.get(id).getIndice();
		String sql = "DELETE FROM controle_de_contas.CDC_" + usuario + " WHERE (id= ?)";
		PreparedStatement stmt =Conexao.getConexao().prepareStatement(sql);
		
		
		stmt.setInt(1, indice);
		stmt.execute();
		stmt.close();
		
		
	}

}
