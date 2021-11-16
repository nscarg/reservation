package nsc.com.mail.listener;



import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nsc.com.mail.service.*;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.ast.StringLiteral;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

import nsc.com.mail.entity.*;
import nsc.com.mail.operador.*;
import nsc.com.mail.entity.MParametros;

import nsc.com.mail.dto.*;


@Component
@Log4j2
@Builder
public class SqsListener {
	
	private MailService 		envios;
	private MParametrosService 	parametros;
	private SGrid				sendgrid;

    @JmsListeners(value = {
    @JmsListener(destination = "mail_reservation")
    })

       public void receive(String message) {
        log.info("Received {}", message);
       
        
        try {
   		 
        	String 	json = new String(message);
        	 		json = message.substring(1, message.length()-1);
        	 		json = json.translateEscapes();

         	List<QMail_AWS> listaMsjes = new Gson().fromJson(json, new TypeToken<ArrayList<QMail_AWS>>(){}.getType());	
      
	        for(QMail_AWS envio : listaMsjes) {
           
	        	MParametros param = parametros.elegirTemplate(envio.getId_operador(),
	        												  envio.getId_empresa(),
	        												  envio.getId_sucursal(),
	        												  envio.getId_operacion(),
	        												  envio.getLenguaje());

	       	
	        	String 	operador 	= envio.getId_operador().toString();
	        	Boolean resultado 	= false;
     	
	        	switch(operador) {
	        	  case "1":
	        		  resultado = sendgrid.enviarMail(	param.getApi_key(),
	        				  			  				param.getId_template(),
	        				  			  				param.getFrom(),
	        				  			  				param.getSendName(),
	        				  			  				envio.getEmail(),
	        				  			  				param.getSubject(),
	        				  			  				envio.getDatos());
	        		  break;
	        	  
	        	}
	        	
	        	log.info("Resultado {}", resultado+"-*-"+envio.getEmail());

	        	
	        	if (resultado){
	        		OMail mailDB = new OMail(	envio.getId_empresa(),
					  							envio.getId_sucursal(),
					  							new Timestamp(new Date().getTime()),
						        				envio.getId_operacion(),
						        				envio.getEmail(),
						        				envio.getId_operador()); 
  	
	        		envios.createMail(mailDB);
	        		
	        	} else {
	        		 log.error("Error Envios Correo SENDGRID");
	        	}
	
			
        
	        }
	    
        } catch (Exception e) {
            log.error("Error in receive portfolio data", e);
        }

    }

	private Collector toList() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
  
}
