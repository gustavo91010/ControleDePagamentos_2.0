package maven.boleto;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import maven.infra.Conexao;
import maven.infra.ImprimirLista;
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
				Tabela.criarDbeTabela(usuario);
				
				//Ação!
				
				List<Boleto> boletos = vizu.vizualizar(usuario, d1);
				System.out.println();
				add.adicionarContas(usuario, "Jujuba", 2, d1);
				exc.excluirBoleto(usuario, id);
				vizu.vizualizar(usuario, d1);

				// Teste:
				
				assertTrue(boletos.size() == 0);
	}

}
