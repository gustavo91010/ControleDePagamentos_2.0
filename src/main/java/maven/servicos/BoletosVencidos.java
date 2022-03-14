package maven.servicos;

import java.sql.SQLException;

import java.time.LocalDate;
import java.util.List;

import maven.entidades.Boleto;
import maven.infra.AlimentarListaBoletos;
import maven.infra.ImprimirLista;

public class BoletosVencidos implements Vizualizar {
	private LocalDate hoje = LocalDate.now();

	public List<Boleto> vizualizar(String usuario, int mes) throws SQLException {
		
		LocalDate hoje2 = LocalDate.of(hoje.getYear(),  mes, 1);
		LocalDate amanha = LocalDate.of(hoje2.getYear(), hoje2.getMonthValue(), hoje2.getDayOfMonth());

		System.out.println("Boletos vencidos ate o dia: "+amanha.getDayOfMonth() +" / "+amanha.getMonthValue());
		System.out.println();
		
		String sql = "SELECT id,descricao, valor, vencimento from controle_de_contas.CDC_" + usuario
				+ " where paga=0 and vencimento < curdate();" ;
		List<Boleto> boletos= AlimentarListaBoletos.alimentarListaBoletos(sql);
		ImprimirLista.imprimirLista(boletos);
		
		
		
	
		
		
		return boletos;

	}

}
