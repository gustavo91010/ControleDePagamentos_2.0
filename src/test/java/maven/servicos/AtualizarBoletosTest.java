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

public class AtualizarBoletosTest {

	private AtualizarBoletos atlz = new AtualizarBoletos();
	private Vizualizar vizu = new Vizualizar();
	private Boleto boleto;
	private List<Boleto> boletos;
	private int mesAtual = LocalDate.now().getMonthValue();
	private String tabelaTeste = "UsuarioTeste";
	private String teste = "teste";

	@Before
	@After
	public void setup() throws SQLException {
		ExcluirTabela.excluir(tabelaTeste);
	}

	@Test
	public void deveAdicionarBoletoNaTabelaNoBancoDeDados() throws SQLException {

		boleto = atlz.adicionarBoleto(tabelaTeste, teste, 2, 10);

		Assert.assertEquals(boleto.getDescricao(), teste);
	}

	@Test
	public void deveAdicionarBoletosComMesesEmSequencia() throws SQLException {
		// cenario
		int reccorencia = 7;
		boletos = atlz.adicionarBoleto(tabelaTeste, teste, 2, 10, reccorencia);

		// verificação:

		int quintoMes = boletos.get(4).getVencimento().getMonthValue();
		int verificacao = mesAtual + 5;

		// ação:

		Assert.assertEquals(quintoMes, verificacao);
		Assert.assertEquals(boletos.size(), reccorencia);

	}

	@Test
	public void soDeveTerUmBoletoNaLista() throws SQLException {
		// cenario
		atlz.adicionarBoleto(tabelaTeste, teste, 2, 1);
		atlz.adicionarBoleto(tabelaTeste, teste, 2, 2);
		atlz.adicionarBoleto(tabelaTeste, teste, 2, 3);

		// verificação:
		atlz.excluirBoleto(tabelaTeste, 1);
		atlz.excluirBoleto(tabelaTeste, 2);

//		ação:
		Assert.assertEquals(vizu.boletosDoMes(tabelaTeste, mesAtual).size(), 1);

	}

	@Test
	public void deveHaverUmBoletoPago() throws SQLException {
		// cenario
		 atlz.adicionarBoleto(tabelaTeste, teste, 2, 1);
		 atlz.adicionarBoleto(tabelaTeste, teste, 2, 2);
		 atlz.adicionarBoleto(tabelaTeste, teste, 2, 3);
		
		// verificação:
//		vizu.boletosDoMes(tabelaTeste, mesAtual).size();
		atlz.pagarBoleto(tabelaTeste, 1);
		int boletosTotal= vizu.boletosDoMes(tabelaTeste, mesAtual).size();
		int boletosPagos=vizu.boletosPagos(tabelaTeste, mesAtual).size();
//		ação:
		Assert.assertFalse(boletosTotal ==3);
		Assert.assertEquals(boletosPagos, 1);
	}

	
	
}
