package maven.servicos;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import maven.infra.ExcluirTabela;

public class BoletosPagosTest {
	
	AdicionarBoletos add = new AdicionarBoletos();
	BoletosAPagar aPagar = new BoletosAPagar();
	LocalDate d = LocalDate.now();
	String usuario = "BoletosPagosTest";
	
	@After
	@Before
	public void excluirTabelaDinamico() throws SQLException {
		ExcluirTabela.excluir(usuario);
	}
	
	
	@Test
	public void deveImprimirUmaListaDeBoletosPagos() throws SQLException {

		PagarBoletos pagar= new PagarBoletos();
		BoletosPagos boletosPagos= new BoletosPagos();
		
		add.adicionarContas(usuario, "Pipoca", 3, 10);
		pagar.vizualizar(usuario, 1);
		
		int tamanhoListaBoletosPagos= boletosPagos.vizualizar(usuario, d.getMonthValue()).size();
		Assert.assertEquals( 1, tamanhoListaBoletosPagos);
		
	}

}
