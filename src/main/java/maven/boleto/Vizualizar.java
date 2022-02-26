package maven.boleto;

import java.sql.SQLException;

public interface Vizualizar {

	void vizualizar(String usuario, int mes) throws SQLException;

}
