package nsc.com.operaciones.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nsc.com.operaciones.entity.N2;
import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Turno;

public interface ReservaRepositorio extends  JpaRepository<Reserva, Long>{
	
	@Query("SELECT e from Reserva e WHERE e.idemp =  ?1 AND e.idsuc = ?2 AND e.idn1 = ?3 AND e.idn2 = ?4 AND e.idage = ?5 AND e.est=1 AND e.fecha = ?6 ORDER BY e.hora ASC")
	public List<Reserva> reservaDisponible(Long idemp, Long idsuc, Long n1,Long n2,Long agente, Date fecha);
	
	@Query("SELECT e , count(e.idreserva) from Reserva e WHERE e.idemp =  ?1 AND e.idsuc = ?2 AND e.idn1 = ?3 AND e.idn2 = ?4 AND e.idage = ?5 AND e.fecha >= ?6 AND e.est=1 GROUP BY e.fecha ORDER BY e.fecha ASC")
	public List<Reserva> fechaDisponible(Long idemp, Long idsuc, Long n1,Long n2,Long agente,Date Fecha);		
	
	

}
