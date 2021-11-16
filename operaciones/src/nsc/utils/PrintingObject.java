package nsc.utils;

import java.io.Serializable;
import java.util.List;


public class PrintingObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8217632006168730293L;
	private	String		usuario;
	private	String		clave;
	private	String		id;
	private String		apellido;
	private String		sectores;
	private String      fecha;
	
	
	


	private List<EstudioItem> lista;
	
	public PrintingObject( ){
	
    }

	public PrintingObject( String usuario,
						   String  clave_p,
						   String 	id_p,
						   String	apellido_p,
						   String	sectores_p,
						   String  fecha_p,
						   List<EstudioItem> lista){
		this.usuario=usuario;
		this.clave      =   clave_p;
		this.id			=	id_p;
		this.apellido	=	apellido_p;
		this.sectores	=	sectores_p;
		this.fecha		=   fecha_p;
		this.lista=lista;

	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario=usuario;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public void setClave(String clave) {
		this.clave=clave;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id=id;
	}

	public String getApellido() {
		return this.apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido=apellido;
	}
	
	
	public String getSectores() {
		return this.sectores;
	}	
	public void setSectores(String sectores){
		this.sectores=sectores;
		
	}

	public String getFecha() {
		return this.fecha;
	}	
	public void setFecha(String fecha){
		this.fecha=fecha;
		
	}
	
	public List<EstudioItem> getLista() {
		return this.lista;
	}	
	public void setLista(List<EstudioItem> lista){
		this.lista=lista;
		
	}
	
}
