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


public class EnvioRequest {
	
	Date 							fechaemision;
	String 							fecha_p;
	private static String 			TF_NAME ="EquipoMessage.nsc";
	private String                  origen;
	private String equipoip;
	private int equipopuerto;
	private NSC_AuditCSV			auditoriaMail;
	
	public void setOrigen (String origen_p) {
		origen = origen_p;
	}
	public String getOrigen () {
		  return origen;
	}
	
	public void setEquipoip (String equipoip_p) {
		this.equipoip=equipoip_p;
	}
	public String getEquipoip() {
		  return equipoip;
	}
	public void setEquipopuerto(int equipopuerto_p) {
		equipopuerto = equipopuerto_p;
	}
	public int getEquipopuerto() {
		  return this.equipopuerto;
	}
	public void setAuditoriaMail (NSC_AuditCSV auditoriaMail_p) {
		  this.auditoriaMail = auditoriaMail_p;
	}
	public NSC_AuditCSV getAuditoriaMail () {
		  return this.auditoriaMail;
	}

	public String envioEquipoRequest(	String  fecha_p,
										String  dni_p,
										String  afiliado_p,
										String  osocial_p,
										String  plan_p,
										String  apellido_p,
										String  fnacimiento_p,
										String  sexo_p,
										String  telefono_p,
										String  tipo_p, 
										String  id_p,
										String  idestudio_p,
										String  estudio_p,
										String  equipo_p,
										String modalidad_p){
		FileInputStream 	fileIS;

		String 				salida="";

		try{

		File f = new File(getOrigen()+TF_NAME);
		fileIS = new FileInputStream(f);
		BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));
		String readString = new String();


		String plantillae="";
	
		
		while((readString = buf.readLine())!= null){
				plantillae=readString;
				plantillae=plantillae.replaceAll("FECHA", fecha_p);
				plantillae=plantillae.replaceAll("DNI", dni_p);
				plantillae=plantillae.replaceAll("AFILIADO", afiliado_p);
				plantillae=plantillae.replaceAll("APELLIDO", apellido_p);
				plantillae=plantillae.replaceAll("FNACIM", fnacimiento_p);
				plantillae=plantillae.replaceAll("SEXO", sexo_p);
				if(telefono_p != null)
				    plantillae=plantillae.replaceAll("TELEFONO", telefono_p);
				else
					plantillae=plantillae.replaceAll("TELEFONO", "");
				
				plantillae=plantillae.replaceAll("TIPO", tipo_p);
				plantillae=plantillae.replaceAll("TRAMITE", id_p);
				plantillae=plantillae.replaceAll("DESDE", fecha_p);
				plantillae=plantillae.replaceAll("HASTA", calcularFecha(fecha_p));
				plantillae=plantillae.replaceAll("OSOCIAL",osocial_p);
				plantillae=plantillae.replaceAll("PLAN",plan_p);
				plantillae=plantillae.replaceAll("IDEXAMEN", idestudio_p);
			
				plantillae=plantillae.replaceAll("ESTUDIO", estudio_p);
				plantillae=plantillae.replaceAll("EQUIPOS", equipo_p);
				plantillae=plantillae.replaceAll("MODALIDAD", modalidad_p);
				
				
				//salida=salida+"\r"+plantillae;
				salida=salida+plantillae+"\r";
		}	
		
		
		fileIS.close();	
		
			
		} catch (FileNotFoundException e) {
		System.out.println("FNF - "+e);
		return null;
		} catch (IOException e){
		System.out.println("IOE - "+e);
		return null;
		} catch(Exception ex) {
			System.out.println("E - "+ex);
			return null;
		}

		return salida;
		}




    public String calcularFecha(String fecha_d)  {
    
    	 Long milliHasta;
    	 String fecha_str=null;
    	 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	
    	 try {
	    	
	    	 Date fecha =df.parse(fecha_d);
	    	 milliHasta =  fecha.getTime()+900000;
	       	 fecha = new Date(milliHasta);
	    	 fecha_str=df.format(fecha);
	    	
	    	 
    	 } catch(Exception ex ) {
    		 System.out.println("EnvioRequest ---- "+ex);
    	 }
    	 
    	return fecha_str; 
    }
    
    
	

  
	public  boolean enviar(   String envio_p){

		 			 	
			try {
				
				System.out.println("ENVIO- Gateway "+envio_p);		
				auditoriaMail.auditEnvio(envio_p,"salida_lote.csv");
				Socket S = new Socket(equipoip,equipopuerto);
				String data;
				BufferedReader entrada;
				
				
					S.setSoTimeout(15000);	
					
					
					DataOutputStream out = new DataOutputStream(S.getOutputStream());
					out.writeBytes(envio_p);
					out.flush();
					out.close();
					
				
					return true;
		
				    
				
			} catch (SocketTimeoutException e) {
				auditoriaMail.auditEnvio("STE---"+e+"\n","salida_lote.csv");
				System.out.println("STOE - Gateway "+e);
				return false;
			} catch (InterruptedIOException e) {
				auditoriaMail.auditEnvio("IE---"+e+"\n","salida_lote.csv");
				System.out.println("IE - Gateway "+e);
				return false;
			} catch (SocketException e) {
				auditoriaMail.auditEnvio("SE---"+e+"\n","salida_lote.csv");
				System.out.println("SE - Gateway "+e);
				return false;
			}  catch (IOException e) {
				auditoriaMail.auditEnvio("IOE---"+e+"\n","salida_lote.csv");
				System.out.println("IOE - Gateway "+e);
				return false;
			} catch (IllegalArgumentException e) {
				auditoriaMail.auditEnvio("IAE---"+e+"\n","salida_lote.csv");
				System.out.println("IAE - Gateway "+e);
				return false;
			} catch (Exception e) {
				auditoriaMail.auditEnvio("E---"+e+"\n","salida_lote.csv");
				System.out.println("E - Gateway "+e);
				return false;
			}
		
			
	}

	
	

	
	

}
