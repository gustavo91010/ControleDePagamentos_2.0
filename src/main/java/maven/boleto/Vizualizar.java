package maven.boleto;

import java.sql.SQLException;
import java.util.List;

public interface Vizualizar {

	List<Boleto> vizualizar(String usuario, int mes) throws SQLException;

}
