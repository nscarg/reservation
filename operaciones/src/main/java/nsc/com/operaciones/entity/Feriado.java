package nsc.com.operaciones.entity;







import java.util.Date;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="feriado")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Feriado {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	idferiado;

		
	@ApiModelProperty(notes = "Id Pais",required = true)
	@Column(nullable = false)
	private Long 	   pais;
		
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Fecha Feriado", required = true)
	@Temporal(TemporalType.DATE)
	private Date 	fecha;
	
	@Column(nullable = false )
	@ApiModelProperty(notes = "Detalle Feriado", required = true)
	private String	   deno;
	

}
