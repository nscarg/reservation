package nsc.com.operaciones.services;

import java.util.Date;
import java.util.List;

import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Turno;

public interface TurnoService {
	public List<Turno> 	listTurno();
	public Turno   		getTurno(Long idturno);
	public Turno 	  	createTurno(Turno turno);
	public Turno       	updateTurno(Turno turno);
	public Turno       	deleteTurno(Long idturno);
	
	public List<Turno>  fechasOtorgados(Long idempresa,
			    					   Long idsucursal,
			    					   Long idn1,
			    						Long idn2,
			    						Long idagente,
			    						Date fecha);
	
	
	public List<Turno>  otorgadosxFechas(Long idempresa,
										 Long idsucursal,
										 Long idn1,
										 Long idn2,
										 Long idagente,
										 Date fecha);
	
	public List<Turno>  turnosAsignados(Long idempresa,
			   							Long idcliente,
			   							Long idsucursal,
			   							Long idnivel1,
			   							Long idnivel2,
			   							Long idoperador);
	
	public Boolean 		turnoCancelado(Long cliente,
			                           Long idreserva,
			                           Long idturno);
	
	public Boolean      turnoCanceladoxManejo(Long 	 idturno,
			                                  String Tipo);
	
	public List<Turno>  turnosPendiente(Long idemp);
	
	public Long   		totalPendientes(Long idemp);
	
	public Boolean      turnoAprobado(Long idturno);
	
}
