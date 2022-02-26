package maven.infra;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import maven.boleto.Boleto;

public class AlimentarListaBoletos {
	
		
		public static List<Boleto> alimentarListaBoletoos(String usuario, String sql, int mes) throws SQLException {
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
				boletos.add(new Boleto(descricao, valor, data, indice));
			}
			stmt.close();
			resultado.close();
			return boletos;
		}

}
