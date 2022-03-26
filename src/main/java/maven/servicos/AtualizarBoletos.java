package maven.servicos;

import static maven.infra.Tabela.criar;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import maven.entidades.Boleto;
import maven.infra.Conexao;
import maven.infra.Tabela;

public class AtualizarBoletos {

	private Vizualizar vizualizar = new Vizualizar();
	private int mesAtual = LocalDate.now().getMonthValue();
	private int anoAtual = LocalDate.now().getYear();
	private Date date;

	public Boleto adicionarBoleto(String usuario, String descricao, double valor, int dia) throws SQLException {
		Tabela.criar(usuario);

		String inserirDados = "INSERT INTO  controle_de_contas.CDC_" + usuario
				+ " (descricao, valor,Vencimento,Paga ) SELECT  ?,?,?,?" + " FROM DUAL"
				+ " WHERE NOT EXISTS( SELECT descricao, Vencimento  FROM controle_de_contas.CDC_" + usuario
				+ " WHERE Vencimento = ? and descricao= ?)";

		LocalDate vencimento = LocalDate.of(anoAtual, mesAtual, dia);
		Date date = java.sql.Date.valueOf(vencimento);

		PreparedStatement pstm2 = Conexao.getConexao().prepareStatement(inserirDados);
		pstm2.setString(1, descricao);
		pstm2.setDouble(2, valor);
		pstm2.setDate(3, date);
		pstm2.setBoolean(4, false);
		pstm2.setDate(5, date);
		pstm2.setString(6, descricao);

		pstm2.execute();
		pstm2.close();

		Boleto boleto = new Boleto(descricao, valor, vencimento);
		return boleto;
	}

	@SuppressWarnings("deprecation")
	public List<Boleto> adicionarBoleto(String usuario, String descricao, double valor, int dia, int recorrencia)
			throws SQLException {

		Tabela.criar(usuario);

		String inserirDados = "INSERT INTO  controle_de_contas.CDC_" + usuario
				+ " (descricao, valor,Vencimento,Paga ) SELECT  ?,?,?,?" + " FROM DUAL"
				+ " WHERE NOT EXISTS( SELECT descricao, Vencimento  FROM controle_de_contas.CDC_" + usuario
				+ " WHERE Vencimento = ? and descricao= ?)";

		LocalDate vencimento = LocalDate.of(anoAtual, mesAtual, dia);

		if (dia < LocalDate.now().getDayOfMonth()) {
			mesAtual = LocalDate.now().getMonthValue() + 1;
		}
		date = java.sql.Date.valueOf(vencimento);

		PreparedStatement pstm2 = Conexao.getConexao().prepareStatement(inserirDados);
		List<Boleto> boletos = new ArrayList<Boleto>();

		for (int i = 0; i <= recorrencia - 1; i++) {
			int mesVariavel = mesAtual++;

			pstm2.setString(1, descricao);
			pstm2.setDouble(2, valor);
			date.setMonth(mesVariavel - 1);
			pstm2.setDate(3, date);
			pstm2.setBoolean(4, false);
			pstm2.setDate(5, date);
			pstm2.setString(6, descricao);

			pstm2.execute();

			LocalDate venc = LocalDate.of(anoAtual, mesVariavel, dia);

			boletos.add(new Boleto(descricao, valor, venc));
		}
		pstm2.close();
		return boletos;

	}

	public void excluirBoleto(String usuario, int indice) throws SQLException {
		String sql = "DELETE FROM controle_de_contas.CDC_" + usuario + " WHERE (id= ?)";
		PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

		stmt.setInt(1, indice);
		stmt.execute();
		stmt.close();

	}

	public void pagarBoleto(String usuario, int indice) throws SQLException {

		List<Boleto> boletos = vizualizar.boletosDoMes(usuario, mesAtual);
		int indiceReal = boletos.get(indice - 1).getIndice();
		criar(usuario);

		String update = "UPDATE controle_de_contas.CDC_" + usuario + " SET Paga =1 WHERE (id= ?)";
		PreparedStatement stmt1 = Conexao.getConexao().prepareStatement(update);

		stmt1.setInt(1, indiceReal);
		stmt1.execute();
		stmt1.close();

	}

//	public LocalDate converteDateLocalDate(Date dateToConvert) {
//		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	}

//	public Date convertLocalDateDate(LocalDate dateToConvert) {
//		return java.sql.Date.valueOf(dateToConvert);
//	}

}
