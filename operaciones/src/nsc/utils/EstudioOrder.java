package nsc.utils;

import java.io.Serializable;


public class EstudioOrder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1697688448036117454L;
	private	String		equipo;
    private	String		estudio;
	private String		$key;
	
	
	public EstudioOrder (	String equipo_p,String  estudio_p,
							String  $key_p){
		
		equipo	=		equipo_p;
		estudio	=		estudio_p;
		$key   	=		$key_p;
	
	}
			
				 
	public String getEquipo() {
		return this.equipo;
	}
	
	public void setEquipo(String equipo_p) {
		this.equipo=equipo_p;
	}
	
	public String getEstudio() {
		return this.estudio;
	}
	
	public void setEstudio(String estudio_p) {
		this.estudio=estudio_p;
	}
	
	
	public void set$key(String $key_p){
		this.$key = $key_p;
		
	}
	public String get$key() {
		return this.$key;
	}	
	
	
}
