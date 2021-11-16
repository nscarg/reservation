package nsc.com.operaciones.entity;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="aws")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class AWS {
	
	@Id
	private Long 	   id;
	
	
	@Column(nullable = false, length=150)
	@ApiModelProperty(notes = "Region AWS", required = true)
	private String 	region;
	
	@Column(nullable = false, length=150)
	@ApiModelProperty(notes = "Ipool AWS", required = true)
	private String 	ipoolid;
	
	@Column(nullable = false, length=150)
	@ApiModelProperty(notes = "SQS Suscripcion", required = true)
	private String 	sqssusc;
	
	@Column(nullable = false, length=150)
	@ApiModelProperty(notes = "SQS Reserva", required = true)
	private String 	sqsres;
	
	@Column(nullable = false, length=150)
	@ApiModelProperty(notes = "SQS Turnos", required = true)
	private String 	sqsturn;
	
	

}
