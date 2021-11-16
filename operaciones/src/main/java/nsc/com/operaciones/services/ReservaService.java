package nsc.com.operaciones.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import nsc.com.operaciones.entity.N2;
import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Turno;

public interface ReservaService {
	
	public List<Reserva> 	listReserva();
	public Reserva   		getReserva(Long idreserva);
	public Reserva 	  		createReserva(Reserva reserva);
	public Reserva       	updateReserva(Reserva reserva);
	public Reserva       	deleteReserva(Long idreserva);
	public void				armaReserva(	Long idempresa,
											Long idsucursal,
											Long idn1,
											Long idn2,
											Long idagente,
											Date fecha,
											Integer dia,
											BigDecimal hora,
											Long qturnos,
											Integer estado);
	
	public List<Reserva>   reservasDisponibles(	Long idempresa,
											   	Long idsucursal,
											   	Long idn1,
											   	Long idn2,
											   	Long idagente,
											   	Date fecha);	
										
										
	public List<Reserva>   fechasDisponibles(	Long idempresa,
			   									Long idsucursal,
			   									Long idn1,
			   									Long idn2,																																
			   									Long idagente,
			   									Date fecha);
	

	
	public Turno  reservaToma(	Long idreserva,
					    		Long idcliente,
								Long idagente,
								String uid,																							
								String ip,																										
								String email,
								Boolean confirma);
	
		
	public Boolean  reservaCancelacion (Long idreserva);
	
	public Boolean  eliminacionxManejo (Long idreserva);
	
}
