package nsc.com.operaciones.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="reserva")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Reserva {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	idreserva;
	
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Id Empresa", required = true)
	private Long 	idemp;
	
	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Id Sucursal", required = true)
	private Long 	idsuc;
	
	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Id Nivel 1", required = true)
	private Long 	idn1;
	
	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Id Nivel 2", required = true)
	private Long 	idn2;
	
	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Id Agente", required = true)
	private long 	idage;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Fecha Reserva", required = true)
	@Temporal(TemporalType.DATE)
	private Date 	fecha;
	
	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Dia de la Semana", required = true)
	private Integer dia;
	

	@ApiModelProperty(notes = "Hora xx.xx",required = true)
	@Column(nullable = false,  precision = 4, scale = 2)
	private BigDecimal  hora;
	
	@ApiModelProperty(notes = "Cantidad Turnos Disponibles",required = true)
	@Column(nullable = false,  length=4)
	private Long  qturnos;
	
	@ApiModelProperty(notes = "Estado  1:Habilitada,  3:Completa",required = true)
	@Column(nullable = false,  length=1)
	private Integer  est;
	
	
}
