package nsc.com.utilidades;

import java.util.Calendar;
import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public class Fechas {
	
	
	
	public Date sumarRestarDiasFecha(Date fecha, int dias){

	      Calendar 	calendar = Calendar.getInstance();
	      			calendar.setTime(fecha); 					// Configuramos la fecha que se recibe
	      			calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de d�as a a�adir, o restar en caso de d�as<0
	      
	      return calendar.getTime(); 						  // Devuelve el objeto Date con los nuevos d�as a�adidos
	      
	      
	}
}
