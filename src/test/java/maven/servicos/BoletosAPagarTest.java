package maven.servicos;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import maven.entidades.Boleto;
import maven.infra.ExcluirTabela;
import maven.infra.Tabela;
import maven.servicos.AdicionarBoletos;
import maven.servicos.BoletosAPagar;

public class BoletosAPagarTest {
	String usuario = "BoletosAPagarTest";
	
	@After
	@Before
	public void excluirTabelaDinamico() throws SQLException {
		ExcluirTabela.excluir(usuario);
	}

	@Test
	public void deveMostrarQuantidadeBoletosNoMes() throws SQLException {

		// Cenario:
		AdicionarBoletos add = new AdicionarBoletos();
		BoletosAPagar aPagar = new BoletosAPagar();
		LocalDate d = LocalDate.now();
		int d2 = d.getMonthValue();
		

		// Ação:
		
		Tabela.criar(usuario);
		add.adicionarContas(usuario, "jujuba", 1.3, 10);

		List<Boleto> aPagaer2 = aPagar.vizualizar(usuario, d2);


		// Teste:
		Assert.assertEquals(aPagaer2.size(), 1);
//		ExcluirTabela.excluir(usuario);


	}

}
