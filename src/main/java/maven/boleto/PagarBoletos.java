package maven.boleto;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import maven.infra.AlimentarListaBoletos;
import maven.infra.Conexao;
import maven.infra.Tabela;

public class PagarBoletos  {

	Date data;
	LocalDate hoje = LocalDate.now();
	BoletosAPagar cont = new BoletosAPagar();
	LocalDate hoje2 = LocalDate.of(hoje.getYear(), hoje.getMonthValue(), 1);
	LocalDate amanha = LocalDate.of(hoje.getYear(), hoje.getMonthValue(), hoje.lengthOfMonth());

	public void vizualizar(String usuario, int id) throws SQLException {

		String sql = "SELECT id, descricao, valor, vencimento from controle_de_contas.CDC_" + usuario
				+ " where paga =0 and vencimento between CAST('" + hoje2 + "' AS DATE) AND CAST( '" + amanha
				+ "' AS DATE);";
		List<Boleto> boletos= AlimentarListaBoletos.alimentarListaBoletos( sql);

		Tabela.criar(usuario);

		String update = "UPDATE controle_de_contas.CDC_" + usuario + " SET Paga =1 WHERE (id= ?)";
		PreparedStatement stmt1 =Conexao.getConexao().prepareStatement(update);

		int id2 = boletos.get(id - 1).getIndice();

		stmt1.setInt(1, id2);
		stmt1.execute();
		stmt1.close();
		System.out.println("Teste: "+ boletos.size());

	}

}
