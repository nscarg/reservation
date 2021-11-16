package nsc.utils;

import java.io.InputStream;
import java.io.Serializable;

public class NSC_MensajeMail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String fecha;
	private String asunto;
	private String cuerpo;
	private InputStream narchivo;
	private Boolean estado;
	
	public NSC_MensajeMail(){
		
	}
	
    public NSC_MensajeMail(String mensaje){
    	 this.cuerpo=mensaje;
		
	}
	public NSC_MensajeMail(NSC_MensajeMail clone){
		this.id    		= clone.getId();
		this.fecha 		= clone.getFecha();
		this.asunto		= clone.getAsunto();
		this.cuerpo		= clone.getCuerpo();
		this.narchivo	= clone.getNarchivo();
		this.estado		= clone.getEstado();
	}
	
	public  NSC_MensajeMail(String id_p,
				   			String fecha_p,
				   			String asunto_p,
				   			String cuerpo_p,
				   			InputStream narchivo_p,
				   			Boolean	estado_p) {
		
        this.id    		= id_p;
		this.fecha 		= fecha_p;
		this.asunto		= asunto_p;
		this.cuerpo		= cuerpo_p;
		this.narchivo	= narchivo_p;
		this.estado		= estado_p;
	}
	
	public void setId(String id_p){
		this.id = id_p;
	}
	public String getId(){
		return id;
	}
	public void setFecha(String fecha_p){
		this.fecha = fecha_p;
	}
	public String getFecha(){
		return fecha;
	}
	public void setAsunto(String asunto_p){
		this.asunto = asunto_p;
	}
	public String getAsunto(){
		return asunto;
	}	
	public void setCuerpo(String cuerpo_p){
		this.cuerpo = cuerpo_p;
	}
	public String getCuerpo(){
		return cuerpo;
	}
	public void setNarchivo(InputStream narchivo_p){
		this.narchivo = narchivo_p;
	}
	public InputStream getNarchivo(){
		return narchivo;
	}
	
	public void setEstado(Boolean estado_p){
		this.estado = estado_p;
	}
	public Boolean getEstado(){
		return estado;
	}
}
