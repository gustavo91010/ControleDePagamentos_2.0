package maven.boleto;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import maven.infra.AlimentarListaBoletos;
import maven.infra.ImprimirLista;
import maven.infra.Tabela;

public class BoletosAPagar implements Vizualizar {

	Date data;
	LocalDate hoje = LocalDate.now();
	LocalDate hoje2 = LocalDate.of(hoje.getYear(), hoje.getMonth(), 1);
	LocalDate amanha = LocalDate.of(hoje.getYear(), hoje.getMonth(), hoje.lengthOfMonth());


	public void vizualizar(String usuario, int mes) throws SQLException {
		Tabela.criarDbeTabela(usuario);
		hoje2 = LocalDate.of(hoje.getYear(), mes, 1);
		amanha = LocalDate.of(hoje.getYear(), mes, hoje.lengthOfMonth());

		System.out.println("Contas do mes " + hoje2.getMonth() + " a pagar:");
		String sql = "SELECT id,descricao, valor, vencimento from controle_de_contas.CDC_" + usuario
				+ " where paga=0 and vencimento" + "  between '" + hoje2 + "'  AND  '" + amanha + "' ";
	

		List<Boleto> boletos= AlimentarListaBoletos.alimentarListaBoletoos(usuario, sql, mes);
		ImprimirLista.imprimirLista(boletos);
	}

	

}
