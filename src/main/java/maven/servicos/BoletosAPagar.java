package maven.servicos;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import maven.entidades.Boleto;
import maven.infra.AlimentarListaBoletos;
import maven.infra.ImprimirLista;

public class BoletosAPagar implements Vizualizar {

	
	

	public List<Boleto> vizualizar(String usuario, int mes) throws SQLException {
//		Tabela.criar(usuario);
		LocalDate hoje = LocalDate.now();
		LocalDate hoje2 = LocalDate.of(hoje.getYear(), mes, 1);
		LocalDate amanha = LocalDate.of(hoje.getYear(), mes, hoje2.lengthOfMonth());

		System.out.println("Contas do mes " + hoje2.getMonth() + " a pagar:");
		String sql = "SELECT id,descricao, valor, vencimento from controle_de_contas.CDC_" + usuario
				+ " where paga=0 and vencimento" + "  between '" + hoje2 + "'  AND  '" + amanha + "' ";
	

		List<Boleto> boletos= AlimentarListaBoletos.alimentarListaBoletos( sql);
		ImprimirLista.imprimirLista(boletos);
		return boletos;
	}

	

}
