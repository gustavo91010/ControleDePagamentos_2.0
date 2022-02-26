package maven.boleto;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import maven.infra.AlimentarListaBoletos;
import maven.infra.Conexao;
import maven.infra.ImprimirLista;

public class BoletosPagos implements Vizualizar {
	Date data;
	LocalDate hoje = LocalDate.now();
	BoletosAPagar contMes = new BoletosAPagar();

	public void vizualizar(String usuario, int mes) throws SQLException {

		LocalDate hoje2 = LocalDate.of(hoje.getYear(), mes, 1);

		Conexao.getConexao();
		System.out.println("Contas do mes " + hoje2.getMonth() + " a pagar:");
		String consultaMes = "SELECT id, descricao, valor, vencimento from" + " controle_de_contas.CDC_" + usuario
				+ " where paga =1";

		ImprimirLista.imprimirLista(
				AlimentarListaBoletos.alimentarListaBoletoos(usuario, consultaMes, hoje.getMonthValue()));

	}

}
