package maven;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import maven.boleto.AdicionarBoletos;
import maven.boleto.Boleto;
import maven.boleto.BoletosAPagar;
import maven.boleto.ExcluirBoletos;
import maven.infra.Conexao;
import maven.infra.Tabela;

public class RascunhoTest {
	public static void main(String[] args) throws SQLException {

		Date hoje = new Date();

//		List<Boleto> boletosTest= new ArrayList<Boleto>();
//	boletosTest.add(new Boleto("Luz", 333.67, hoje));
//	boletosTest.add(new Boleto("agua", 213.67, hoje));
//	boletosTest.add(new Boleto("net", 59, hoje));
//	boletosTest.add(new Boleto("colegio", 240, hoje));
//	boletosTest.add(new Boleto("coco", 1, hoje));
//	ImprimirLista.imprimirLista(boletosTest);
//	
//System.out.println(boletosTest.get(4).getValor()== 1);
//	
		
		// cenario:
		
		LocalDate d = LocalDate.now();
		int d1 = d.getMonthValue();
		String usuario = "teste_unitario";
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
		System.out.println(boletos.size() == 0);

	}

}
