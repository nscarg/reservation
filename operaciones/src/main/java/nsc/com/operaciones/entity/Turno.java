package nsc.com.operaciones.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="turno")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Turno {
	

	public Turno (Long idreserva,
			      Long idemp,
			      Long idsuc,
			      Long idn1,
			      Long idn2,
			      Long idage,
			      Date fecha,
			      Date fechatoma,
			      String generado,
			      Long idcli,
			      Long iduser,
			      BigDecimal hora,
			      String uidturno,
			      Long est,
                  String ip) {
		
		this.idreserva = idreserva;
		this.idemp     = idemp;
		this.idsuc     = idsuc;
		this.idn1      = idn1;
		this.idn2      = idn2;
		this.idage     = idage;
		this.fecha     = fecha;
		this.fechatoma = fechatoma;
		this.generado  = generado;
		this.idcli     = idcli;
		this.iduser    = iduser;
		this.hora      = hora;
		this.uidturno  = uidturno;
		this.est       = est;
		this.ip        = ip;
				
	}

	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	idturno;
	
	
	@ApiModelProperty(notes = "Id Reserva",required = true)
	@Column(nullable = false)
	private Long 	   idreserva;
	
	
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
	private Long 	idage;
		
	
		
	@Column(nullable = false)
	@ApiModelProperty(notes = "Fecha Reserva", required = true)
	@Temporal(TemporalType.DATE)
	private Date 	fecha;

	@Column(nullable = false)
	@ApiModelProperty(notes = "Fecha Toma", required = true)
	@Temporal(TemporalType.DATE)
	private Date 	fechatoma;
	
	@Column(nullable = true)
	@ApiModelProperty(notes = "generado", required = false)
	private String 	generado;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Id Cliente", required = true)
	private Long 	idcli;
	
	@ManyToOne
	@JoinColumn(name = "idcli", nullable = false, insertable = false, updatable = false)
	private Cliente cliente;
	
	@Column(nullable = true)
	@ApiModelProperty(notes = "iduser", required = false)
	private Long iduser;
	
	@ApiModelProperty(notes = "Hora xx.xx",required = true)
	@Column(nullable = false, precision = 4, scale = 2)
	private BigDecimal hora;

	@Column(nullable = false, length=15)
	@ApiModelProperty(notes = "UID Turno", required = true)
	private String 	uidturno;
	
	
	@ApiModelProperty(notes = "Estado  1:Asignado, 2:Pendiente, 4:Cancelado",required = true)
	@Column(nullable = false,  length=1)
	private Long  est;
	
	@Column(nullable = true)
	@ApiModelProperty(notes = "ip", required = false)
	private String 	ip;
	
	
}
