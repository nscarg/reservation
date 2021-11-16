package nsc.com.operaciones.repositorio;

	import java.util.Date;
import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;

import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Turno;

	public interface TurnoRepositorio extends  JpaRepository<Turno, Long>{
		
		@Query("SELECT e from Turno e WHERE e.idemp =  ?1 AND e.idcli = ?2 AND e.idsuc = ?3 AND e.idn1 = ?4 AND e.idn2 = ?5 AND e.idage = ?6 AND e.est=2 ORDER BY e.fecha , e.hora ASC")
		public List<Turno> turnoAsignado(Long idemp,
				                         Long idcli,
				                         Long idsuc,
				                         Long idn1,
				                         Long idn2,
				                         Long idope);

		@Query("SELECT e from Turno e WHERE e.idemp = ?1 AND e.idsuc = ?2 AND e.idn1 = ?3 AND e.idn2 = ?4 AND e.idage = ?5 AND e.fecha = ?6 AND e.est=1 ORDER BY  e.hora ASC")
		public List<Turno> turnosOtorgados(Long idempresa,
									 	   Long idsucursal,
									 	   Long idn1,
									 	   Long idn2,
									 	   Long idagente,
									 	   Date fecha);
		
		@Query("SELECT e , count(e.idturno) from Turno e WHERE e.idemp =  ?1 AND e.idsuc = ?2 AND e.idn1 = ?3 AND e.idn2 = ?4 AND e.idage = ?5 AND e.fecha >= ?6 AND e.est=1 GROUP BY e.fecha ORDER BY e.fecha ASC")
		public List<Turno> fechasTurnos(Long idemp, Long idsuc, Long n1,Long n2,Long agente,Date Fecha);	
		
		@Query("SELECT e  from Turno e WHERE e.idemp =  ?1  AND e.est=2 ORDER BY e.fecha ASC")
		public List<Turno> pendientesTurno(Long idemp);	
		
		@Query("SELECT count(e.idturno) from Turno e WHERE e.idemp =  ?1 AND e.est = 2 GROUP BY e.idreserva")
		public Long totalTPendientes(Long idemp);		
		
		
		
		
	}

