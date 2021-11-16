package nsc.com.operaciones.entity;




import java.math.BigDecimal;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cliente")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Cliente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	idcli;

		
	@ApiModelProperty(notes = "Id Empresa",required = true)
	@Column(nullable = false)
	private Long 	   idemp;
		
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Apellido del Cliente", required = true)
	private String 	apell;
	
	@Column(nullable = false, length=30)
	@ApiModelProperty(notes = "Email del Cliente", required = true)
	private String 	email;
	
		
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Telefono del Cliente", required = true)
	private String 	telef;
	

	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Documento ID", required = true)
	private String 	doc;
	
	@Column(nullable = true, length=20)
	@ApiModelProperty(notes = "Referencia ID", required = false)
	private String 	ref;
	
	@Column(nullable = false, length=1)
	@ApiModelProperty(notes = "Estado  'P:Pendiente, R:Registrado, N:No Habilitado ", required = true)
	private String 	est;
	
	@Column(nullable = true, length=20)
	@ApiModelProperty(notes = "Twitter Id", required = false)
	private String 	twitter;
	
	@Column(nullable = false, length=5)
	@ApiModelProperty(notes = "Lenguaje", required = false)
	private String 	idio;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Fecha Alta", required = true)
	private String 	alta;
	
	@Column(nullable = true )
	@ApiModelProperty(notes = "plataforma", required = true)
	private String	   plataforma;
	
	
	

}
