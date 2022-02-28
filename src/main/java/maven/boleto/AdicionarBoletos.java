package maven.boleto;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import maven.infra.Conexao;
import maven.infra.Tabela;

public class AdicionarBoletos {
	@SuppressWarnings("deprecation")
	public void adicionarContas(String usuario, String descricao, double valor, int dia) throws SQLException {
		
		Tabela.criar(usuario);

		String inserirDados = "INSERT INTO  controle_de_contas.CDC_" + usuario
				+ " (descricao, valor,Vencimento,Paga ) SELECT  ?,?,?,?" + " FROM DUAL"
				+ " WHERE NOT EXISTS( SELECT descricao, Vencimento  FROM controle_de_contas.CDC_" + usuario
				+ " WHERE Vencimento = ? and descricao= ?)";

		GregorianCalendar g = new GregorianCalendar();
		int mesAtual = g.get(GregorianCalendar.MONTH);
		int anoAtual = g.get(GregorianCalendar.YEAR) - 1900;

		PreparedStatement pstm2 = Conexao.getConexao().prepareStatement(inserirDados);
		pstm2.setString(1, descricao);
		pstm2.setDouble(2, valor);
		pstm2.setDate(3, new Date(anoAtual, mesAtual, dia));
		pstm2.setBoolean(4, false);
		pstm2.setDate(5, new Date(anoAtual, mesAtual, dia));
		pstm2.setString(6, descricao);

		pstm2.execute();
		pstm2.close();
	}
	
	@SuppressWarnings("deprecation")
	public void adicionarContas(String usuario, String descricao, double valor, int dia, int recorrencia)
			throws SQLException {

		Tabela.criar(usuario);

		String inserirDados = "INSERT INTO  controle_de_contas.CDC_" + usuario
				+ " (descricao, valor,Vencimento,Paga ) SELECT  ?,?,?,?" + " FROM DUAL"
				+ " WHERE NOT EXISTS( SELECT descricao, Vencimento  FROM controle_de_contas.CDC_" + usuario
				+ " WHERE Vencimento = ? and descricao= ?)";

		GregorianCalendar g = new GregorianCalendar();
		int mesAtual = g.get(GregorianCalendar.MONTH);
		int anoAtual = g.get(GregorianCalendar.YEAR) - 1900;

		PreparedStatement pstm2 = Conexao.getConexao().prepareStatement(inserirDados);

		for (int i = 0; i <= recorrencia - 1; i++) {

			pstm2.setString(1, descricao);
			pstm2.setDouble(2, valor);
			pstm2.setDate(3, new Date(anoAtual, mesAtual + i, dia));
			pstm2.setBoolean(4, false);
			pstm2.setDate(5, new Date(anoAtual, mesAtual + i, dia));
			pstm2.setString(6, descricao);

			pstm2.execute();
		}
		pstm2.close();

	}

}
