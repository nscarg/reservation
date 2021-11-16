package nsc.com.operaciones.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Turno;
import nsc.com.operaciones.services.ReservaService;

@RestController
@RequestMapping("/reservas")

public class ReservaController {
	@Autowired
	private ReservaService reservaService;
	
	
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/horariosDisponibles")
	public ResponseEntity <List<Reserva>> muestraHorario(	@RequestParam("empresa_id")  Long empresa_id,
												  			@RequestParam("sucursal_id") Long sucursal_id,
												  			@RequestParam("nivel1_id")   Long nivel1_id,
												  			@RequestParam("nivel2_id")   Long nivel2_id,
												  			@RequestParam("agente_id")   Long agente_id,
												  			@RequestParam("fecha_id")    String fecha_id){
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		try {
				fecha = sd.parse(fecha_id);
			
	
			@SuppressWarnings("rawtypes")
			List<Reserva> reservas = reservaService.reservasDisponibles(empresa_id,
																		sucursal_id,
																		nivel1_id,
																		nivel2_id,
																		agente_id,
																		fecha);
	
			if (reservas.isEmpty()) {
				return ResponseEntity.noContent().build();
			} 
			
			return ResponseEntity.ok(reservas);
		} catch (ParseException e) {
				// TODO Auto-generated catch block
			return ResponseEntity.noContent().build();
		}
	}
	
	
	


	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/fechasDisponibles")
	public ResponseEntity <List<Reserva>> muestraFechas(@RequestParam("empresa_id")  Long empresa_id,
											  			@RequestParam("sucursal_id") Long sucursal_id,
											  			@RequestParam("nivel1_id")   Long nivel1_id,
											  			@RequestParam("nivel2_id")   Long nivel2_id,
											  			@RequestParam("agente_id")   Long agente_id,
											  			@RequestParam("fecha_id")    String fecha_id){
		

		try {
			
	
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
			Date fecha = (Date)formato.parse(fecha_id);
			System.out.println(fecha);
	
			@SuppressWarnings("rawtypes")
			List<Reserva> casos = 	reservaService.fechasDisponibles(empresa_id,
									 					  		  	 sucursal_id,
																	 nivel1_id,
																	 nivel2_id,
																	 agente_id,
																	 fecha);
																	
			
			
			if (casos.isEmpty()) {
				return ResponseEntity.noContent().build();
			} 
			
			return ResponseEntity.ok(casos);
			
		} catch (Exception e) { //error handling code
			System.out.println(e);
			return ResponseEntity.noContent().build();
		}	
	}
	
	
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/tomarReservas")
	public ResponseEntity<Turno>  tomarReserva(	@RequestParam("reserva_id") 		Long reserva_id,
									@RequestParam("cliente_id") 		Long cliente_id,
									@RequestParam("agente_id")  		Long agente_id,
									@RequestParam("uid_id")     		String uid_id,
									@RequestParam("ip_id")      		String ip_id,
									@RequestParam("email_id")   		String email_id,
									@RequestParam("confirma_id") 		Boolean confirma_id){
		
		
		
		//	@SuppressWarnings("rawtypes")
		   
			Turno tomado= reservaService.reservaToma(	reserva_id,
						   						cliente_id,
												agente_id,
												uid_id,
												ip_id,
												email_id,
										        confirma_id);
			
			
			
			if(tomado == null) {
				return ResponseEntity.noContent().build();
		
			} else {
				return new ResponseEntity<Turno>(tomado, HttpStatus.OK);
			}
		
		
	}
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/analisisReserva")
	public ResponseEntity<Reserva>  tomarReserva(@RequestParam("reserva_id") Long reserva_id){
		
		    @SuppressWarnings("rawtypes")
			Reserva reserva = reservaService.getReserva(reserva_id);
			
			if(reserva == null) {
				return new ResponseEntity(HttpStatus.CONFLICT);
		
			} else {
				return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
			}
		
		
	}

	
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/eliminacionReservas")
	public Boolean cancelacionxCliente(@RequestParam("marcas_id") String[] marcas){
		Boolean operacion=true;
		for (int r=0; r < marcas.length; r++) {
			if (reservaService.eliminacionxManejo(Long.parseLong(marcas[r]))){
				
			} else {
				operacion=false;
				break;
			}

		}

	    return operacion;
		
		
	}
	
	

	
	
	


	
	
	
}
