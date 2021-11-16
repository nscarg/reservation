package nsc.com.operaciones.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Turno;
import nsc.com.operaciones.repositorio.ReservaRepositorio;
import nsc.com.operaciones.repositorio.TurnoRepositorio;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements  ReservaService {
	

	private final ReservaRepositorio reservaRepositorio;
	private final TurnoRepositorio turnoRepositorio;
	
	@Override
	public List<Reserva> listReserva(){
		return reservaRepositorio.findAll();
	}

	@Override
	public Reserva getReserva(Long idreserva) {
		return reservaRepositorio.findById(idreserva).orElse(null);
	}

	@Override
	public Reserva createReserva(Reserva reserva) {
		return reservaRepositorio.save(reserva);
	}

	@Override
	public Reserva updateReserva(Reserva reserva) {
		Reserva reservaDB = getReserva(reserva.getIdreserva());
		if (reservaDB == null) {
			return null;
		} else {
			return reservaRepositorio.save(reserva);
		}
	}

	@Override
	public Reserva deleteReserva(Long idreserva) {
		Reserva reservaDB = getReserva(idreserva);
		if (reservaDB == null) {
			return null;
		} else {
			reservaRepositorio.deleteById(idreserva);
			return reservaDB;
		}
	}
	
	@Override
	public List<Reserva> reservasDisponibles(Long idempresa,
											 Long idsucursal,
											 Long idn1,
											 Long idn2,
											 Long idagente,
											 Date fecha) {
		
		return reservaRepositorio.reservaDisponible(idempresa,
								 		            idsucursal,
								 		            idn1,
								 		            idn2,
								 		            idagente,
								 		            fecha);
		
	}
	
	

	
	
	@Override
	public List<Reserva> fechasDisponibles(Long idempresa,
											 Long idsucursal,
											 Long idn1,
											 Long idn2,
											 Long idagente,
											 Date fecha) {
		
		return reservaRepositorio.fechaDisponible(idempresa,
								 		          idsucursal,
								 		          idn1,
								 		          idn2,
								 		          idagente,
								 		          fecha);
		
	}

	
	
	
	
	@Override
	public  void armaReserva(	Long idempresa,
								Long idsucursal,
								Long idn1,
								Long idn2,
								Long idagente,
								Date fecha,
								Integer dia,
								BigDecimal hora,
								Long qturnos,
								Integer estado) {
		
		 Reserva registro =  Reserva.builder()
				 					.idemp(idempresa)
				 					.idsuc(idsucursal)
				 					.idn1(idn1)
				 					.idn2(idn2)
				 					.idage(idagente)
				 					.fecha(fecha)
				 					.dia(dia)
				 					.hora(hora)
				 					.qturnos(qturnos)
				 					.est(estado)
				 					.build();
				 				
		
		createReserva(registro);
		
	}

	@Override
	public Turno  reservaToma (	Long idreserva,
								Long idcliente,
								Long operador,
								String uid,
								String ip,
								String email,
								Boolean confirma) {

		
		Reserva elegido = getReserva(idreserva);
		
	 /* 1:Habilitado,
		2:No Habilitado,
		3:Completo"
	 */ 
		System.out.println("-------------------->>>");
		System.out.println(elegido);
		System.out.println(elegido.getEst());
		
	
		
		if (elegido.getEst() != 1) {
			 return null;
		} else {
			long qturnos = elegido.getQturnos()-1;
						   elegido.setQturnos(qturnos);
			

			if (qturnos <= 0) {
				elegido.setEst(3);
			} 
			
			Reserva reservaA = updateReserva(elegido);
			
			if (reservaA != null) {
				
				/* Estado Turno
				 * Estado  1:Asignado, 2:Pendiente,  4:Cancelado"
				 */
				
				Long estadoT;
				
				if (confirma){
					estadoT = 2L;
				} else {
					estadoT = 1L;
				}
				
 	
				Turno nuevoTurno = new Turno(idreserva,
											 reservaA.getIdemp(),
											 reservaA.getIdsuc(),
											 reservaA.getIdn1(),
											 reservaA.getIdn2(),
											 reservaA.getIdage(),
											 reservaA.getFecha(),
											 new Date(),
											 email,
											 idcliente,
											 operador,
											 reservaA.getHora(),
											 uid,
											 estadoT,		
											 ip);	
			
				Turno tomado = turnoRepositorio.save(nuevoTurno);

				return tomado;

				
			} else {
				return null;
			}
						
		
		}
			

	}

	@Override
	public Boolean   reservaCancelacion (Long idreserva) {
		
		boolean operacion = false;
		Reserva elegida = getReserva(idreserva);
		if (elegida != null) {
				long qturnos = elegida.getQturnos()+1;
   				elegida.setQturnos(qturnos);
   			//	Estado  1:Habilitada, 2:No Habilitada, 3:Completo"
   				elegida.setEst(1);
   				Reserva reservaA = updateReserva(elegida);
   				
   				if(reservaA != null) {
   					operacion=true;
   					
   				} else {
   					operacion=false;
   				}
   				
		} else {
			operacion=false;
			
		}

		return operacion;
	}
	
	public Boolean   eliminacionxManejo (Long idreserva) {
		
		boolean operacion = false;
		Reserva elegida = deleteReserva(idreserva);
		if (elegida != null) {
			operacion=true;;
		} else {
			operacion=false;
		}
		
		return operacion;
   			
	}
	


	
}
