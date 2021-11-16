package nsc.utils;





public class EstudioEstado {
	
	private String       observaciones;   /* es el IdMState -  Aqui se utilizara solo para actualizar IVR*/
	private	String		 estado;
	private String		 idestudio;       /* es el Còdigo de Idestudio de antes - Ahora toma el campo Orden 0.1.2.3.... 
	 										 Es el que imprime en el talòn de retiro y se debe ingresar al consultar por el
	 										 IVR cuando solicita Còdigo de la pràctica */

	
	public EstudioEstado(){
		
	}
	
	public EstudioEstado( String observaciones_p, String estado_p, String idestudio_p) {
  		// Para cargar los valores del Objeto MStateJSon que envia Android	
	
		this.observaciones 	= observaciones_p;
		this.estado			= estado_p;
		this.idestudio		= idestudio_p;
		
	}
	
	public EstudioEstado(EstudioEstado estudioObj){

	        new EstudioEstado(estudioObj.getObservaciones(),
	                          estudioObj.getEstado(),
	                          estudioObj.getIdestudio());
	}
	public EstudioEstado(EstudioEstadoGSon estudioObj){
		
		this.observaciones  =estudioObj.getIdMState();
		this.estado			=estudioObj.getEstado();
		this.idestudio =estudioObj.getUsuario();   
	}
	
	
			
				 
	
	public String getObservaciones(){
		return observaciones;
	}
	public void setObservaciones(String observaciones_p){
		this.observaciones = observaciones_p;
	}

	public void setEstado(String estado_p){
		this.estado=estado_p;
		
	}
	public String getEstado(){
		return this.estado;
	}	
	public void setIdestudio(String idestudio_p){
		this.idestudio = idestudio_p;
		
	}
	public String getIdestudio(){
		return this.idestudio;
	}	
	
}
