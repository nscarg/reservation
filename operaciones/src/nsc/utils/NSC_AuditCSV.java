package nsc.utils;

	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;

	public class NSC_AuditCSV {
		 
	  private String destino; 
	
	    
		public java.lang.String getDestino () {
		  return this.destino;
		}
		public void setDestino (java.lang.String destino_p) {
		  this.destino = destino_p;
		}
		
		public void audit(String parametros_p, String archivo_p){
		
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(destino+archivo_p, true));
				out.write(parametros_p+"\n");
				out.close();
			
			} catch (IOException e){
				System.out.println("NSC : Audit_csv "+ e);
			}
		}
		
		public void auditEnvio(String envio_p,String archivo_p){
		//	System.out.println("NSC : Audit_csv "+ envio_p);
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(destino+archivo_p, true));
				
				out.write(envio_p);
				out.close();
			
			} catch (IOException e){
				System.out.println("NSC : Audit_csv "+ e);
			}
		}
	}


