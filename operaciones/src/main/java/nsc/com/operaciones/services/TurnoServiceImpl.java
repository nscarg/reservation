package nsc.com.operaciones.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Turno;
import nsc.com.operaciones.repositorio.ReservaRepositorio;
import nsc.com.operaciones.repositorio.TurnoRepositorio;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl  implements TurnoService{

	private final TurnoRepositorio 		turnoRepositorio;
	private final ReservaService 		reservaService;
	
	@Override
	public List<Turno> listTurno(){
		return turnoRepositorio.findAll();
	}

	@Override
	public Turno getTurno(Long idturno) {
		return turnoRepositorio.findById(idturno).orElse(null);
	}

	@Override
	public Turno createTurno(Turno turno) {
		return turnoRepositorio.save(turno);
	}

	@Override
	public Turno updateTurno(Turno turno) {
		Turno turnoDB = getTurno(turno.getIdturno());
		if (turnoDB == null) {
			return null;
		} else {
			return turnoRepositorio.save(turno);
		}
	}
	
	/*
	public Boolean turnoAprobado(Long  idturno) {
		Turno turnoDB = getTurno(idturno);
		if (turnoDB == null) {
			return false;
		} else {
			if (turnoRepositorio.save(turnoDB) != null) {
				return true;
			} else {
				return false;
			}
			
		}
	}
	
	*/
	

	@Override
	public Turno deleteTurno(Long idturno) {
		Turno turnoDB = getTurno(idturno);
		if (turnoDB == null) {
			return null;
		} else {
			turnoRepositorio.deleteById(idturno);
			return turnoDB;
		}
	}
	
	
	@Override
	public List<Turno> turnosAsignados(Long idempresa,
									   Long idcliente,
									   Long idsucursal,
									   Long idnivel1,
									   Long idnivel2,
									   Long idoperador) {
		
		return turnoRepositorio.turnoAsignado(	idempresa,
							 		   			idcliente,
							 		   			idsucursal,
							 		   			idnivel1,
							 		   			idnivel2,
							 		   			idoperador);
		
	}
	
	@Override
	public List<Turno>  fechasOtorgados(Long idempresa,
										Long idsucursal,
										Long idn1,
										Long idn2,
										Long idagente,
										Date fecha){
	
		return turnoRepositorio.fechasTurnos(idempresa,
											 idsucursal,
											 idn1,
											 idn2,
											 idagente,
											 fecha);
   }
	
   @Override
   public List<Turno>  otorgadosxFechas(Long idempresa,
			                           Long idsucursal,
			                           Long idn1,
			                           Long idn2,
			                           Long idagente,
			                           Date fecha){

		return turnoRepositorio.turnosOtorgados( idempresa,
												 idsucursal,
												 idn1,
												 idn2,
												 idagente,
												 fecha);
   }
   
   
   @Override
   public Boolean turnoCanceladoxManejo(Long 	idturno,
		                                String 	tipo){
	   Boolean operacion=false;
	   
	   	Turno elegido = this.getTurno(idturno);
	   	if (elegido != null) {
	   	   elegido.setEst(4L);
		   this.updateTurno(elegido);
		   operacion = true;
		   
		   if (tipo.equals("L") && 
			   elegido.getIdreserva() != 0 ) {
			   operacion = reservaService.reservaCancelacion(elegido.getIdreserva());
			   System.out.println(operacion);
		   }
		   
	   	} else {
	   		operacion=false;
	   	}
	   
	   	
	   return operacion;
   }
   
   
   
   @Override
   public Boolean  turnoCancelado(Long idcliente,
		   						  Long idreserva,
		                          Long idturno) {
	   
	   Boolean operacion=false;
	   Turno elegido = this.getTurno(idturno);

	   if (elegido != null) {		   
			   if(elegido.getIdcli() == idcliente &&
				  elegido.getIdreserva() == idreserva) {
				  // verifico que el turno corresponde al cliente y la reserva
				   elegido.setEst(4L);
				   this.updateTurno(elegido);
				   operacion=true;
				   if(idreserva != 0) {
					   if (reservaService.reservaCancelacion(idreserva)){
						   operacion=true;
					   }
				   }   
			   } else {
				   operacion=false;
			   }
  
	   } else {
		   operacion=false;
	   }
	   
	 return operacion;
	   
	   
   }
   
   
	public List<Turno>   turnosPendiente( Long idemp){
		return turnoRepositorio.pendientesTurno(idemp);
	}
	
	public Long   totalPendientes	( Long idemp) {
		return turnoRepositorio.totalTPendientes(idemp);
	};
	
	public Boolean turnoAprobado (Long idturno){
		  
		  Boolean operacion	= false;
		  Turno   elegido 	= this.getTurno(idturno);
		  
		  if (elegido !=null){
			  elegido.setEst(1L);
			  this.updateTurno(elegido);
			  operacion=true;
	      } else {
	    	  operacion=false;
	      }
   
		return operacion;  
	}
}
