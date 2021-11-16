package nsc.com.operaciones.entity;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long 	   iduser;
	
	
	
	@ApiModelProperty(notes = "Id Empresa",required = true)
	@Column(nullable = false)
	private Long 	   idemp;
	
	@ManyToOne
	@JoinColumn(name ="idemp", insertable = false,updatable = false)
	private Empresa    empresa;
	
	
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Email del Usuario",required = true)
	private String 	   email;
	
	
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Apellido y Nombre del Usuario",required = true)
	private String 	   ape;
	
	@Column(nullable = false, length=20)
	@ApiModelProperty(notes = "Telefono del Usuario",required = true)
	private String 	   tel;

	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Id Sucursal", required = true)
	private Long 	idsuc;
	
	@ManyToOne
	@JoinColumn(name = "idsuc", nullable = false, insertable = false, updatable = false)
	private Sucursal sucursal;
	
	
	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Id Nivel 1", required = true)
	private Long 	idn1;
	
	@ManyToOne
	@JoinColumn(name = "idn1", nullable = false, insertable = false, updatable = false)
	private N1 n1;
	
	
	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Id Nivel 2", required = true)
	private Long 	idn2;
	
	@ManyToOne
	@JoinColumn(name = "idn2", nullable = false, insertable = false, updatable = false)
	private N2 n2;
	
	@Column(nullable = false, length=6)
	@ApiModelProperty(notes = "Id Agente", required = true)
	private Long 	idagente;
	
	@ManyToOne
	@JoinColumn(name = "idagente", nullable = false, insertable = false, updatable = false)
	private Agente agente;
	
	@Column(nullable = false, length=1)
	@ApiModelProperty(notes = "Estado  H:Habilitado ,A:Anulado ", required = true)
	private String 	est;
	
	@Column(nullable = false, length=15)
	@ApiModelProperty(notes = "Password", required = true)
	private String 	passw;
	
	
	

}
