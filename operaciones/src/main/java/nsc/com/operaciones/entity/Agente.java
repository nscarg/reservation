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
@Table(name="agente")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Agente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	idagente;

	
	
	@ApiModelProperty(notes = "Id Empresa",required = true)
	@Column(nullable = false)
	private Long 	   idemp;
	
	@ApiModelProperty(notes = "Id Sucursal",required = true)
	@Column(nullable = false)
	private Long 	   idsuc;
	
	@ApiModelProperty(notes = "Nivel 1",required = true)
	@Column(nullable = false)
	private Long 	   idn1;
	
	@ApiModelProperty(notes = "Nivel 2",required = true)
	@Column(nullable = false)
	private Long 	   idn2;
	
	@Column(nullable = true, length=20)
	@ApiModelProperty(notes = "Id del Agente", required = false)
	private String 	id;
	
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Denominacion", required = true)
	private String 	deno;
	
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Email del Agente", required = true)
	private String 	email;
	
	@Column(nullable = true, length=30)
	@ApiModelProperty(notes = "Telefono del Agente", required = false)
	private String 	telefono;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Tiene que Confirmar Turnos true o false", required = true)
	private Boolean confi;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Maneja API true o false", required = true)
	private Boolean mapi;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Habilita Cobranza true o false", required = true)
	private Boolean mcred;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Cantidad de slot que asigna por Turno", required = true)
	private Long 	qturn;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Duracion de los Turnos", required = true)
	private BigDecimal 	duracion;
	
	@Column(nullable = true, length=120)
	@ApiModelProperty(notes = "URL ubicacion Imagen del Agente", required = true)
	private String 	urli;

}
