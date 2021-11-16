package nsc.com.operaciones.services;

import java.util.List;
import nsc.com.operaciones.entity.Hora;

public interface HoraService {
	public List<Hora> listAllHora();
	
	public Hora   	  getHora(Long id);
	
	public Hora 	  createHora(Hora hora);
	
	public Hora       updateHora(Hora hora);
	
	public Hora       deleteHora(Long id);
	
	public List<Hora> armadoHorario(Long idempresa,
						 	        Long idsuc,
								    Long idn1,
								    Long idn2,
								    Long age,
								    Integer dia);
	
	public List<Hora> horaDia(	Long idempresa,
 	        					Long idsuc,
 	        					Long idn1,
 	        					Long idn2,
 	        					Long age,
 	        					Integer dia);

	public Boolean  eliminacion (Long id);
}
