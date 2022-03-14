package maven.servicos;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import maven.entidades.Boleto;
import maven.infra.AlimentarListaBoletos;
import maven.infra.Conexao;
import maven.infra.ImprimirLista;

public class BoletosPagos implements Vizualizar {
	Date data;
	LocalDate hoje = LocalDate.now();
	BoletosAPagar contMes = new BoletosAPagar();

	public List<Boleto> vizualizar(String usuario, int mes) throws SQLException {

		LocalDate hoje2 = LocalDate.of(hoje.getYear(), mes, 1);

		Conexao.getConexao();
		System.out.println("Contas do mes " + hoje2.getMonth() + " a pagar:");
		String consultaMes = "SELECT id, descricao, valor, vencimento from" + " controle_de_contas.CDC_" + usuario
				+ " where paga =1";

		List<Boleto> boletos= AlimentarListaBoletos.alimentarListaBoletos(consultaMes);
		ImprimirLista.imprimirLista(boletos);
		return boletos;

	}

}
