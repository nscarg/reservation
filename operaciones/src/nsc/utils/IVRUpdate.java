package nsc.utils;

import java.io.Serializable;
import java.util.List;

public class IVRUpdate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8017248604317528215L;
	/**
	 * 
	 */
	
	private	String		operacion;
	private List<EstudioEstado> lista;
	
	
		 
	  
	
	public IVRUpdate( ){
	
    }

	public IVRUpdate( 	  String  operacion,
						  List<EstudioEstado> lista){
		this.operacion = operacion;
		this.lista     = lista;
		
	}
	
	public String getOperacion() {
		return this.operacion;
	}
	
	public void setOperacion(String operacion_p) {
		this.operacion = operacion_p;
	}
	
	
	public List<EstudioEstado> getLista() {
		return this.lista;
	}	
	public void setLista(List<EstudioEstado> lista){
		this.lista=lista;
		
	}
	
}
