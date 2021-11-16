package nsc.com.mail.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="mail_operacion")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class OMail {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	   id;
		
	@Column(nullable = false)
	private Long 	   id_empresa;
		
	@Column(nullable = false)
	private Long 	   id_sucursal;
	
	@Column(nullable = false)
	private Timestamp 	   fecha;
	
	@Column(nullable = false)
	private Long 	   id_operacion;
	
	@Column(nullable = false, length=30)
	private String 	   email;
	
	@Column(nullable = false)
	private Long 	   id_operador;

	
	public OMail(Long 	id_empresa,
		 	 Long 		id_sucursal,
		 	 Timestamp	fecha,
		 	 Long 		id_operacion,
		 	 String 	id_email,
		 	 Long 		id_operador) {
	 
				 this.id_empresa 	= id_empresa;
				 this.id_sucursal 	= id_sucursal;
				 this.fecha			= fecha;
				 this.id_operacion	= id_operacion;
				 this.email			= id_email;
				 this.id_operador   = id_operador;
	}
}



