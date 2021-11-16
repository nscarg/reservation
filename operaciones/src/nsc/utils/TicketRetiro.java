package nsc.utils;

import java.io.*;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.SimpleDateFormat;



import org.apache.log4j.Logger;

//import nsc.com.model.domain.Protodd;


import nsc.utils.Concatenacion;

public class TicketRetiro {
 
	String origen;
	String destino;
	String osName;
    Concatenacion concatenacion;
    /** Logger for this class */
	static Logger logger = Logger.getLogger(TicketRetiro.class);
	
	
	
	public TicketRetiro(){
		
	}
			
	public Boolean accion(String protocolo,
			              List 		l_estudios,
			              String 	host,
			              long		puerto){ // Protodd
		
		//String protocolo_id = String.valueOf(protocolo.getId());
		String protocolo_id = String.valueOf("10123"); 
		
		
		Boolean flag1=false;
		Boolean flag2=false;
		Boolean flag3=false;
		Boolean flag4=false;
		Boolean flag5=false;
	
		// Seccuencia de Tareas
		flag1 =this.armado(protocolo, l_estudios);
		flag2 =this.armadoPie(protocolo);
		flag3 =     concatenacion.combineFile(protocolo_id);
		flag4= this.impresion(host, puerto, protocolo_id);
	 //   flag5= this.EliminacionAImpresion(String.valueOf(protocolo.getId()));
		flag5= this.EliminacionAImpresion(String.valueOf(protocolo_id));
		
		if (flag1 == true &&
			flag2 == true &&
			flag3 == true &&
			flag4 == true &&
			flag5 == true){
		
			return true;
		} else {
			return false;
		}
		
	}	
	
	public Boolean armado(String protocolo, List l_estudios){ //Protodd
		
	    try{
	
		    FileInputStream fs = new FileInputStream(origen+"cuerpo.plantilla");
		    DataInputStream in = new DataInputStream(fs);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    
			FileOutputStream fos; 
		    DataOutputStream dos;
//		    
		  //  File file= new File(destino+"C"+String.valueOf(protocolo.getId())+".nsc"); 
		    File file= new File(destino+"C"+String.valueOf(protocolo)+".nsc"); 
		    fos = new FileOutputStream(file);
		    dos=new DataOutputStream(fos);
		    
		    String strLine;
		    String plantilla="";

		    while ((strLine = br.readLine()) != null)   {
		      plantilla=strLine;
		      
		     // plantilla=plantilla.replaceAll("PROTOCOLO"    ,String.valueOf(protocolo.getId()))+"\n";
		      plantilla=plantilla.replaceAll("PROTOCOLO"    ,String.valueOf(protocolo))+"\n";
	         // plantilla=plantilla.replaceAll("APELLIDO" 	,protocolo.getAfiliado().getApellido());
		      plantilla=plantilla.replaceAll("APELLIDO" 	,"AGUIRRE");
		    //  plantilla=plantilla.replaceAll("NOMBRE"   	,protocolo.getAfiliado().getNombre());
		      plantilla=plantilla.replaceAll("NOMBRE"   	,"LUIS");
		   //   plantilla=plantilla.replaceAll("FECHA"    	,protocolo.getFecha().toString());
		      plantilla=plantilla.replaceAll("FECHA"    	,GregorianCalendar.getInstance().getTime().toString());
		   //   plantilla=plantilla.replaceAll("AFILIADO" 	,protocolo.getAfiliado().getAfiliado());
		      plantilla=plantilla.replaceAll("AFILIADO" 	,"141217");
		      dos.writeBytes(plantilla);
		      
		    }
		   

			for (int r=0; r < l_estudios.size() ; r++){
			  dos.writeBytes(l_estudios.get(r)+"\n");
			}
		    
		    dos.close();
		    fos.close();
		    	
	    	in.close();
	    	fs.close();
	    		    	
	    	return true;
	    	    
	    }catch (Exception e){
	      System.err.println("NSC TicketRetiro armado : " + e.getMessage());
	      return false;
	    }

	}
	
	public Boolean armadoPie(String protocolo){ //Protodd
	    try{
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 
	    //	String fcom = sdf.format(protocolo.getFcompromiso());
	    	String fcom = sdf.format("2017-01-14");
	  	
		    FileInputStream fs = new FileInputStream(origen+"pie.plantilla");
		    DataInputStream in = new DataInputStream(fs);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    
			FileOutputStream fos; 
		    DataOutputStream dos;
//		    
		//   File file= new File(destino+"P"+String.valueOf(protocolo.getId())+".nsc"); 
		    File file= new File(destino+"P"+protocolo+".nsc"); 
		    fos = new FileOutputStream(file);
		    dos=new DataOutputStream(fos);
	    
		    String strLine;
		    String plantilla;
	
		    while ((strLine = br.readLine()) != null)   {
		      plantilla=strLine;
		      
		      plantilla=plantilla+"\n";
		      plantilla=plantilla.replaceAll("ENTREGA"   	,fcom);
		      dos.writeBytes(plantilla);
		    }
		    
		    dos.close();
		    fos.close();
		    	
	    	in.close();
	    	fs.close();
	    		    	
	    	return true;
	    	    
	    }catch (Exception e){
	    	System.err.println("NSC TicketRetiro armadoPie: " + e.getMessage());
	      return false;
	    }
	}
	
	public Boolean impresion(String host_p, long puerto_p, String protocolo_p){
		String[] cmd = new String[3];

		if( osName.equals( "Windows" ) ){
			cmd[0] = "cmd.exe" ;
			cmd[1] = "/C" ;
//			
			cmd[2] = "lpr -S "+host_p+" -P lp -o l "+destino+"T"+protocolo_p+".nsc";
		}
		
		Runtime rt = Runtime.getRuntime(); 
		
		try {
			Process proc = rt.exec(cmd);
			return true;			
			
		} catch (IOException e) {
			System.err.println("NSC TicketRetiro impresion : " + e.getMessage());
			return false;
		}
		
	
	}
	
	public Boolean EliminacionAImpresion(String protocolo_p){
	
		try {
			
			File ficheroC = new File(destino+"C"+protocolo_p+".nsc");
			File ficheroP=  new File(destino+"P"+protocolo_p+".nsc");
			Boolean flagC = false;
			Boolean flagP = false;
			
			flagC =ficheroC.delete();
			flagP =ficheroP.delete();
	
			if (flagC==true && flagP==true)
				return true;
			else
				return false;	
			
	    } catch (SecurityException e) {
	        System.err.println("NSC TicketRetiro EliminacionAImpresion :"+e.getMessage());
	        return false;
	    }
		
	}

	
	public String getOrigen() {
		return this.origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getDestino() {
		return this.destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getOsName() {
		return this.osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	
	public Concatenacion getConcatenacion() {
		return this.concatenacion;
	}
	public void setConcatenacion(Concatenacion concatenacion) {
		this.concatenacion = concatenacion;
	}
	
}
