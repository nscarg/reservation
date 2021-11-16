package nsc.utils;

import java.util.List;

public class NSC_Error {
	String operacion;
	String destino;
	String fecha;
	String descripcion;
	
	public NSC_Error(){
		
	}
	public NSC_Error(String operacion, String destino , String fecha, String descripcion){
		this.operacion   = operacion;
		this.destino     = destino;
		this.fecha     	 = fecha;
		this.descripcion = descripcion;
	}
	
	public void setOperacion(String operacion){
		this.operacion = operacion;
	}
	public String getOperacion(){
		return operacion;
	}
	public void setDestino(String destino){
		this.destino = destino;
	}
	public String getDestino(){
		return destino;
	}	
	public void setFecha(String fecha){
		this.fecha = fecha;
	}
	public String getFecha(){
		return fecha;
	}
	public void setDescripicon(String descripcion){
		this.descripcion = descripcion;
	}
	public String getDescripcion(){
		return descripcion;
	}
	
	
	public String ver(List<NSC_Error> errores){
		 String leyenda="Notificacion Errores " +"\n";
	
		  for(int e=0; e < errores.size(); e++) {
			  
		      leyenda=leyenda+((NSC_Error)errores.get(e)).getOperacion()+ " "+
		                      ((NSC_Error)errores.get(e)).getDestino()+" "+
		                      ((NSC_Error)errores.get(e)).getFecha()+" "+
		                      ((NSC_Error)errores.get(e)).getDescripcion()+"\n";
	      
		  }  	  

		return leyenda;
	}
	
}
