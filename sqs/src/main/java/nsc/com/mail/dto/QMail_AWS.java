package nsc.com.mail.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class QMail_AWS {
	
	private Long    id_empresa;
	private Long	id_operacion;
	private String 	lenguaje;
	private Long	id_operador;
	private Long	id_sucursal;
	private String  email;
	private String  datos;
	
}
