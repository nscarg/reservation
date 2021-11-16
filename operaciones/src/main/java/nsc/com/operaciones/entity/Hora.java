package nsc.com.operaciones.entity;


import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="hora")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Hora {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	   idhora;

	
	@ApiModelProperty(notes = "Id Empresa",required = true)
	@Column(nullable = false)
	private Long 	   idemp;
	
	@ApiModelProperty(notes = "Id Sucursal",required = true)
	@Column(nullable = false)
	private Long 	   idsuc;
			
	@ApiModelProperty(notes = "Id N1",required = true)
	@Column(nullable = false)
	private Long 	   idn1;
		
	@ApiModelProperty(notes = "Id N2",required = true)
	@Column(nullable = false)
	private Long 	   idn2;
	
	@ApiModelProperty(notes = "Id Agente",required = true)
	@Column(nullable = false)
	private Long 	   idage;
	
	@ApiModelProperty(notes = "Numero de Dia 1-7",required = true)
	@Column(nullable = false)
	private Integer	   dia;
	
	@ApiModelProperty(notes = "xx.xx formato permitido",required = true)
	@Column(nullable = false)
	private BigDecimal  hora;
	
	@ApiModelProperty(notes = "Cantidad de Turnos Simultaneos Habilitados",required = true)
	@Column(nullable = false)
	private Long 	   qturnos;
	

}
