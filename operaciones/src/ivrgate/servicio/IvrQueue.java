package ivrgate.servicio;

import java.awt.Color;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTable;

import nsc.gui.cg.MainFrame;
import nsc.gui.cg.button.ActionListenerButton;
import nsc.utils.EstudioEstado;
import nsc.utils.EstudioEstadoGSon;
import nsc.utils.IVRObject;
import nsc.utils.IVRUpdate;
import nsc.utils.MStateGSon;
import nsc.utils.NSC_AMensaje;
import nsc.utils.NSC_AuditCSV;
import nsc.utils.NSC_MensajeMail;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
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

public class IvrQueue  implements Runnable  {

	NSC_AMensaje			  	serviciosMail;
    ArrayList<String>           lista;
    JTable 	   				  	tabla;
	ActionListenerButton		inicioBoton;
	
    private MainFrame		  	mainFrame;
    NSC_AuditCSV			  	auditoriaMail;
	NSC_MensajeMail			  	mail_nsc;
    
    private JavaMailSenderImpl 	mailSender;
    private SimpleMailMessage   mailMessage;
    private String 				sqsIVR; 
    private String 				idenQueue; 
    private String 				fromConfirma; 
    private String 				serverConfirma; 
     
	public    				  Thread t1=null;
	String    				  flag;
	String leyenda;
	Message mensaje;
	
	 AmazonSQSClient sqsClient=null;
	 ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsIVR);
	
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
	
	public void setSqsIVR (String sqsIVR) {
		  this.sqsIVR = sqsIVR;
	}
	public String getSqsIVR() {
		  return this.sqsIVR;
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
		
			 sqsClient.setEndpoint(sqsIVR);
             receiveMessageRequest.setMaxNumberOfMessages(10);
		
			 inicioBoton.setForeground(Color.GREEN);
			 t1=new Thread(this,"ivrQueue");
		 	 System.out.println("Inicio de la aplicación ...."+"\n"+GregorianCalendar.getInstance().getTime());
			 t1.start();
		} else {
			 inicioBoton.setForeground(Color.GREEN);
			 // t.resume();
			 System.out.println("Proceso de la aplicación ...."+"\n"+GregorianCalendar.getInstance().getTime());
		}
	}
	 

	public void run(){
	
	
			   
        Boolean markA=null;
        Boolean markU=null;
		String body = null;
		IVRObject ivrObj = new IVRObject();
		IVRUpdate ivrUpdate = new IVRUpdate();
	
	    List<MStateGSon> listaMSt = new ArrayList<MStateGSon>();
	    List<EstudioEstadoGSon> listaEstEST = new ArrayList<EstudioEstadoGSon>();
	    String idtram= null;
	    String apellido= null;
	    
		  flag = "E-Lectura Cola IVR : Lectura del Canal";
		
		  try {
			
				
			while(true){
			
				 List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
				 String postURLA ;
				 String postURLU ;
	             postURLA="http://23.21.129.196:8080/mdelivery/grabarMState.do";
	             postURLU="http://23.21.129.196:8080/mdelivery/actualizarEstadoIVR.do";
	             HttpPost postA = new HttpPost(postURLA);
	             HttpPost postU = new HttpPost(postURLU);
	             HttpClient httpClient; 
	             
		         for (Message message : messages) {
		                System.out.println("  Message");
		                System.out.println("    MessageId:     " + message.getMessageId());
		                System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
		                System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
		                System.out.println("    Body:          " + message.getBody());
		             
		                body=message.getBody();
		                mensaje = message;
		                
		                Gson gsonA = new Gson();
		                String jsonA = null;
		                Gson gsonU = new Gson();
		                String jsonU = null;
		                char oper =  body.charAt(14);
		                
		                List<NameValuePair> params_p = new ArrayList<NameValuePair>();
		           /*     HttpParams httpParameters = new BasicHttpParams();
	                	  int timeoutConnection = 30000;
	                	  HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	                	  int timeoutSocket = 30000;
	                	  HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	                	  DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);*/
		                
	                	  httpClient = HttpClients.createDefault();
	                
	                	  if(oper == 'A') {  /* Es una Alta a MState */
	                	  
	                		  ivrObj=gsonA.fromJson(body, IVRObject.class);
	                		  listaMSt = cargarListaMst(ivrObj);
	                			
		                	  jsonA = gsonA.toJson(listaMSt);
		                	 
		                	  idtram=ivrObj.getIdtramite();
		                	  apellido= ivrObj.getApellido();
		                	  
		                	  params_p.add(new BasicNameValuePair("param", jsonA));
		                /*	  UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params_p, HTTP.ISO_8859_1);*/
		                	  UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params_p, "ISO-8859-1");
		                	  RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).setSocketTimeout(30000).build(); 
		                	  
		                	  postA.setConfig(requestConfig);
		                	  postA.setEntity(ent);
		                	  
		                	  HttpResponse responsePOST = httpClient.execute(postA);
		                	  HttpEntity resEntity = responsePOST.getEntity();
		                
		             
		                	  if(resEntity != null)
		                		  markA=Boolean.TRUE;
		                	  else 
		                		  markA=Boolean.FALSE;
		                	  
		                	  finaliza(markA,oper,ivrObj.getIdtramite(),message.getMessageId(), ivrObj.getApellido());
		                	
		                	  if (markA.equals(Boolean.TRUE)) {

									sqsClient.deleteMessage(new DeleteMessageRequest()
								    .withQueueUrl(sqsIVR)
								    .withReceiptHandle(message.getReceiptHandle()));
									markA=null;
	                          }
		                	  
	                	  } else if(oper == 'U') {   /* Es una actualizacion del Estado */
	                		  
	                		  System.out.println("Update0");
	                		  ivrUpdate=gsonU.fromJson(body, IVRUpdate.class);
	                		  System.out.println("Update1");
	                		  listaEstEST = cargarListaEstEST(ivrUpdate);
	                		  System.out.println("Update2"+listaEstEST);
	                		  System.out.println("Update3"+listaEstEST.size());
		                	  jsonU = gsonU.toJson(listaEstEST);
		                	//  idtram=ivrUpdate.getIdtramite();
		                	  System.out.println("Update4"+jsonU);
		                	  apellido="";
		                	  
		                	  	                
		                	  params_p.add(new BasicNameValuePair("param1", jsonU));
		                	  System.out.println("Update5");   
				                	  UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params_p, "ISO-8859-1");
				                	  RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).setSocketTimeout(30000).build(); 
				                	  System.out.println("Update6"+ent);    
				                	  postU.setConfig(requestConfig);
				                	  postU.setEntity(ent);
				                	  System.out.println("Update7");  	  
				                	  HttpResponse responsePOST = httpClient.execute(postU);
				                	  HttpEntity resEntity = responsePOST.getEntity();
				                	  System.out.println("Update8"+resEntity);  
		             	  
		                	  if(resEntity != null)
		                		  markU=Boolean.TRUE;
		                	  else 
		                		  markU=Boolean.FALSE;
		                	  
		                	//  finaliza(markU,oper,ivrObj.getIdtramite(),message.getMessageId(),apellido);
		                	  finaliza(markU,oper,"Lista con "+ ivrUpdate.getLista().size()+"  estudios",message.getMessageId(),apellido);
		                	  System.out.println("Update9"+markU);  	
		                	  if (markU.equals(Boolean.TRUE)) {
		                		  System.out.println("Update10-TRUE"); 
									sqsClient.deleteMessage(new DeleteMessageRequest()
								    .withQueueUrl(sqsIVR)
								    .withReceiptHandle(message.getReceiptHandle()));
									markU=null;
									 System.out.println("Update11"); 
	                          } 
		                	  System.out.println("Update12"); 
		                  }  /* Fin del Else por Operacion Update  Actualizar Estado */
		                   
		                  }  /* Fin del For de Mensajes de la Cola Gateway*/
			
		      } // Fin del while que escucha la Cola IVR
	    }  catch (SocketTimeoutException ste) {    
		      
		       
		}  catch (Exception e) {
			 
			 /*
			    **** Error: 1 **** 
			    Tipo General 
		     */  
		
			 	leyenda="Leer IvrQueue - Cola IVR:  AVISO ERROR Operación (1) : GENERICO  "+"\r\n"+ 
			 			"Tràmite ID ..................... : "+idtram+"\r\n"+ 
			 	    	"Apellido ....................... : "+apellido+"\r\n"+ 
			 			"Fecha .......................... : "+GregorianCalendar.getInstance().getTime()+"\r\n"+ 
			 			"-------------------------------------------------------------"+"\r\n"+ 
			 			"Especificacion:  "+e.toString() +"\r\n"+
			 			"-------------------------------------------------------------"+"\n";

			 	
			 	
			 	mailMessage.setText(leyenda.toString());
			 		
				this.enviarMail(mailMessage.getFrom(),
						        mailMessage.getFrom(),
						        "HAC - IMAGENES:  Error en Lectura Cola IVR",
						        leyenda,
						        mailMessage);
			
				
				System.out.println("HAC-IMAGENES en Operación : IvrQueue  : "+e+"\r\n"+GregorianCalendar.getInstance().getTime());
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
		   this.start();
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
		  		
		  		
				  System.out.println("NSC en Operación : Error(3)  IvrQueue-enviarMail()- : "+maile+"\r\n"+GregorianCalendar.getInstance().getTime());
				  this.stop();
				  System.exit(0);
			}
 }	
 
 
 public List<MStateGSon> cargarListaMst(IVRObject ivrObj_p)  {

	 List<MStateGSon> listaMSt_p = new ArrayList<MStateGSon>();
	 MStateGSon mst;
	 EstudioEstado estEst = null;
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  
	 for (int r=0; r < ivrObj_p.getLista().size(); r++){
         
           estEst = ivrObj_p.getLista().get(r);
   	   try {
		   mst = new MStateGSon(0, Integer.parseInt(ivrObj_p.getCliente()),Integer.parseInt(ivrObj_p.getSucursal()),
				                Integer.parseInt(ivrObj_p.getSector()),"9999",ivrObj_p.getIdtramite().substring(1, ivrObj_p.getIdtramite().length()),
				                Integer.parseInt(estEst.getIdestudio()),ivrObj_p.getAfiliado(),ivrObj_p.getApellido(),
				                ivrObj_p.getTelefono(),Integer.parseInt(ivrObj_p.getEdad()),ivrObj_p.getSexo(),
				                ivrObj_p.getTipo(),9999,9999,null,9999,df.parse(ivrObj_p.getFingreso()),0,
				                null,0,null,estEst.getObservaciones(),estEst.getEstado());		
				                		
		  
		   listaMSt_p.add(mst);	
		  
		   
	   } catch(Exception ex) {
		  
	   }
	 }
   	   
   	       return listaMSt_p;  

    
  }

 public List<EstudioEstadoGSon> cargarListaEstEST(IVRUpdate ivrUpdate_p)  {

	 List<EstudioEstadoGSon> listaEstEST_p = new ArrayList<EstudioEstadoGSon>();
	 EstudioEstado estEst = null;
	 EstudioEstadoGSon estEstGS = null;
	 System.out.println("Update15"+ivrUpdate_p); 
	 System.out.println("Update15"+ivrUpdate_p.getLista());  
	 System.out.println("Update15"+ivrUpdate_p.getLista().size());  	
	 for (int r=0; r < ivrUpdate_p.getLista().size(); r++){
         
           estEst = ivrUpdate_p.getLista().get(r);
   	   try {
   		 		
		    estEstGS = new EstudioEstadoGSon(estEst.getObservaciones(),estEst.getEstado(),estEst.getIdestudio()); 
				                		
		  
		   listaEstEST_p.add(estEstGS);	
		  
		   
	   } catch(Exception ex) {
		  
	   }
	 }
   	   
   	       return listaEstEST_p;  
 
  }
 
 public void finaliza(Boolean mark, char operacion,String idtramite,String messId, String apellido) {
	 String leyenda_p;
	 
	 if (mark.equals(Boolean.TRUE)) { 
	   	
	    auditoriaMail.audit("9999"+","+
	                        operacion+","+
                            idtramite+","+
                            GregorianCalendar.getInstance().getTime().toString()+","+
                            "MessageId:"+messId+","+
                            "Mensaje de Cola IVR",
			 			    "ivr_audit.csv");
	 

		if (lista.size()>10){
			lista.remove(0);
			tabla.repaint();
			tabla.revalidate();
		}
	
		lista.add("   "+idtramite+"\t\t\t"+ " - " +"\t\t\t"+  apellido + " - " +"\t"+operacion);

	tabla.repaint();
	tabla.revalidate();


	 } else  {
		 System.out.println("NSC en Operacion : Leer IvrQueue  : En mark=false"+"\n"+GregorianCalendar.getInstance().getTime());
	

		/*
		    **** Error: 2 **** 
		    Ingresa x Error en el Envio la Cola IVR 
		*/
		 
		 leyenda_p="Leer IvrQueue : AVISO ERROR Operacion (2) : GENERICO "+"\r\n"+ 
			     "Tràmite ID ..................... : "+idtramite+"\r\n"+
			     "Apellido ....................... : "+apellido+"\r\n"+ 
			     GregorianCalendar.getInstance().getTime()+"\r\n"+
			     "Orden .......................... : "+messId+"\r\n"+
			     "-------------------------------------------------------------"+"\r\n"+ 
				 "Especificacion: Error Leyendo Cola IVR"+"\r\n"+
		  	     "-------------------------------------------------------------"+"\n";
			
		 this.enviarMail(mailMessage.getFrom(),
				         mailMessage.getFrom(),
		 		         "HAC - IMAGENES:   Error Leer IvrQueue",
		 		         leyenda_p,
		 		         mailMessage);

	
	System.out.println("NSC en Operacion : Leer IvrQueue  : Suspende Ejecucion"+"\r\n"+GregorianCalendar.getInstance().getTime());
	inicioBoton.setForeground(Color.RED);
	
	// Fue Deprecada t.suspend() ;
	t1.yield();
	this.stop();
	System.exit(0);
	} // fin del else flago == true
 }
}