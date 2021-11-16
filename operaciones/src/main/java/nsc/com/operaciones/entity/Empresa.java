package nsc.com.operaciones.entity;


import java.util.Date;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="empresa")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Empresa {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 		idemp;
	
	@Column(nullable = false )
	@ApiModelProperty(notes = "Tipo de Actividad", required = true)
	private Long		ida; 
	
	@Column(nullable = false )
	@ApiModelProperty(notes = "Manejo de Niveles 0-5", required = true)
	private Long		nivel; 
		
	@Column(nullable = false )
	@ApiModelProperty(notes = "Manejo de Agentes", required = true)
	private Boolean		oper;
	
	@Column(nullable = false )
	@ApiModelProperty(notes = "Cantidad de Slots por Turno", required = true)
	private Long	   qturn;
	
	@Column(nullable = false )
	@ApiModelProperty(notes = "URL Imagen", required = true)
	private String	   urli;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Confirma la Suscripcion",required = true)
	private Boolean		confsus; 
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Confirma Turno",required = true)
	private Boolean		confi;  
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Almacena Documentacion",required = true)
	private Boolean		minfo;
	
	
	@Column(nullable = true )
	@ApiModelProperty(notes = "Aclaracion Documentacion", required = false)
	private String	   aclaracarga;
	
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Maneja API",required = true)
	private Boolean 	mapi;    
			
	@Column(nullable = false)
	@ApiModelProperty(notes = "Fecha Ultima Reserva Corrida",required = true)
	private Date 		ufhab; 
	
	@Column(nullable = false )
	@ApiModelProperty(notes = "Cantidad Dias que Habilita", required =true)
	private	Integer	   qdias;
	
	@Column(nullable = false )
	@ApiModelProperty(notes = "Codigo de Pais", required = true)
	private Long	   pais;
	
	@Column(nullable = false )
	@ApiModelProperty(notes = "Hora Proceso Generacion", required = true)
	private Integer	   horapro;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Maneja Mensajeria",required = true)
	private Boolean 	msjes;    
	
	@Column(nullable = true )
	@ApiModelProperty(notes = "Denominacion Empresa", required = false)
	private String	   deno;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Habilita Otras formas de Login",required = true)
	private Boolean 	login;  
	
	
}
