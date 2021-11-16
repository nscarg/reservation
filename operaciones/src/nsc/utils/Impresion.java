package nsc.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Impresion {
	
	Date 							fechaemision;
	String 							fecha_p;
	private static String 			TF_NAME ="AQTalon.nsc";
	private String                  origen;
	private String etPrinterip;
	private int etPrinterp;
	private String taPrinterip;
	private int taPrinterp;
	private String compres;
	
	public void setOrigen (String origen_p) {
		origen = origen_p;
	}
	public String getOrigen () {
		  return origen;
	}
	
	public void setEtPrinterip (String etPrinterip_p) {
		etPrinterip = etPrinterip_p;
	}
	public String getEtPrinterip () {
		  return etPrinterip;
	}
	public void setEtPrinterp (int etPrinterp_p) {
		etPrinterp = etPrinterp_p;
	}
	public int getEtPrinterp () {
		  return etPrinterp;
	}
	public void setTaPrinterip (String taPrinterip_p) {
		taPrinterip = taPrinterip_p;
	}
	public String getTaPrinterip () {
		  return taPrinterip;
	}
	public void setTaPrinterp (int taPrinterp_p) {
		taPrinterp = taPrinterp_p;
	}
	public int getTaPrinterp () {
		  return taPrinterp;
	}

	public String getCompres() {
		  return compres;
	}
	public void setCompres(String compres_p) {
		compres = compres_p;
	}
	
	public  boolean imprimeTalon(   String preid_p,String apellido_p,
							        String sectores_p,
							        String fecha_p,
							        List<EstudioItem>	estudios){

	/*	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		fechaemision = Calendar.getInstance().getTime();
		fecha_p  = dateFormat.format(fechaemision);
	*/  
	   
		String enca="";
	/*	String preid = preid_p;
		String apellido = apellido_p;
		String sector = sectores_p;*/
	
			
		List<EstudioItem> estudios_p= estudios;
	
		
		enca= lPlantillaTickets(	fecha_p,
							 		preid_p,
				                    sectores_p,
				                   	apellido_p,
				                  	estudios_p);
		if (enca!=null){
			
				
			try {
				
				Socket S = new Socket(taPrinterip,taPrinterp);
				
				S.setSoTimeout(18000);	
			
				DataOutputStream out = new DataOutputStream(S.getOutputStream());
				out.writeBytes(enca);
				out.flush();
				out.close();
				return true;
			} catch (SocketTimeoutException e) {
				System.out.println("STOE Talon - "+e);
				return false;
			} catch (InterruptedIOException e) {
				System.out.println("SE  Talon - "+e);
				return false;
			} catch (SocketException e) {
				System.out.println("SE  Talon - "+e);
				return false;
			}  catch (IOException e) {
				System.out.println("IOE  Talon - "+e);
				return false;
			} catch (IllegalArgumentException e) {
				System.out.println("IAE Talon - "+e);
				return false;
			} catch (Exception e) {
				System.out.println("E Talon - "+e);
				return false;
			}
		} else {   /* enca == null - Si falta algun parametro en la lista */  
			System.out.println("EEL  Talon");
			return false;
		}	
			
	}

	
	public boolean imprimeEtiquetas(String preid_p,String apellido_p,
									String sectores_p,
									String fecha_p,
									List<EstudioItem>	estudios) {
	
	/*	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		fechaemision = Calendar.getInstance().getTime();
		fecha_p  = dateFormat.format(fechaemision);*/

	  
		return lPlantillaEtiquetas(etPrinterip,etPrinterp,
							 	   fecha_p,
							 	   preid_p,
							 	   sectores_p,
							 	   apellido_p,
							 	   estudios);
		 	
	}
	

	
	
	
	
	
	public String lPlantillaTickets(String fecha,
									String idtramite,
			 						String sector,
			 						String apellido,
			 						List<EstudioItem> estudios){
	
		FileInputStream 	fileIS;
	
		
		String 				salida="";
		
		try{
	
			File f = new File(getOrigen()+TF_NAME);
			fileIS = new FileInputStream(f);
			BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));
			String readString = new String();
			
				
			String plantillae="";
			String plantillap="";
			
			
			while((readString = buf.readLine())!= null){
				plantillae=readString;
			
				plantillae=plantillae.replaceAll("TRAMITE", idtramite)+"\n";
				plantillae=plantillae.replaceAll("APELLIDO", apellido);
				plantillae=plantillae.replaceAll("FECHA", fecha);
				plantillae=plantillae.replaceAll("SECTOR",sector );
				
				salida=salida+plantillae;
			}	
			fileIS.close();	
			
			int m = 0;
			int endi=0;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String fechae;
			Date fechaem;
			
			try {
				
				for (int r=0; r < estudios.size(); r++){
					// m=r+1;
					m=r;   /* porque ahora debe coincidir con el campo Orden */
					
					if(estudios.get(r).getEstudio() != null && estudios.get(r).getEstudio().length() != 0 ) {
						
						endi = estudios.get(r).getEstudio().length();
						
						if(endi > 45)
							endi=45;
					} else {
						buf.close();
						fileIS.close();	
						
						return null;
					}
									
					fechaem=estudios.get(r).getFeentrega();
					fechae  = dateFormat.format(fechaem);
					
					plantillap=plantillap+m+" - "+estudios.get(r).getEstudio().substring(0, endi)+"\n\t"+fechae+"\n";
				}
				
			} catch(Exception e) {
				System.out.println("Error - " + e);
				return null;
			}
			
			int mi = 29;
	        int pa = 1;
	        String si = (char)mi+"V"+(char)pa;
	        
	      
			salida=salida+plantillap+"\n\n\n\n\n"+si;
		
		} catch (FileNotFoundException e) {
			System.out.println("FNF - "+e);
			return null;
		} catch (IOException e){
			System.out.println("IOE - "+e);
			return null;
		}
		
		return salida;
	}
	
	public boolean lPlantillaEtiquetas( String ip,
			                            int puerto,
			                            String fecha,
										String idtramite,
										String sector,
										String apellido,
										List<EstudioItem> estudios){

		String  salida="";
		String 	peSobre= "";
		
/*		String 	peSobre= "^XA ^CF0,25^FO15,25^FD APELLIDO ^FS ^FO20,70^BY1.45^B3N,N,80,Y,N^A0,29,22^FD TRAMITE ^FS ^FO225,100^A0,29,22^FD TRAMITE ^XZ"; */ // Para impresora Zebra Negra en NSC xq no se leia el CB 
	                   //  "^XA ^CF0,30^FO20,30^FD APELLIDO ^FS ^FO20,80^BY3^B3N,N,100,Y,N^FD TRAMITE ^FS ^FO20,220^FD FECHA ^FS ^FO500,55^GB1,50,1,B,0^FS ^FO475,75^GB50,1,1,B,0^FS ^FT500,75^A0R,25,25 ^XZ";
		if(compres.equals("^BY1.45")) 
			  peSobre= "^XA ^CF0,25^FO15,25^FD APELLIDO ^FS ^FO20,70"+compres+"^B3N,N,80,Y,N^A0,29,22^FD TRAMITE ^FS ^FO225,100^A0,29,22^FD TRAMITE ^FS ^FO20,220^FD FECHA ^XZ";  // Para impresora Zebra Negra en NSC xq no se leia el CB 
		else
		      peSobre= "^XA ^CF0,25^FO15,25^FD APELLIDO ^FS ^FO20,70"+compres+"^B3N,N,100,Y,N^A0,29,22^FD TRAMITE ^FS ^FO20,220^FD FECHA ^XZ";
		 
				peSobre=peSobre.replaceAll("TRAMITE", idtramite)+"\n";
				peSobre=peSobre.replaceAll("APELLIDO", apellido);
				peSobre=peSobre.replaceAll("FECHA", fecha);
					
				/* Imprimo etiqueta Receta y Sobre */
		
				for (int r=0; r<2; r++){
				
					try {
						
						 Socket S = new Socket(ip,puerto);	
						 S.setSoTimeout(18000);	
					
						 DataOutputStream out = new DataOutputStream(S.getOutputStream());
						 out.writeBytes(peSobre);
						 out.flush();
						 out.close();
				 
					} catch (SocketException e) {
						System.out.println("SE Etiqueta Generica - " +e);
						return false;
					}  catch (SocketTimeoutException e) {
						System.out.println("STOE Etiqueta Generica - "+e);
						return false;
					} catch (IOException e) {
						System.out.println("IOE Etiqueta Generica - "+e);
						return false;
					} catch (IllegalArgumentException e) {
						System.out.println("IAE Etiqueta Generica - "+e);
						return false;
					} catch (Exception e) {
						System.out.println("E Etiqueta Generica - "+e);
						return false; 
					}						
				}	
	
				String 	peEstudio="";
				
				for (int r=0; r<estudios.size(); r++){
					
					if(estudios.get(r).getEstudio() != null && estudios.get(r).getEstudio().length() != 0 )	{
									
					
					/*	String 	peEstudio="^XA ^CF0,30^FO15,30^A0,,^FD APELLIDO ^FS ^FO15,60^BY1.45^B3N,N,80,Y,N ^FD TRAMITE ^FS ^FO225,70^A0,29,22^FD TRAMITE ^FS ^FO225,110^A0,25,18^FD FECHA ^FS ^FO15,170^A0,25,18^FD ESTUDIO ^FS ^FO430,55^FS ^FO475,75^FS ^FT500,75^A0R,25,25^FS ^XZ"; */ // Para impresora Zebra Negra en NSC xq no se leia el CB 	
						if(compres.equals("^BY1.45"))
							peEstudio="^XA ^CF0,30^FO15,30^A0,,^FD APELLIDO ^FS ^FO15,60"+compres+"^B3N,N,80,Y,N ^FD TRAMITE ^FS ^FO225,70^A0,29,22^FD TRAMITE ^FS ^FO225,110^A0,25,18^FD FECHA ^FS ^FO15,170^A0,25,18^FD ESTUDIO ^FS ^FO430,55^FS ^FO475,75^FS ^FT500,75^A0R,25,25^FD SECTOR ^FS ^XZ";  // Para impresora Zebra Negra en NSC xq no se leia el CB 	
						else
							peEstudio="^XA ^CF0,30^FO20,30^FD APELLIDO ^FS ^FO20,80"+compres+"^B3N,N,100,Y,N^FD TRAMITE ^FS ^FO20,220^FD FECHA ^FS ^FO20,260^FD ESTUDIO ^FS ^FO500,55^GB1,50,1,B,0^FS ^FO475,75^GB50,1,1,B,0^FS ^FT500,75^A0R,25,25^FD SECTOR ^FS ^XZ";	
                 
					    	peEstudio=peEstudio.replaceAll("TRAMITE", idtramite)+"\n";
				   			peEstudio=peEstudio.replaceAll("APELLIDO", apellido);
							peEstudio=peEstudio.replaceAll("FECHA", fecha);
							peEstudio=peEstudio.replaceAll("ESTUDIO", estudios.get(r).getEstudio());
							peEstudio=peEstudio.replaceAll("SECTOR", sector);
						
							salida=salida+peEstudio;
				
							/* Imprimo etiqueta x Cada
							 *  Estudio del Tramite */
					} else 	{ 
						/* Else del IF Validacion Estudios */	
						return false;
					}
				}			
				

				try {
					 Socket S = new Socket(ip,puerto);
						
					 S.setSoTimeout(18000);	
						
						
					 DataOutputStream out = new DataOutputStream(S.getOutputStream());
					 out.writeBytes(salida);
					 out.flush();
				
					 out.close();
				} catch (IllegalArgumentException e) {
					System.out.println("IAE Etiqueta Estudio - "+e);
					return false;
				} catch (SocketException e) {
					System.out.println("SE Etiqueta Estudio - "+e);
					return false;
				}  catch (SocketTimeoutException e) {
					System.out.println("STOE Etiqueta Estudio - "+e);
					return false;
				} catch (IOException e) {
					System.out.println("IOE Etiqueta Estudio - "+e);
					return false;
				} catch (Exception e) {
					System.out.println("E Etiqueta Estudio - "+e);
					return false;
				}						
				
				return true;
	}
	


}
