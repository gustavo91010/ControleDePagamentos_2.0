package maven.infra;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import maven.entidades.Boleto;

public class AlimentarListaBoletos {

	public static List<Boleto> alimentarListaBoletos(String sql) throws SQLException {
		Date data;
//			Date input = new Date(1l);

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

}
