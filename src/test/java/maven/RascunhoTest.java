package maven;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Ignore;

import builder.BoletosBuilder;
import maven.infra.Conexao;
import maven.infra.ExcluirTabela;
import maven.infra.Tabela;
import maven.servicos.AdicionarBoletos;
import maven.servicos.BoletosAPagar;
import maven.servicos.BoletosVencidos;
import maven.servicos.PagarBoletos;

public class RascunhoTest {

	@Ignore
	public static void main(String[] args) throws SQLException {
//		AdicionarBoletos add = new AdicionarBoletos();
//		BoletosAPagar aPagar = new BoletosAPagar();
//		LocalDate d = LocalDate.now();
//		String usuario = "BoletosPagosTest";
//		add.adicionarContas(usuario, "Pipoca", 3, 10);
//		PagarBoletos pagar = new PagarBoletos();
//		pagar.vizualizar(usuario, 1);
	
	
//		BoletosVencidos boletosVencidos= BoletosVencidosBuilder.boletoVencido().agora();		
//		LocalDate d = LocalDate.now();
//		int hoje= d.getDayOfMonth();
//		System.out.println(hoje);
		Date input = new Date(1l);
		System.out.println(input);
	}

}
