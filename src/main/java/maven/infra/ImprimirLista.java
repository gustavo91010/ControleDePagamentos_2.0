package maven.infra;

import java.util.List;

import maven.boleto.Boleto;

public  class ImprimirLista {
	
	public static void imprimirLista(List<Boleto> boletos) {
		int id=1;
		for (Boleto b : boletos) {
			Double v = b.getValor();
			String v2 = v.toString();
			while (v2.length() < 7) {
				v2 += " ";
			}
			
			System.out.println("Id: " + id + " Descri: " + b.getDescricao() + ": " + v2 + ", venc: "
					+ b.getVencimento());
			id++;
		}

	}

}
