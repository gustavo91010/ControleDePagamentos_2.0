package maven.boleto;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import maven.infra.Conexao;
import maven.infra.ExcluirTabela;
import maven.infra.Tabela;

public class ExcluirBoletosTest {

	
	

	
	@Test
	public void deveraExcluirOBoletoSelecionado() throws SQLException {
		// cenario:
		
				LocalDate d = LocalDate.now();
				int d1 = d.getMonthValue();
				String usuario = "TesteUnitarioExcluirBoleto";
				int id = 0;
				
				

				AdicionarBoletos add = new AdicionarBoletos();
				ExcluirBoletos exc = new ExcluirBoletos();
				BoletosAPagar vizu = new BoletosAPagar();

				Conexao.getConexao();
				Tabela.criar(usuario);
				
				//Ação!
				
				List<Boleto> boletos = vizu.vizualizar(usuario, d1);
				System.out.println();
				add.adicionarContas(usuario, "Jujuba", 2, d1);
				exc.excluirBoleto(usuario, id);

				// Teste:
				
				assertTrue(boletos.size() == 0);
				ExcluirTabela.excluir(usuario);

	}

}
