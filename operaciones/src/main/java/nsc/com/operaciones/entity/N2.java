package nsc.com.operaciones.entity;


import java.util.Date;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="n2")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class N2 {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	   idn2;

	
	@ApiModelProperty(notes = "Id Empresa",required = true)
	@Column(nullable = false)
	private Long 	   idemp;
	
	@ApiModelProperty(notes = "Id Sucursal",required = true)
	@Column(nullable = false)
	private Long 	   idsuc;
	
	@ApiModelProperty(notes = "Id N1",required = true)
	@Column(nullable = false)
	private Long 	   idn1;
	
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Denominacion Nivel 2",required = true)
	private String 	   deno;

}
