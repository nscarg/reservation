package nsc.utils;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class ManejoFechas {
	// Fields    

	
		
	// Constructors
	/** default constructor */
	public ManejoFechas() {
	}

	
	public static int calcularEdad(String fecha){
        String datetext = fecha;

        try {
            Calendar birth = new GregorianCalendar();
            Calendar today = new GregorianCalendar();

            int age = 0;
            int factor = 0;
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(datetext);

            Date currentDate = new Date(); //current date
            birth.setTime(birthDate);
            today.setTime(currentDate);             

            if(today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)){
                if(today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)){

                    if(today.get(Calendar.DATE) > birth.get(Calendar.DATE)) {
                        factor = -1; //Aun no celebra su cumpleaños

                    }
                }else{
                    factor = -1; //Aun no celebra su cumpleaños
                }

            }
            age =(today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) )+ factor;
            return age;
        } catch (ParseException e) {

            return -1;
        }
	}
	
}
