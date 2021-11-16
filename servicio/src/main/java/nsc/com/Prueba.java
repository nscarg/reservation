package nsc.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
public class Prueba {
	
	private Integer puerto;
	
	Prueba(Integer puerto) {
		this.puerto = puerto;
	}
	
	public Integer leerMarca() {
		return puerto;
	}
	


}
