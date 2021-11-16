package nsc.utils;

import nsc.utils.EstudioEstado;


public class EstudioEstadoGSon {
	
	private String       idMState ;   /* Viene de Android como campo observaciones ya que en Elastic el autoincremental es String */
	private	String		 estado;
	private String		 usuario;     /* Viene de Android como campo idestudio (en realidad en la Base esta como orden)- Representaba antes el codigo de la practica, ahora es el renglòn o posicion del estudio o practica */ 

	
	public EstudioEstadoGSon(){
		
	}
	
	public EstudioEstadoGSon( String idMState_p, String estado_p, String usuario_p) {
  		// Para cargar los valores del Objeto MStateGSon que envia Android	

		this.idMState	    =idMState_p;
		this.estado			=estado_p;
		this.usuario		=usuario_p;
		
	
}
	public EstudioEstadoGSon(EstudioEstado estudioObj){
	       new EstudioEstado(estudioObj.getObservaciones(),
	                          estudioObj.getEstado(),
	                          estudioObj.getIdestudio());
	}
	
	
	
			
				 
	
	public String getIdMState(){
		return idMState;
	}
	public void setIdMState(String idMState_p){
		this.idMState = idMState_p;
	}

	public void setEstado(String estado_p){
		this.estado=estado_p;
		
	}
	public String getEstado(){
		return this.estado;
	}	
	public void setUsuario(String usuario_p){
		this.estado=usuario_p;
		
	}
	public String getUsuario(){
		return this.usuario;
	}	
	
}
