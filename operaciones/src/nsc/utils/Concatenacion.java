package nsc.utils;

import java.io.*;

public class Concatenacion {
	String 	  origen;
	String [] tipos = {"C","P"};
	
	public Concatenacion(){
	}
	
	public Boolean combineFile(String protocolo_p) {

	    try{
			FileOutputStream fos; 
		    DataOutputStream dos;
	    
		    File file= new File(origen+"T"+protocolo_p+".nsc");
		    fos = new FileOutputStream(file);
		    dos=new DataOutputStream(fos);
		    
		    String strLine;
//				    
		    for(int fileCount=0;fileCount<tipos.length;fileCount++) {
		    	FileInputStream fs = new FileInputStream(origen+tipos[fileCount]+protocolo_p+".nsc");
		 	    DataInputStream in = new DataInputStream(fs);
		 	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		   
		 	    while ((strLine = br.readLine()) != null)   {
		 	      dos.writeBytes(strLine+"\n");
		 	    }
		    	in.close();
		    	fs.close();   
		    }   
		    dos.close();
		    fos.close();
	    	return true;
	    	    
	    }catch (Exception e){//Catch exception if any
	      System.err.println("NSC Contatenacion : combineFile() : " + e.getMessage());
	      return false;
	    }
	}
		

	
	public String getOrigen() {
		return this.origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	

}
