package nsc.com.operaciones.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsc.com.operaciones.entity.Cliente;
import nsc.com.operaciones.entity.Turno;


@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Envio_Turno {
	
	
		

		public Envio_Turno (  Long 		idreserva,
						      Long 		idemp,
						      Long 		idsuc,
						      Long 		idn1,
						      Long 		idn2,
						      Long 		idage,
						      String 	fecha,
						      String 	fechatoma,
						      String 	generado,
						      Long 		idcli,
						      Long 		iduser,
						      BigDecimal 	hora,
						      String 	uidturno,
						      Long 		est,
			                  String 	ip,
			                  Boolean   envio) {
					
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
			this.envio     = envio;
		}
		
		private Long 		idturno;
		private Long 		idreserva;
		private Long 		idemp;
		private Long 		idsuc;
		private Long 		idn1;
		private Long 		idn2;
		private Long 		idage;
		private String 		fecha;
		private String 		fechatoma;
		private String 		generado;
		private Long 		idcli;
		private Long 		iduser;
		private BigDecimal 	hora;
		private String 		uidturno;
		private Long  		est;
		private String 		ip;
		private Boolean 	envio;

		public Turno conversor(Envio_Turno envio) {
		 try {	
			Date fecha   	= new SimpleDateFormat("yyyy-MM-dd").parse(envio.getFecha());
		    Date fechatoma  = new SimpleDateFormat("yyyy-MM-dd").parse(envio.getFechatoma());
			
			Turno t = new Turno(envio.idreserva,
							envio.idemp,
							envio.idsuc,
							envio.idn1,
							envio.idn2,
							envio.idage,
							fecha,
							fechatoma,
							envio.generado,
							envio.idcli,
							envio.iduser,
							envio.hora,
							envio.uidturno,
							envio.est,
							envio.ip);
			
			   return t;
		 } catch (Exception e) {
		    return null; 
		 }

			
		}
		

		
		
}
