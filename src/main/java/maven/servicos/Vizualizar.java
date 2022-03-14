package maven.servicos;

import java.sql.SQLException;
import java.util.List;

import maven.entidades.Boleto;

public interface Vizualizar {

	List<Boleto> vizualizar(String usuario, int mes) throws SQLException;

}
