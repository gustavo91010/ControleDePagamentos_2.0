package maven;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Rascunho {
	public static void main(String[] args) {

	
	
	LocalDate date= LocalDate.of(2018, 8,21);
	System.out.println(date);
	System.out.println(date.now());
	System.out.println(LocalDate.now().getYear());
	}

}
