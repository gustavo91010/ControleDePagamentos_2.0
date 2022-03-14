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

public class BoletosVencidosTest {
	Boleto boleto;
	String usuario = "BoletosVencidos";

	@After
	@Before
	public void excluirTabelaDinamico() throws SQLException {
		ExcluirTabela.excluir(usuario);
	}
	
	@Test
	public void soDeveMostrarContasVencidas() throws SQLException {
		// cenario:
		
		
		int mes = 3;
		
		
		LocalDate d = LocalDate.now();
		int hoje= d.getDayOfMonth();
		
		
		AdicionarBoletos add = new AdicionarBoletos();
		add.adicionarContas(usuario, "teste", 333.33, hoje);

		BoletosVencidos boletosVencidos = new BoletosVencidos();


		// ação:
		List<Boleto> b = boletosVencidos.vizualizar(usuario, mes);
				for (Boleto item : b) {

			// Verificação:
			boolean verificacao = item.getVencimento().getDayOfMonth() > hoje;
			Assert.assertFalse(verificacao);
		}
	}

}
