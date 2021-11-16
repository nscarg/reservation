package nsc.com.operaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nsc.com.operaciones.entity.Hora;

public interface HoraRepositorio extends JpaRepository<Hora, Long> {
	
	@Query("SELECT e from Hora e WHERE e.idemp =  ?1 AND e.idsuc = ?2 AND e.idn1 = ?3 AND e.idn2 = ?4 AND e.idage = ?5 AND e.dia = ?6 ORDER BY e.hora ASC")
	public List<Hora> generacion(Long idemp, 
			                       Long idsuc,
			                       Long idn1,
			                       Long idn2,
			                       Long idage,
			                       Integer dia);	
	

	@Query("SELECT e from Hora e WHERE e.idemp =  ?1 AND e.idsuc = ?2 AND e.idn1 = ?3 AND e.idn2 = ?4 AND e.idage = ?5 AND e.dia = ?6 ORDER BY e.hora ASC")
	public List<Hora> horasxDia(	Long 	idemp, 
			                       	Long 	idsuc,
			                       	Long 	idn1,
			                       	Long 	idn2,
			                       	Long 	idage,
			                       	Integer dia);		
}
