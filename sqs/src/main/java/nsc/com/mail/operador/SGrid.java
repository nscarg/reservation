package nsc.com.mail.operador;




import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nsc.com.mail.dto.QMail_AWS;

@Component
@NoArgsConstructor @Builder
public class SGrid {
	
	public Boolean enviarMail(String apikey,
							  String template,
							  String from,
							  String sendname,
							  String to,
							  String subject,
							  String data) {
			
					String[] campo = data.split(",");
			
		    		Email fromEmail = new Email();
		    			  fromEmail.setName(sendname);
		    			  fromEmail.setEmail(from);
		    			  
		    		Mail mail = new Mail();
		    			 mail.setFrom(fromEmail);
		    			 mail.setTemplateId(template);
			
		    		Personalization personalization = new Personalization();
		    						personalization.addTo(new Email(to));
		    						personalization.addDynamicTemplateData("subject",subject+campo[0]);
		    						
						    		for (int r=1; r< campo.length; r++) {
						    			personalization.addDynamicTemplateData( String.valueOf(r),campo[r]);
						    		}
		    	

		    		mail.addPersonalization(personalization);
		    		
		    		SendGrid sg 	= new SendGrid(apikey);
		    		Request request = new Request();
			        	      
	        	    try {
	        	    	
	        	    	request.setMethod(Method.POST);
	        	    	request.setEndpoint("mail/send");
	        	    	request.setBody(mail.build());
	        	    	Response response = sg.api(request);
	        	    	return true;
	        	     
	        	    } catch (IOException ex) {
	        	    	ex.printStackTrace();
	        	    	return false;
	        	    }
			        	    
	}

}
