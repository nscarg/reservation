package nsc.com.operaciones.entity;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sucursal")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Sucursal {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	   idsuc;
	
	
	
	@ApiModelProperty(notes = "Id Empresa",required = true)
	@Column(nullable = false)
	private Long 	   idemp;
	
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Denominacion de la Sucursal",required = true)
	private String 	   deno;

}
