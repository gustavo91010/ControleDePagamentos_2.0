package maven.infra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import maven.entidades.Boleto;

public class ImprimirListaTest {

	@Test
	public void deveriaImprimirNaOrdemCorreta() {
//		Date hoje = new Date();
		LocalDate hoje= LocalDate.now();
		List<Boleto> boletosTest= new ArrayList<Boleto>();
		boletosTest.add(new Boleto("Luz", 333.67, hoje));
		boletosTest.add(new Boleto("agua", 213.67, hoje));
		boletosTest.add(new Boleto("net", 59, hoje));
		boletosTest.add(new Boleto("colegio", 240, hoje));
		boletosTest.add(new Boleto("coco", 1, hoje));
		ImprimirLista.imprimirLista(boletosTest);
		
		Assert.assertEquals( boletosTest.get(4).getValor(), 1, 0.1);
	}
}
