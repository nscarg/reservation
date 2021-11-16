package nsc.utils;

import java.io.Serializable;
import java.util.List;

public class IVRObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4682363638844700522L;
	/**
	 * 
	 */
	
	private	String		operacion;
	private	String		cliente;
	private	String		sucursal;
	private	String		sector;
	private String      idtramite;
	private String      afiliado;
	private String      apellido;
	private String      telefono;
	private String      edad;
	private String      sexo;
	private String      tipo;
	private String      fingreso;
	private List<EstudioEstado> lista;
	
	
		 
	  
	
	public IVRObject( ){
	
    }

	public IVRObject( 	  String  operacion,
						  String  cliente,
						  String  sucursal,
						  String  sector,
						  String  idtramite,
						  String  afiliado,
						  String  apellido,
						  String  telefono,
						  String  edad,
						  String  sexo,
						  String  tipo,
						  String  fingreso,
						  List<EstudioEstado> lista){
		this.operacion = operacion;
		this.cliente   = cliente;
		this.sucursal  = sucursal;
		this.sector    = sector;
		this.idtramite = idtramite;
		this.afiliado  = afiliado;
		this.apellido  = apellido;
		this.telefono  = telefono;
		this.edad	   = edad;
		this.sexo 	   = sexo;
		this.tipo      = tipo;
		this.fingreso  = fingreso;
		this.lista     = lista;
		
	}
	
	public String getOperacion() {
		return this.operacion;
	}
	
	public void setOperacion(String operacion_p) {
		this.operacion = operacion_p;
	}
	
	public String getCliente() {
		return this.cliente;
	}	
	public void setCliente(String cliente_p){
		this.cliente=cliente_p;
		
	}
	public String getSucursal() {
		return this.sucursal;
	}
	
	public void setSucursal(String sucursal_p) {
		this.sucursal=sucursal_p;
	}
	public String getSector() {
		return this.sector;
	}
	
	public void setSector(String sector_p) {
		this.sector = sector_p;
	}
	
	public String getIdtramite() {
		return this.idtramite;
	}
	
	public void setIdtramite(String idtramite_p) {
		this.idtramite = idtramite_p;
	}
	
	public String getAfiliado() {
		return this.afiliado;
	}
	
	public void setAfiliado(String afiliado_p) {
		this.afiliado = afiliado_p;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public void setApellido(String apellido_p) {
		this.apellido=apellido_p;
	}
	

	public String getTelefono() {
		return this.telefono;
	}	
	
	
	public void setTelefono(String telefono_p){
		this.telefono = telefono_p;
		
	}
	public void setEdad(String edad_p){
		this.edad = edad_p;
		
	}
	public String getEdad() {
		return this.edad;
	}
	
	public void setSexo(String sexo_p) {
		this.sexo=sexo_p;
	}
	public String getSexo() {
		return this.sexo;
	}	
	
	public void setTipo(String tipo_p){
		this.tipo=tipo_p;
		
	}

	public String getTipo() {
		return this.tipo;
	}	

	
	public String getFingreso() {
		return this.fingreso;
	}
	
	public void setFingreso(String fingreso) {
		this.fingreso = fingreso;
	}
	
	
	public List<EstudioEstado> getLista() {
		return this.lista;
	}	
	public void setLista(List<EstudioEstado> lista){
		this.lista=lista;
		
	}
	
}
