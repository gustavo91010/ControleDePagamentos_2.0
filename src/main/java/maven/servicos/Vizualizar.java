package maven.servicos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import maven.entidades.Boleto;
import maven.infra.Conexao;

public class Vizualizar {
	
private	LocalDate hoje = LocalDate.now();
private	LocalDate hoje2 ;
private	LocalDate amanha ;

	
	public static List<Boleto> alimentarListaBoletos(String sql) throws SQLException {
		Date data;

		Statement stmt = Conexao.getConexao().createStatement();
		ResultSet resultado = stmt.executeQuery(sql);
		List<Boleto> boletos = new ArrayList<Boleto>();

		while (resultado.next()) {

			int valor = resultado.getInt("valor");
			String descricao = resultado.getString("descricao");
			int indice = resultado.getInt("id");

			while (descricao.length() < 10) {
				descricao += " ";
			}
			data = resultado.getDate("vencimento");

			LocalDate data2 = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(data));

			boletos.add(new Boleto(descricao, valor, data2, indice));
		}
		stmt.close();
		resultado.close();
		return boletos;
	}
	
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
	
	public List<Boleto> boletosDoMes(String usuario, int mes) throws SQLException {
		hoje2 = LocalDate.of(hoje.getYear(), mes, 1);
		amanha = LocalDate.of(hoje.getYear(), mes, hoje2.lengthOfMonth());
		
		System.out.println("Contas do mes " + hoje2.getMonth() + " a pagar:");
		String sql = "SELECT id,descricao, valor, vencimento from controle_de_contas.CDC_" + usuario
				+ " where paga=0 and vencimento" + "  between '" + hoje2 + "'  AND  '" + amanha + "' ";
	

		List<Boleto> boletos= alimentarListaBoletos( sql);
		imprimirLista(boletos);
		return boletos;
	}
	
	public List<Boleto> boletosPagos(String usuario, int mes) throws SQLException {

		hoje2 = LocalDate.of(hoje.getYear(), mes, 1);

		Conexao.getConexao();
		System.out.println("Contas do mes " + hoje2.getMonth() + " a pagar:");
		String consultaMes = "SELECT id, descricao, valor, vencimento from" +
		" controle_de_contas.CDC_" + usuario
				+ " where paga =1";

		List<Boleto> boletos= alimentarListaBoletos(consultaMes);
		imprimirLista(boletos);
		return boletos;

	}
	
public List<Boleto> boletosVencidos(String usuario, int mes) throws SQLException {
		
		 hoje2 = LocalDate.of(hoje.getYear(),  mes, 1);
		 amanha = LocalDate.of(hoje2.getYear(), hoje2.getMonthValue(), hoje2.getDayOfMonth());

		System.out.println("Boletos vencidos ate o dia: "+amanha.getDayOfMonth() +" / "+amanha.getMonthValue());
		System.out.println();
		
		String sql = "SELECT id,descricao, valor, vencimento from controle_de_contas.CDC_" + usuario
				+ " where paga=0 and vencimento < curdate();" ;
		List<Boleto> boletos= alimentarListaBoletos(sql);
		imprimirLista(boletos);
		return boletos;

	}

}
