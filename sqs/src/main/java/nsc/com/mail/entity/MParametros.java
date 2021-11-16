package nsc.com.mail.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="mail_parametros")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class MParametros {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	id;
		
	@Column(nullable = false)
	private Long 	   id_empresa;
		
	@Column(nullable = false)
	private Long 	   id_sucursal;
	
	@Column(nullable = false)
	private Long 	   id_operacion;
	
	@Column(nullable = false)
	private String 	   from;
	
	@Column(nullable = false)
	private String 	   sendName;
	
	@Column(nullable = false)
	private String 	   lenguaje;
	
	@Column(nullable = false)
	private String 	   subject;
	
	@Column(nullable = false)
	private Long 	   id_operador;
	
	@Column(nullable = false)
	private String 	   api_key;
	
	@Column(nullable = false)
	private String 	   id_template;
	

	

}
