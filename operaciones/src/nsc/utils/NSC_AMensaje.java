package nsc.utils;

import java.util.*;
import java.io.*;
import java.util.ArrayList;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException; 
import javax.mail.Session;
import javax.mail.Store;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;


import nsc.utils.NSC_MensajeMail;



public class NSC_AMensaje {

	  String  			protocol;
	  String  			host;
	  String  			user;
	  String  			password;
	  boolean 			sdebug;
	 
	  NSC_MensajeMail  	 mail_nsc;
	//  NSC_ReadSignedMail lecturaFirma;
	  
	
	  public void setProtocol (String protocol_p) {
			this.protocol = protocol_p;
	  }
	  public String getProtocol () {
		  return this.protocol;
	  }
	  public void setHost (String host_p) {
			this.host = host_p;
	  }
	  public String getHost () {
		  return this.host;
	  }
	  public void setUser (String user_p) {
			this.user = user_p;
	  }
	  public String getUser () {
		  return this.user;
	  }		  
	  public void setPassword (String password_p) {
			this.password = password_p;
	  }
	  public String getPaswword () {
		  return this.password;
	  }	  
	  public void setSdebug (boolean sdebug_p) {
			this.sdebug = sdebug_p;
	  }
	  public boolean getSdebug () {
		  return this.sdebug;
	  }	  
	  public void setMail_nsc (NSC_MensajeMail mensaje_p) {
			  this.mail_nsc = mensaje_p;
	  }
	  public NSC_MensajeMail getMail_nsc () {
			  return this.mail_nsc;
	  }
	  
	
	  
	public NSC_MensajeMail leer(Message mensaje){
	
		
		NSC_MensajeMail mailnsc 		= new NSC_MensajeMail();
		String  		email			="";
		String  		fecha			="";
		String			asuntoMensaje	="";
		String  		cuerpoMensaje	="";

		InputStream 	archivo			=null;
		String 			nombrePart		="";
		Boolean			estado			=null;
		
		MimeBodyPart mbp = null;
		fecha = Calendar.getInstance().getTime().toString();
		try {
	
			
			email        = ((InternetAddress)(mensaje.getFrom()[0])).getAddress().trim();
			fecha        = fecha.trim();
			asuntoMensaje= (mensaje.getSubject().trim());
			cuerpoMensaje= "";
			archivo      = null;
			Object o 	 = mensaje.getContent();

//	    Si el mensaje es de texto sin adjuntos, ya sea texto HTML o	texto plano, el contenido será de tipo String
		if (o instanceof String) {
			if (mensaje.getContentType().indexOf("text/plain") != -1) {
				cuerpoMensaje=(String)o;
				cuerpoMensaje=cuerpoMensaje.trim();
			
				System.out.println("02-T:TP:de "+"Fecha:"+fecha.trim()+"E-Mail:"+mensaje.getFrom()[0].toString().trim()+"Asunto:"+mensaje.getSubject().trim());	
			} 
//	      Si entra por aquí es que se trata de un mensaje con adjunto/s o	HTML con imágenes embebidas en el mensaje
		} else if (o instanceof Multipart) {
//	        Se sabe que el contenido del mensaje es de tipo Multipart, así que se le hace un casting para obtener un objeto de este tipo
			Multipart mp = (Multipart)o;
//	        Recorrer todos los Part que componen el mensaje
			int numPart = mp.getCount();

			for (int i=0; i < numPart; i++) {

//	            Con cada parte del mensaje hay que ver la disposición que	 tiene
				Part part = mp.getBodyPart(i);

				String disposition = part.getDisposition();
//	            Si la disposición es null probablemente se trate del contenido en sí del mensaje
				if (disposition == null) {
					
					if (part.isMimeType("multipart/related") && 
				        cuerpoMensaje.length() == 0 ) {
						
						cuerpoMensaje = (String)armarCuerpoMultiparte(part).get("cpm");
					
						
					} else 	if (part.isMimeType("multipart/alternative") && 
						        cuerpoMensaje.length() == 0 ){
						
								cuerpoMensaje = armarCuerpoSimple(part);
						
					} else  if (part.isMimeType("text/plain")&& 
				             	cuerpoMensaje.length() == 0 ){
								
								cuerpoMensaje = (String)part.getContent();
								cuerpoMensaje = cuerpoMensaje.trim();
							
					} else if (part.isMimeType("multipart/mixed")) {
						
						cuerpoMensaje = (String)armarCuerpoMultiparte(part).get("cpm");
					    archivo		  =  (InputStream)armarCuerpoMultiparte(part).get("arc");
					    
												
					} else {
//	                    Si entra por aquí se trataría de la parte del texto de	un correo HTML con imágenes embebidas
						if (part.getContentType().indexOf("text/plain") != -1 && 
			                 cuerpoMensaje.length() == 0 ) {
							
							mbp = (MimeBodyPart)part;
							cuerpoMensaje =(String)part.getContent();
						
						} else  if (part.isMimeType("text/html") && 
				                  cuerpoMensaje.length() == 0 ){
					
							// Agregado para Thunder
							cuerpoMensaje=(String)part.getContent();
							cuerpoMensaje=cuerpoMensaje.trim();
												
						} 
					}
//	              Si entra por aquí es que se trata de un adjunto o de una imagen embebida en el mensaje
				} else if ((disposition != null) &&
						   (disposition.equalsIgnoreCase(Part.ATTACHMENT) ||
						    disposition.equalsIgnoreCase(Part.INLINE))) {
			
								 nombrePart = part.getFileName();

                              if (nombrePart != null){
								  nombrePart=nombrePart.toLowerCase();
							  
								if (nombrePart.startsWith("datos")) {
		
									if (part.getSize()<82000){
										mbp 	= (MimeBodyPart)part;
										archivo	=  mbp.getInputStream();
									}
																			
//									ZONA 4 Ver 1 Campo System System.out
									System.out.println("04-T:C/ARCHIVO:de "+"Fecha:"+fecha.trim()+" E-Mail:"+mensaje.getFrom()[0].toString().trim()+" Asunto:"+mensaje.getSubject().trim());			
																
								}
                              }	else {

                            	  if (cuerpoMensaje.length() == 0 ){
                            			cuerpoMensaje 	= (String)part.getContent();
                            			cuerpoMensaje 	= cuerpoMensaje.trim();
                            	  }
                            	  
                              }
				}
			}  //  Fin for
		}   // else multipart
		
		mailnsc.setId(email);
		mailnsc.setFecha(fecha);
		mailnsc.setAsunto(asuntoMensaje);
		mailnsc.setCuerpo(cuerpoMensaje);
		mailnsc.setNarchivo(archivo);
		mailnsc.setEstado(estado);
		return mailnsc;
		
		} catch (MessagingException me) {
			System.err.println(me.toString());
			return null;
		} catch (IOException ioe) {
			System.err.println(ioe.toString());
			return null;
		} catch (Exception ioe) {
			System.err.println(ioe.toString());
			return null;
		}
	} // Fin Funcion 
	
	
	
	
	
	public  List<NSC_MensajeMail> conectarPOPServer(){
		
		  List<NSC_MensajeMail>  lmensajes = new ArrayList<NSC_MensajeMail>();
		
		  Properties lProperties =  System.getProperties();
						  
		  Session lSession = Session.getDefaultInstance(lProperties, null);
	   	  lSession.setDebug(getSdebug ());
		      
		  Store  store 		 = null;
		  Folder folder 	 = null;
	//	  Folder cpiofolder  = null;
		  
		  int lTotalMessages = 0;
	//	  boolean rescopia    = true;
		  
		  Message[] messages;
	  
		  try {
		      store  = lSession.getStore(protocol);
			  store.connect(host, user, password);
			  folder = store.getFolder("INBOX");
			  folder.open(Folder.READ_WRITE);
			
			  lTotalMessages =  folder.getMessageCount();
			
			  if (lTotalMessages == 0) {
				  folder.close(false);
				  folder = null;
				  store.close();
				  return null;
			  } else {
				  
				  messages =  folder.getMessages();	
				  for (int i = 0; i < messages.length; i++) {
					 messages[i].setFlag(Flags.Flag.DELETED,true);
					 
					 mail_nsc = leer(messages[i]);	

					 if (mail_nsc.getId() != null) 
						 lmensajes.add(mail_nsc);
			  	  }
			  }  

			  folder.close(true);
			  store.close();
		
			  
			  if (lmensajes.size()>0)
			  return lmensajes;
			  else
			  return null;
			  
		  } catch (MessagingException me) {
			  System.err.println(me.toString());
			  return null;	
		  } 

	
	}
	

public Map<Object,Object> armarCuerpoMultiparte(Part part){


	Map<Object,Object> 	valores = new HashMap<Object,Object>();

	String 		cuerpoMensaje  =  "";
	InputStream archivo	=null;
	String 		nombrePart	="";

	MimeBodyPart mbp		= null;

   try {	
	
	Multipart      mp0 = (Multipart)part.getContent();
	int       numPart0 = mp0.getCount();

	for (int i1=0; i1 < numPart0; i1++) {		
		MimeBodyPart part0 = (MimeBodyPart)mp0.getBodyPart(i1);
		String disposition0 = part0.getDisposition();
		if (disposition0 == null) {
			if (part0.isMimeType("multipart/alternative") && 
			    cuerpoMensaje.length() == 0 ){
										
				Multipart mp4 = (Multipart) part0.getContent();
				Part part4 = mp4.getBodyPart(0);
				cuerpoMensaje = (String)part4.getContent();
				cuerpoMensaje = cuerpoMensaje.trim();

			} else if (part0.isMimeType("multipart/related") && 
			    cuerpoMensaje.length() == 0 ){
				
				cuerpoMensaje=(String)armarCuerpoMultiparte(part0).get("cpm");
				cuerpoMensaje = cuerpoMensaje.trim();
				
			} else  if (part0.isMimeType("text/plain") && 
	                  cuerpoMensaje.length() == 0 ){

				cuerpoMensaje=(String)part0.getContent();
				cuerpoMensaje=cuerpoMensaje.trim();
									
						
			} else  if (part0.isMimeType("text/html") && 
		                  cuerpoMensaje.length() == 0 ){
			
					// Agregado para Thunder
					cuerpoMensaje=(String)part0.getContent();
					cuerpoMensaje=cuerpoMensaje.trim();
										
					}
			
		} else if((disposition0 != null) &&
	  		   (disposition0.equalsIgnoreCase(Part.ATTACHMENT)||
			    disposition0.equalsIgnoreCase(Part.INLINE))) {  											
				nombrePart = part0.getFileName();

				if (nombrePart != null){
				 nombrePart=nombrePart.toLowerCase();
					
				 if (nombrePart.startsWith("datos")) {
				
					if (part0.getSize()<82000){
						mbp 		= (MimeBodyPart)part0;
						archivo	=  mbp.getInputStream();
					}
				 }
				}	
		}

	} // Fin For Anidado
	 
	valores.put("cpm",cuerpoMensaje);
	 valores.put("arc",archivo);
	 

	return valores;
	
   } catch (MessagingException me) {
		System.err.println(me.toString());
		return null;
	} catch (IOException ioe) {
		System.err.println(ioe.toString());
		return null;
	} 

}


public String armarCuerpoSimple(Part part){

	try {
	
	String 	cuerpoMensaje  =  "";
	Multipart mp2 	= (Multipart) part.getContent();
	Part part2 		= mp2.getBodyPart(0);
	cuerpoMensaje 	= (String)part2.getContent();
	cuerpoMensaje 	= cuerpoMensaje.trim();

   return cuerpoMensaje;
   
   } catch (MessagingException me) {
		System.err.println(me.toString());
		return null;
   } catch (IOException ioe) {
		System.err.println(ioe.toString());
		return null;
   }  
   

}



	
}
