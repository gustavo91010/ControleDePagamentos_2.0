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


public class AdicionarBoletosTest {
	AdicionarBoletos add = new AdicionarBoletos();
	BoletosAPagar aPagar = new BoletosAPagar();
	LocalDate d = LocalDate.now();
	String usuario = "AdicionarBoletosTest";
	
	@After
	@Before
	public void excluirTabelaDinamico() throws SQLException {
		ExcluirTabela.excluir(usuario);
	}

	@Test
	public void deveriaAdicionarItemAoBancoDados() throws SQLException {

		add.adicionarContas(usuario, "jujuba", 1, 10);
		List<Boleto> boletos = aPagar.vizualizar(usuario, d.getMonthValue());
		Assert.assertEquals(boletos.size(), 1);

	}

	
	@Test
	public void deveriaAdicionarItemAoBancoDados2() throws SQLException {

		add.adicionarContas(usuario, "pipoca", 1, 10, 5);
		List<Boleto> boletos = aPagar.vizualizar(usuario, d.getMonthValue());
		Assert.assertEquals(boletos.size(), 1);

	}

}
