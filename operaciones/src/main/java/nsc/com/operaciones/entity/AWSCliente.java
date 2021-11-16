package nsc.com.operaciones.entity;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="aws_cliente")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class AWSCliente {
	@Id
	private Long 	   idemp;
	
	@Column(nullable = false, length=150)
	@ApiModelProperty(notes = "Region AWS", required = true)
	private String 	region;

	@Column(nullable = false, length=150)
	@ApiModelProperty(notes = "Ipool ID AWS", required = true)
	private String 	ipoolid;
	
	@Column(nullable = true, length=150)
	@ApiModelProperty(notes = "Cola SQS Suscripcion", required = false)
	private String 	sqssusc;
	
	@Column(nullable = true, length=150)
	@ApiModelProperty(notes = "Cola SQS Reservas", required = false)
	private String 	sqsres;
	
	@Column(nullable = true, length=150)
	@ApiModelProperty(notes = "Cola SQS Turnos", required = false)
	private String 	sqsturn;
	
	@Column(nullable = true, length=1)
	@ApiModelProperty(notes = "Cantidad Maxima Archivos Almacenar", required = false)
	private Long 	filesqfile;
	
	@Column(nullable = true, length=10)
	@ApiModelProperty(notes = "Limite en Bytes de los Archivos", required = false)
	private Long 	filessize;
	
}
