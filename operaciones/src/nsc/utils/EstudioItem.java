package nsc.utils;

import java.io.Serializable;
import java.util.Date;


public class EstudioItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1697688448036117454L;
    private	String		estudio;
	private Date		feentrega;
	private String		$key;
	
	
	public EstudioItem( 	String  estudio_p,
							Date	feentrega_p,
							String  $key_p){
		
		
		estudio=		estudio_p;
		feentrega=		feentrega_p;
		$key     =		$key_p;
	
	}
			
				 
	
	
	public String getEstudio() {
		return this.estudio;
	}
	
	public void setEstudio(String estudio_p) {
		this.estudio=estudio_p;
	}
	
	
	
	
	
	public Date getFeentrega() {
		return this.feentrega;
	}
	
	public void setFeentrega(Date feentrega_p){
		this.feentrega=feentrega_p;
		
	}
	
	
	public void set$key(String $key_p){
		this.$key = $key_p;
		
	}
	public String get$key() {
		return this.$key;
	}	
	
	
}
