package maven.infra;

import java.time.LocalDate;
import java.util.List;

import maven.entidades.Boleto;

public  class ImprimirLista {
	
	public static void imprimirLista(List<Boleto> boletos) {
		int id=1;
		for (Boleto b : boletos) {
			
			Double v = b.getValor();
			String v2 = v.toString();
			while (v2.length() < 7) {
				v2 += " ";
			}
			String descricao= b.getDescricao();
			while(descricao.length()<8) {
				descricao +=" ";
			}
			Boolean passouMesVencimento= b.getVencimento().getDayOfMonth()> LocalDate.now().getDayOfMonth();
			Boolean passouDiaVencimento= b.getVencimento().getMonthValue()>=  LocalDate.now().getMonthValue();
			
			 if( passouDiaVencimento&& passouMesVencimento ) {
				 System.out.println ("Id: " + id + " Descri: " + descricao + " " + v2 + ", venc: "
							+ b.getVencimento());
			 }else {
				 System.err.println("Id: " + id + " Descri: " + descricao + " " + v2 + ", venc: "
							+ b.getVencimento());
			 }
			
			 
			 id++;
		}

	}

}
