package gateway.servicio;

import java.awt.Color;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTable;

import nsc.gui.cg.MainFrame;
import nsc.gui.cg.button.ActionListenerButton;
import nsc.utils.EnvioRequest;
import nsc.utils.GatewayObject;
import nsc.utils.NSC_AMensaje;
import nsc.utils.NSC_AuditCSV;
import nsc.utils.NSC_MensajeMail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityResult;
import com.amazonaws.services.cognitoidentity.model.GetIdRequest;
import com.amazonaws.services.cognitoidentity.model.GetIdResult;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.google.gson.Gson;

public class GatewayQueue  implements Runnable  {

	NSC_AMensaje			  	serviciosMail;
    ArrayList<String>           lista;
    JTable 	   				  	tabla;
	ActionListenerButton		inicioBoton;
	
    private MainFrame		  	mainFrame;
    NSC_AuditCSV			  	auditoriaMail;
	NSC_MensajeMail			  	mail_nsc;
    
    private JavaMailSenderImpl 	mailSender;
    private SimpleMailMessage   mailMessage;
    private String 				sqsGATE; 
    private String 				idenQueue; 
    private String 				fromConfirma; 
    private String 				serverConfirma; 
    private String 				titulo; 
    private EnvioRequest        envioRequest;
    
	public    				  Thread t1=null;
	String    				  flag;
	
	 AmazonSQSClient sqsClient=null;
	 ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsGATE);
	
	public void setServiciosMail (NSC_AMensaje serviciosMail_p) {
		  this.serviciosMail = serviciosMail_p;
	}
	public NSC_AMensaje getServiciosMail () {
		  return this.serviciosMail;
	}
	public void setLista (ArrayList<String> lista_p) {
		  this.lista = lista_p;
	}
	public ArrayList<String> getLista () {
		  return this.lista;
	}
	
	
	public void setTabla (JTable tabla_p) {
		  this.tabla = tabla_p;
	}
	public JTable getTabla () {
		  return this.tabla;
	}

	public void setMainFrame (MainFrame mainFrame_p) {
		  this.mainFrame = mainFrame_p;
	}
	public MainFrame getMainFrame () {
		 return this.mainFrame;
	}
	
	public void setTitulo (String titulo_p) {
		  this.titulo=titulo_p;
	}
	public String getTitulo () {
		 return this.titulo;
	}
	
	public void setAuditoriaMail (NSC_AuditCSV auditoriaMail_p) {
		  this.auditoriaMail = auditoriaMail_p;
	}
	public NSC_AuditCSV getAuditoriaMail () {
		  return this.auditoriaMail;
	}
	public void setMail_nsc (NSC_MensajeMail mensaje_p) {
	  this.mail_nsc = mensaje_p;
	}
	public NSC_MensajeMail getMail_nsc () {
	  return this.mail_nsc;
	}
	
	public void setIdenQueue (String idenQueue) {
		  this.idenQueue = idenQueue;
	}
	public String getIdenQueue() {
		  return this.idenQueue;
	}
		
	public void setFlag (String flag_p) {
		this.flag = flag_p;
	}
	public String getFlag () {
		  return this.flag;
	}
	public void setMailMessage (SimpleMailMessage simplemail_p) {
		  this.mailMessage= simplemail_p;
	}
	public SimpleMailMessage getMailMessage () {
		  return this.mailMessage;
	}
	public void setMailSender (JavaMailSenderImpl mailsender_p) {
		  this.mailSender= mailsender_p;
	}
	public JavaMailSenderImpl getMailSender () {
		  return this.mailSender;
	}
	public void setInicioBoton (ActionListenerButton inicioboton_p) {
		  this.inicioBoton = inicioboton_p;
	}
	public ActionListenerButton getInicioBoton () {
		  return this.inicioBoton;
	}
	
	public void setFromConfirma (String fromConfirma_p) {
		  this.fromConfirma= fromConfirma_p;
	}
	public String getFromConfirma () {
		  return this.fromConfirma;
	}
	
	public void setServerConfirma (String serverConfirma_p) {
		  this.serverConfirma= serverConfirma_p;
	}
	public String getServerConfirma () {
		  return this.serverConfirma;
	}
	
	public void setSqsGATE (String sqsGATE) {
		  this.sqsGATE = sqsGATE;
	}
	public String getSqsGATE() {
		  return this.sqsGATE;
	}
	
	public void setEnvioRequest (EnvioRequest envioRequest_p) {
		  this.envioRequest = envioRequest_p;
	}
	public EnvioRequest getEnvioRequest() {
		  return this.envioRequest;
	}
	
	public void start(){
		
  		if (t1 == null){
  			 String identityId=null;
  			 AmazonCognitoIdentity cognitoIdentityClient = new AmazonCognitoIdentityClient(new AnonymousAWSCredentials());
			 
  		
			 GetIdRequest idRequest = new GetIdRequest();
			 idenQueue=("us-east-1:1055c550-9cc1-4e96-9016-c615ebbd08a4");
			 idRequest.setAccountId("016473106015");
			 idRequest.setIdentityPoolId(getIdenQueue());
		
			 GetIdResult idResp = cognitoIdentityClient.getId(idRequest);
						
			 identityId = idResp.getIdentityId();
			
			 GetCredentialsForIdentityResult credentials = new GetCredentialsForIdentityResult() ;
				
		     credentials.setIdentityId(identityId);
		    	
			 sqsClient = new AmazonSQSClient((AWSCredentials) credentials.getCredentials());
		
			 sqsClient.setEndpoint(sqsGATE);
             receiveMessageRequest.setMaxNumberOfMessages(10);
		
			 inicioBoton.setForeground(Color.GREEN);
			 t1=new Thread(this,"gatewayQueue");
		 	 System.out.println("Inicio de la aplicación ...."+"\n"+GregorianCalendar.getInstance().getTime());
			 t1.start();
		} else {
			 inicioBoton.setForeground(Color.GREEN);
			 // t.resume();
			 System.out.println("Proceso de la aplicación ...."+"\n"+GregorianCalendar.getInstance().getTime());
		}
	}
	 

	public void run(){
	
	
		String  			leyenda;
	    String envio=null;
        Boolean mark=null;
		String body = null;
		GatewayObject gatewayObj = new GatewayObject();
		String tramite=null;
		String modalid=null;
	 
		  flag = "E-Lectura Cola Gateway : Lectura del Canal";
		
		  try {
			
				
			while(true){
			
				 List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
			
		         for (Message message : messages) {
		                System.out.println("  Message");
		                System.out.println("    MessageId:     " + message.getMessageId());
		                System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
		                System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
		                System.out.println("    Body:          " + message.getBody());
		              
		                body=message.getBody();
		               
		                Gson gson = new Gson();
		                gatewayObj=gson.fromJson(body, GatewayObject.class);
		               		                
		                tramite=gatewayObj.getId();
		               
		                
		                if(gatewayObj.getId() != null && gatewayObj.getId().length() != 0 ) {
		                	modalid=obtenerModalidad(gatewayObj.getId());
		                
		                  if(gatewayObj.getFecha() != null && gatewayObj.getDni() != null &&
		                	 gatewayObj.getAfiliado() != null && gatewayObj.getOsocial() != null &&
		 					 gatewayObj.getPlan() != null && gatewayObj.getApellido() != null &&
		 					 gatewayObj.getFnacimiento() != null && gatewayObj.getSexo() != null &&
		                	 gatewayObj.getTipo() != null &&
		                	 gatewayObj.getFecha().length() != 0 && gatewayObj.getDni().length() != 0 &&
		                	 gatewayObj.getAfiliado().length() != 0 && gatewayObj.getOsocial().length() != 0 &&
		 					 gatewayObj.getPlan().length() != 0 && gatewayObj.getApellido().length() != 0 &&
		 					 gatewayObj.getFnacimiento().length() != 0 && gatewayObj.getSexo().length() != 0 &&
		                	 gatewayObj.getTipo().length() != 0) {
		                	
		                	if(gatewayObj.getLista() != null ) {
   	    			     	     
		                		for (int r=0; r < gatewayObj.getLista().size(); r++){
		                			  
		                		  if( gatewayObj.getLista().get(r).get$key() != null &&
					    		 	  gatewayObj.getLista().get(r).getEstudio() != null &&
					    		 	  gatewayObj.getLista().get(r).getEquipo() != null &&
					    		 	  gatewayObj.getLista().get(r).get$key().length() != 0 &&
					    		 	  gatewayObj.getLista().get(r).getEstudio().length() != 0 &&
					    		 	  gatewayObj.getLista().get(r).getEquipo().length() != 0) {
		                				
		                			
		                			envio =envioRequest.envioEquipoRequest(gatewayObj.getFecha(),gatewayObj.getDni(),
					    		 							   gatewayObj.getAfiliado(),gatewayObj.getOsocial(),
					    		 							   gatewayObj.getPlan(),gatewayObj.getApellido(),
					    		 						       gatewayObj.getFnacimiento(),gatewayObj.getSexo(),
					    		 						       gatewayObj.getTelefono(),gatewayObj.getTipo(),
					    		 						       gatewayObj.getId()+"-"+r,
					    		 						       gatewayObj.getLista().get(r).get$key(),
					    		 						       gatewayObj.getLista().get(r).getEstudio(),
					    		 						       gatewayObj.getLista().get(r).getEquipo(),
					    		 						       modalid);
					    		 						      
		                				  
		                			if (envio != null && envio.length() > 0) { 
					  	
		                				if(envioRequest.enviar(envio)) 
		                					mark=Boolean.TRUE;
		                				else 
		                					mark=Boolean.FALSE;
					    		  
		                			}
		                			
		                			if (mark.equals(Boolean.TRUE)) { 
					   	
							    	 auditoriaMail.audit(gatewayObj.getUsuario()+","+
						                                 gatewayObj.getId()+","+
						                                 GregorianCalendar.getInstance().getTime().toString()+","+
						                                 "MessageId:"+message.getMessageId()+","+
						                                 "Mensaje de Cola Gateway",
							    			 			 "gateway_audit.csv");
							    	 
							    /*	 auditoriaMail.auditEnvio(gatewayObj.getUsuario()+","+
							    			 				  gatewayObj.getId()+","+
							    			 				  GregorianCalendar.getInstance().getTime().toString()+","+
							    			 				  "MessageId:"+message.getMessageId()+","+
							    			 				  "Mensaje de Envio a Equipo:"+gatewayObj.getLista().get(r).getEquipo(),"salida_audit.csv");*/
							    	 
							    	 	if (lista.size()>10){
							    	 		lista.remove(0);
							    	 		tabla.repaint();
							    	 		tabla.revalidate();
							    	 	}
									
							    	 	lista.add("   "+tramite+"\t\t\t"+ " - " +"\t\t\t"+  gatewayObj.getApellido()+ " - " +"\t"+gatewayObj.getLista().get(r).getEstudio());
								
							    	 	tabla.repaint();
							    	 	tabla.revalidate();
								
							
		                			} else {
								      System.out.println("NSC en Operacion : Leer GatewayQueue  : En mark=false"+"\n"+GregorianCalendar.getInstance().getTime());
									
								     /*  SI TUVO PROBLEMA CON LA CONEXION NO SE DETIENE LA APP PERO SE ENVIA MAIL 
										
									 /*
								       **** Error: 2 **** 
								    	Ingresa x Error en el Envio la Cola Gateway 
								     */  
								      leyenda="Leer GatewayQueue : AVISO ERROR Operacion (2) : GENERICO "+"\r\n"+ 
									     "Tràmite ID ..................... : "+gatewayObj.getId()+"\r\n"+
									     "Apellido ....................... : "+gatewayObj.getApellido()+"\r\n"+ 
									     GregorianCalendar.getInstance().getTime()+"\r\n"+
									     "Orden .......................... : "+message.getMessageId()+"\r\n"+
									     "-------------------------------------------------------------"+"\r\n"+ 
										 "Especificacion: Error Leyendo Cola Gateway"+"\r\n"+
								  	     "-------------------------------------------------------------"+"\n";
									
								      this.enviarMail(mailMessage.getFrom(),
										              mailMessage.getFrom(),
								 		              "HAC - IMAGENES:   Error Leer GatewayQueue",
								 		              leyenda,
								 		              mailMessage);
							
									
								      System.out.println("NSC en Operacion : Leer GatewayQueue  : Suspende Ejecucion"+"\r\n"+GregorianCalendar.getInstance().getTime());
								      //	inicioBoton.setForeground(Color.RED);
							
								      // Fue Deprecada t.suspend() ;
								      //	t1.yield();
								      //	this.stop();
								      //	System.exit(0);
		                			} // fin del else flago == true
		                		  }  else  { 
					                	/* Else si le falta alguno de los campos requeridos para cada estudio*/
					                	
					               
					                	leyenda="Leer GatewayQueue : AVISO ERROR Operacion (5) : GENERICO "+"\r\n"+ 
											     "Tràmite ID ..................... : "+gatewayObj.getId()+"\r\n"+
											     "Apellido ....................... : "+gatewayObj.getApellido()+"\r\n"+ 
											     GregorianCalendar.getInstance().getTime()+"\r\n"+
											     "Orden .......................... : "+message.getMessageId()+"\r\n"+
											     "-------------------------------------------------------------"+"\r\n"+ 
												 "Especificacion: Faltan campos requeridos para cada estudio"+"\r\n"+
										  	     "-------------------------------------------------------------"+"\n";
											
										      this.enviarMail(mailMessage.getFrom(),
												              mailMessage.getFrom(),
										 		              "HAC - IMAGENES:   Error Leer GatewayQueue",
										 		              leyenda,
										 		              mailMessage);
							
										mark=Boolean.TRUE;
					              }	
		                		} // Fin del For lista Estudios
		                          
		                		if(gatewayObj.getLista().size() == 0) {
		                			
		                			leyenda="Leer GatewayQueue : AVISO ERROR Operacion (6) : GENERICO "+"\r\n"+ 
										     "Tràmite ID ..................... : "+gatewayObj.getId()+"\r\n"+
										     "Apellido ....................... : "+gatewayObj.getApellido()+"\r\n"+ 
										     GregorianCalendar.getInstance().getTime()+"\r\n"+
										     "Orden .......................... : "+message.getMessageId()+"\r\n"+
										     "-------------------------------------------------------------"+"\r\n"+ 
											 "Especificacion: Lista vacia Sin Estudios"+"\r\n"+
									  	     "-------------------------------------------------------------"+"\n";
										
									      this.enviarMail(mailMessage.getFrom(),
											              mailMessage.getFrom(),
									 		              "HAC - IMAGENES:   Error Leer GatewayQueue",
									 		              leyenda,
									 		              mailMessage);
									      
		                			mark=Boolean.TRUE;
		                		}
		                		
		                	} else {  // ELSE lista == null - 
		     	    	 
		                		leyenda="Leer GatewayQueue - Cola Gateway:  AVISO ERROR Operación (4) : GENERICO  "+"\r\n"+ 
					 			"Tràmite ID ..................... : "+gatewayObj.getId()+"\r\n"+ 
					 	    	"Apellido ....................... : "+gatewayObj.getApellido()+"\r\n"+ 
					 			"Fecha .......................... : "+GregorianCalendar.getInstance().getTime()+"\r\n"+ 
					 			"-------------------------------------------------------------"+"\r\n"+ 
					 			"Especificacion:  "+ "Lista null" +"\r\n"+
					 			"-------------------------------------------------------------"+"\n";

					 	
					 	
		                		mailMessage.setText(leyenda.toString());
					 		
		                		this.enviarMail(mailMessage.getFrom(),
								        		mailMessage.getFrom(),
								        		"HAC - IMAGENES:  Error en Lectura Cola Gateway",
								        		leyenda,
								        		mailMessage);
		                		
		                		mark=Boolean.TRUE;
		                		
		                	/*	sqsClient.deleteMessage(new DeleteMessageRequest()
		                		.withQueueUrl(sqsGATE)
		                		.withReceiptHandle(message.getReceiptHandle()));
		                		mark=null;*/
					
		                	}
		                  }  else  { 
			                	/* Else si le falta alguno de los campos comunes requeridos */
		                	  
		                	  leyenda="Leer GatewayQueue : AVISO ERROR Operacion (7) : GENERICO "+"\r\n"+ 
									     "Tràmite ID ..................... : "+gatewayObj.getId()+"\r\n"+
									     "Apellido ....................... : "+gatewayObj.getApellido()+"\r\n"+ 
									     GregorianCalendar.getInstance().getTime()+"\r\n"+
									     "Orden .......................... : "+message.getMessageId()+"\r\n"+
									     "-------------------------------------------------------------"+"\r\n"+ 
										 "Especificacion: Faltan campos comunes"+"\r\n"+
								  	     "-------------------------------------------------------------"+"\n";
									
								      this.enviarMail(mailMessage.getFrom(),
										              mailMessage.getFrom(),
								 		              "HAC - IMAGENES:   Error Leer GatewayQueue",
								 		              leyenda,
								 		              mailMessage);
			                	mark=Boolean.TRUE;
			                	
			              }	
		                }  else  { 
		                	/* Else si no tiene Id */
		                	
		                	leyenda="Leer GatewayQueue : AVISO ERROR Operacion (8) : GENERICO "+"\r\n"+ 
								     "Tràmite ID ..................... : "+gatewayObj.getId()+"\r\n"+
								     "Apellido ....................... : "+gatewayObj.getApellido()+"\r\n"+ 
								     GregorianCalendar.getInstance().getTime()+"\r\n"+
								     "Orden .......................... : "+message.getMessageId()+"\r\n"+
								     "-------------------------------------------------------------"+"\r\n"+ 
									 "Especificacion: Falta Id del Tramite"+"\r\n"+
							  	     "-------------------------------------------------------------"+"\n";
								
							      this.enviarMail(mailMessage.getFrom(),
									              mailMessage.getFrom(),
							 		              "HAC - IMAGENES:   Error Leer GatewayQueue",
							 		              leyenda,
							 		              mailMessage);
		                	mark=Boolean.TRUE;
		                	
		                }
		                
	                    if (mark.equals(Boolean.TRUE)) {

	                    	sqsClient.deleteMessage(new DeleteMessageRequest()
							.withQueueUrl(sqsGATE)
							.withReceiptHandle(message.getReceiptHandle()));
							mark=null;
	                    }
		         }  /* Fin del For de Mensajes de la Cola Gateway*/
		               
		    } // Fin del while que escucha la Cola Gateway
		               
		  }  catch (Exception e) {
			 
			 /*
			    **** Error: 1 **** 
			    Tipo General 
		     */  
			
			 	leyenda="Leer GatewayQueue - Cola Gateway:  AVISO ERROR Operación (1) : GENERICO  "+"\r\n"+ 
			 			"Tràmite ID ..................... : "+gatewayObj.getId()+"\r\n"+ 
			 	    	"Apellido ....................... : "+gatewayObj.getApellido()+"\r\n"+ 
			 			"Fecha .......................... : "+GregorianCalendar.getInstance().getTime()+"\r\n"+ 
			 			"-------------------------------------------------------------"+"\r\n"+ 
			 			"Especificacion:  "+e.toString() +"\r\n"+
			 			"-------------------------------------------------------------"+"\n";

			 	
			 	
			 	mailMessage.setText(leyenda.toString());
			 		
				this.enviarMail(mailMessage.getFrom(),
						        mailMessage.getFrom(),
						        "HAC - IMAGENES:  Error en Lectura Cola Gateway",
						        leyenda,
						        mailMessage);
			
				
				System.out.println("HAC-IMAGENES en Operación : GatewayQueue  : "+e+"\r\n"+GregorianCalendar.getInstance().getTime());
				inicioBoton.setForeground(Color.RED);
				
				// Fue Deprecada t.suspend();
				t1.yield();
				this.stop();	
				System.exit(0);	
		  } 
			try{
				Thread.sleep(200000);
			} catch (InterruptedException e){
				
			}
		//}      // Fin While  mt = t	
	  }       // Fin Funcion Run

		
	  public void stop(){
		   t1=null;
		   flag="";
		 //  this.start();
	  }

 public void enviarMail(String from,String to,String asunto, String cuerpo,SimpleMailMessage message_p){
		  
		  final String from_p  = from;
		  final String to_p    = to;
		  final String asunto_p=asunto;
		  final String cuerpo_p=cuerpo;
		 
	      message_p.setFrom(from_p);
		  message_p.setTo(to_p);
	      message_p.setSubject(asunto_p);
	      message_p.setText(cuerpo_p);		
	     
		  	try {	  
		  		mailSender.send(message_p);
	 			
		  	} catch (Exception maile){
		  		
				/*
				    **** Error: 3 **** 
				    Ingresa x Error en el Envio Mails 
			     */  
		  		
		  		
				  System.out.println("NSC en Operación : Error(3)  GatewayQueue-enviarMail()- : "+maile+"\r\n"+GregorianCalendar.getInstance().getTime());
				  this.stop();
				  System.exit(0);
			}
 }	
 
public String obtenerModalidad(String idtramite) {
	String mod = null;
	
	switch(Integer.valueOf(idtramite.substring(0,1))) {
	case 1:
		mod="CR";
		break;
	case 2:
		mod="US";
		break;
	case 3:
		mod="MG";
		break;
	case 4:
		mod="NM";
		break;
	case 5:
		mod="CT";
		break;
	default:
		break;
	
	}
	return mod;
}

}
