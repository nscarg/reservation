package printers.servicio;

import java.awt.Color;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTable;

import nsc.gui.cg.MainFrame;
import nsc.gui.cg.button.ActionListenerButton;
import nsc.utils.Impresion;
import nsc.utils.NSC_AMensaje;
import nsc.utils.NSC_AuditCSV;
import nsc.utils.NSC_MensajeMail;
import nsc.utils.PrintingObject;

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

public class LeerPrinterQueue  implements Runnable  {

	NSC_AMensaje			  	serviciosMail;
    ArrayList<String>           lista;
    JTable 	   				  	tabla;
	ActionListenerButton		inicioBoton;
	
    private MainFrame		  	mainFrame;
    NSC_AuditCSV			  	auditoriaMail;
	NSC_MensajeMail			  	mail_nsc;
    
    private JavaMailSenderImpl 	mailSender;
    private SimpleMailMessage   mailMessage;
    private Impresion           impresion;
    private String 				sqsURL; 
    private String 				idenQueue; 
    private String 				fromConfirma; 
    private String 				serverConfirma; 
	
	public    				  Thread t=null;
	String    				  flag;
	
	 AmazonSQSClient sqsClient=null;
	 ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsURL);
	 
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
	
	public void setImpresion (Impresion impresion_p) {
		  this.impresion = impresion_p;
	}
	public Impresion getImpresion() {
		  return this.impresion;
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
	
	public void setSqsURL (String sqsURL) {
		  this.sqsURL = sqsURL;
	}
	public String getSqsURL() {
		  return this.sqsURL;
	}
	
	
	public void start(){
		
  		if (t == null){
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
System.out.println(sqsURL);	    
			 sqsClient.setEndpoint(sqsURL);
             receiveMessageRequest.setMaxNumberOfMessages(10);
		
			 inicioBoton.setForeground(Color.GREEN);
			t=new Thread(this,"leerPrinterQueue");
			System.out.println("Inicio de la aplicación ...."+"\n"+GregorianCalendar.getInstance().getTime());
			t.start();
		} else {
			inicioBoton.setForeground(Color.GREEN);
			// t.resume();
			System.out.println("Proceso de la aplicación ...."+"\n"+GregorianCalendar.getInstance().getTime());
		}
	}
	 

	public void run(){
	
		boolean				flago   = false;
	
		String  			leyenda;
	    boolean imprT=false;
        boolean imprET=false;
        
  		String body = null;
		PrintingObject printObj = new PrintingObject();
		String sector=null;
		String tramite=null;
		String display = null;
	 
		  flag = "E-Lectura Cola Impresoras : Lectura del Canal";
		  
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
		                printObj=gson.fromJson(body, PrintingObject.class);
		              /*  sector=calcularDesc(printObj.getSectores().trim(),10);
		                tramite=calcularDesc(printObj.getId().trim(),30);*/
		             
		             if(printObj.getSectores() != null && printObj.getSectores().length() != 0 && 
		            		 printObj.getId() != null && printObj.getId().length() != 0 && 
		            		 printObj.getApellido() != null && printObj.getApellido().length() != 0 &&
		            		 printObj.getFecha()!= null && printObj.getFecha().length() != 0 &&
		            		 printObj.getLista()!= null && printObj.getLista().size() != 0 ) { 
		           
		                sector=printObj.getSectores().trim();
		                tramite=printObj.getId().trim();

		                imprET=impresion.imprimeEtiquetas(printObj.getId(),printObj.getApellido(),
		                		                         printObj.getSectores(),
		                		                         printObj.getFecha(),
		                		                         printObj.getLista());  

		                
		                if(impresion.getTaPrinterip().length() != 0) {
							    imprT =impresion.imprimeTalon(	 printObj.getId(),printObj.getApellido(),
										   						 printObj.getSectores(),
										   						 printObj.getFecha(),
										   						 printObj.getLista());
		                } else {
		                	    imprT = true;
		                } 					 
						    if(imprET && imprT )
						    	flago=true;
						    else
						    	flago=false;
						  
						    if (flago == true){ 
						    
						    	 auditoriaMail.audit(printObj.getUsuario()+","+
					                        printObj.getId()+","+
					                        GregorianCalendar.getInstance().getTime().toString()+","+
					                        "MessageId:"+message.getMessageId()+","+
							                "Mensaje de Cola de Impresiòn"+","+
				 				    		"Etiquetas:"+impresion.getEtPrinterip()+"-"+impresion.getEtPrinterp()+","+
							                "Talon:"+impresion.getTaPrinterip()+"-"+impresion.getTaPrinterp(),
				 				    	    "queue_audit.csv");
						    
								if (lista.size()>10){
									lista.remove(0);
									tabla.repaint();
									tabla.revalidate();
								}
								
								display="   "+tramite+"-"+sector+"-"+ printObj.getApellido();
								
								lista.add(display);
								
								tabla.repaint();
								tabla.revalidate();
								
								sqsClient.deleteMessage(new DeleteMessageRequest()
							    .withQueueUrl(sqsURL)
							    .withReceiptHandle(message.getReceiptHandle()));
							
							 } else {  /* flago == false */
								 System.out.println("NSC en Operacion : Leer PrinterQueue  : En flag0=false"+"\n"+GregorianCalendar.getInstance().getTime());
								
							
								/*  SI TUVO PROBLEMA ALGUNA IMPRESION SE DETIENE LA APP Y SE ENVIA MAIL 
								 *  Para evitar llenar el buffer de las impresoras 
								    **** Error: 2 **** 
								    Ingresa x Error en el Envio la Cola de Mensajes */
							      
								 leyenda="Leer PrinterQueue : AVISO ERROR Operacion (3) : GENERICO "+"\r\n"+ 
									     "Tràmite ID ..................... : "+printObj.getId()+"\r\n"+
									     "Apellido ....................... : "+printObj.getApellido()+"\r\n"+ 
									     "Fecha .......................... : "+GregorianCalendar.getInstance().getTime()+"\r\n"+
									     "Orden .......................... : "+message.getMessageId()+"\r\n"+
									     "-------------------------------------------------------------"+"\r\n"+ 
										 "Especificacion:  Error al imprimir "+"\r\n"+
										 "-------------------------------------------------------------";
							     
								 mailMessage.setText(leyenda);	
								 
								 this.enviarMail(mailMessage.getFrom(),
										         mailMessage.getFrom(),
								 		         "HAC - IMAGENES:   Error Leer PrinterQueue",
								 		         leyenda,
								 		         mailMessage);
							
								sqsClient.deleteMessage(new DeleteMessageRequest()
								.withQueueUrl(sqsURL)
								.withReceiptHandle(message.getReceiptHandle()));
									
								System.out.println("NSC en Operacion : Leer PrinterQueue  : Suspende Ejecucion"+"\r\n"+GregorianCalendar.getInstance().getTime());
								inicioBoton.setForeground(Color.RED);
							
								// Fue Deprecada t.suspend() ;
								t.yield();   /*  SE DETIENE LA APP */
								this.stop();
								System.exit(0);
							 } // fin del else flago == true
		             } else {
		            	 /* Else del IF de Validacion de Parametros */ 
		            	 leyenda="LeerPrinterQueue - Cola de impresiòn:  AVISO ERROR Operación (2) : GENERICO  "+"\r\n"+ 
		 			 			"Tràmite ID ..................... : "+printObj.getId()+"\r\n"+ 
		 			 	    	"Apellido ....................... : "+printObj.getApellido()+"\r\n"+ 
		 			 			"Fecha .......................... : "+GregorianCalendar.getInstance().getTime()+"\r\n"+ 
		 			 			"-------------------------------------------------------------"+"\r\n"+ 
		 			 			"Especificacion: Faltan Parametros " +"\r\n"+
		 			 			"-------------------------------------------------------------"+"\r\n";
		 			 	
		 				 this.enviarMail(mailMessage.getFrom(),
		 						         mailMessage.getFrom(),
		 						         "HAC - IMAGENES:  Error en Lectura Cola de Impresiòn",
		 						         leyenda,
		 						         mailMessage);
		 				 
		 				 sqsClient.deleteMessage(new DeleteMessageRequest()
					     .withQueueUrl(sqsURL)
					     .withReceiptHandle(message.getReceiptHandle()));
		             }
		                   
		         }  /* Fin del For de Mensajes de la Cola de Impresiòn*/
			
		    } // Fin del while que escucha la Cola de Impresoras
		      
		 }  catch (Exception e) {
			 
			 /*
			    **** Error: 1 **** 
			    Tipo General 
		     */  
			
			 	leyenda="LeerPrinterQueue - Cola de impresiòn:  AVISO ERROR Operación (1) : GENERICO  "+"\r\n"+ 
			 			"Tràmite ID ..................... : "+printObj.getId()+"\r\n"+ 
			 	    	"Apellido ....................... : "+printObj.getApellido()+"\r\n"+ 
			 			"Fecha .......................... : "+GregorianCalendar.getInstance().getTime()+"\r\n"+ 
			 			"-------------------------------------------------------------"+"\r\n"+ 
			 			"Especificacion: "+ e.toString() +"\r\n"+
			 			"-------------------------------------------------------------"+"\r\n";
			 	
				this.enviarMail(mailMessage.getFrom(),
						        mailMessage.getFrom(),
						        "HAC - IMAGENES:  Error en Lectura Cola de Impresiòn",
						        leyenda,
						        mailMessage);
			
				
				System.out.println("HAC-IMAGENES en Operación : LeerPrinterQueue  : "+e+"\r\n"+GregorianCalendar.getInstance().getTime());
				inicioBoton.setForeground(Color.RED);
				
				// Fue Deprecada t.suspend();
				t.yield();
				this.stop();	
				//System.exit(0);	
		  } 
			try{
				Thread.sleep(200000);
			} catch (InterruptedException e){
				
			}
		//}      // Fin While  mt = t	
	  }       // Fin Funcion Run

		
	  public void stop(){
		  
		   t=null;
		   flag="";
		 //  this.start();
		
	  }

 public void enviarMail(String from,String to,String asunto, String cuerpo, SimpleMailMessage message){
		  
		  final String from_p  = from;
		  final String to_p    = to;
		  final String asunto_p=asunto;
		  final String cuerpo_p=cuerpo;
	      message.setFrom(from_p);
	      message.setTo(to_p);
	      message.setSubject(asunto_p);
	      message.setText(cuerpo_p);
		  	try {	
		  		    mailSender.send(message);
	 			
		  	} catch (Exception maile){
		  		
				/*
				    **** Error: 3 **** 
				    Ingresa x Error en el Envio Mails 
			     */  
		  		
		  		
				  System.out.println("NSC en Operación : Error(3)  LeerPrinterQueue-enviarMail()- : "+maile+"\r\n"+GregorianCalendar.getInstance().getTime());
				  this.stop();
				  System.exit(0);
			}
			
			
	   }
	  
	public String calcularDesc(String desc_p, int length_p) {
		   String desc=desc_p;
	       while(desc.length() < length_p)  {
	    	      desc=desc.concat(".");
	       }
	       
	       return desc;
    }  
	
	
 
	  
  }
