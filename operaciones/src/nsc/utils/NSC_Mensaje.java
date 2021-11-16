package nsc.utils;



public class NSC_Mensaje {
	// Fields    

	private String id;
	private String resultado;
	
		
	// Constructors
	/** default constructor */
	public NSC_Mensaje() {
	}

	/** minimal constructor */
	
	public NSC_Mensaje(String id, String resultado) {
		this.id 	    = id;
		this.resultado 	= resultado;
		
	}


	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getResultado() {
		return this.resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
