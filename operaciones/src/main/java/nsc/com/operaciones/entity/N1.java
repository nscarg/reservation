package nsc.com.operaciones.entity;

import java.util.Date;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="n1")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class N1 {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	   idn1;
	

	@ApiModelProperty(notes = "Id Empresa",required = true)
	@Column(nullable = false)
	private Long 	   idemp;
	
	@ApiModelProperty(notes = "Id Sucursal",required = true)
	@Column(nullable = false)
	private Long 	   idsuc;
		
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Denominacion N1",required = true)
	private String 	   deno;

}
