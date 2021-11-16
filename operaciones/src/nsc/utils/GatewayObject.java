package nsc.utils;

import java.io.Serializable;
import java.util.List;

public class GatewayObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8217632006168730293L;
	private	String		usuario;
	private String      fecha;
	private String 		dni;
	private String		afiliado;
	private String  	osocial;
	private String  	plan;
	private String		apellido;
    private String  	fnacimiento;
	private	String 		sexo;
	private String 		telefono;
	private String  	tipo; 
	private	String		id;
	private List<EstudioOrder> lista;
	
	public GatewayObject( ){
	
    }

	public GatewayObject( String  usuario,
						  String  fecha_p,
						  String  dni_p,
						  String  afiliado_p,
						  String  osocial_p,
						  String  plan_p,
						  String  apellido_p,
						  String  fnacimiento_p,
						  String  sexo_p,
						  String  telefono_p,
						  String  tipo_p, 
						  String  id_p,
						  List<EstudioOrder> lista){
		this.usuario	=usuario;
		this.fecha  	=fecha_p;
		this.dni  		=dni_p;
		this.afiliado	=afiliado_p;
		this.osocial 	=osocial_p;
		this.plan 		=plan_p;
		this.apellido	=apellido_p;
		this.fnacimiento=fnacimiento_p;
		this.sexo		=sexo_p;
		this.telefono	=telefono_p;
		this.tipo  		=tipo_p;
		this.id			=id_p;
		this.lista		=lista;

	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(String usuario_p) {
		this.usuario=usuario_p;
	}
	
	public String getFecha() {
		return this.fecha;
	}	
	public void setFecha(String fecha_p){
		this.fecha=fecha_p;
		
	}
	public String getDni() {
		return this.dni;
	}
	
	public void setDni(String dni_p) {
		this.dni=dni_p;
	}
	public String getAfiliado() {
		return this.afiliado;
	}
	
	public void setAfiliado(String afiliado_p) {
		this.afiliado=afiliado_p;
	}
	
	public String getOsocial() {
		return this.osocial;
	}
	
	public void setOsocial(String osocial_p) {
		this.osocial=osocial_p;
	}
	
	public String getPlan() {
		return this.plan;
	}
	
	public void setPlan(String plan_p) {
		this.plan=plan_p;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public void setApellido(String apellido_p) {
		this.apellido=apellido_p;
	}
	
	public String getFnacimiento() {
		return this.fnacimiento;
	}
	public void setFnacimiento(String fnacimiento_p) {
		this.fnacimiento=fnacimiento_p;
	}
	
	public void setSexo(String sexo_p) {
		this.sexo=sexo_p;
	}
	public String getSexo() {
		return this.sexo;
	}	
	public void setTelefono(String telefono_p){
		this.telefono=telefono_p;
		
	}

	public String getTelefono() {
		return this.telefono;
	}	
	public void setTipo(String tipo_p){
		this.tipo=tipo_p;
		
	}

	public String getTipo() {
		return this.tipo;
	}	

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id=id;
	}

	
	public List<EstudioOrder> getLista() {
		return this.lista;
	}	
	public void setLista(List<EstudioOrder> lista){
		this.lista=lista;
		
	}
	
}
