package maven;

import java.sql.Date;
import java.sql.SQLException;

import maven.entidades.Boleto;
import maven.servicos.AtualizarBoletos;


public class Rascunho {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {

		AtualizarBoletos atlz= new AtualizarBoletos();
		String tabelaTeste= "Rascunho";
		String teste="uhadu";
		Boleto boleto= new Boleto();
		
		
//		System.out.println(LocalDate.now().getClass().getSimpleName().equalsIgnoreCase("LocalDate") );
//		System.out.println(LocalDate.now().getClass().getSimpleName()=="LocalDate" );
		
		Date data= new Date(0);
//		System.out.println(data.getClass().getSimpleName());
		System.out.println(data);
		
		
	}

}
