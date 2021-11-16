package nsc.com.operaciones.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nsc.com.operaciones.controller.Envio_Turno;
import nsc.com.operaciones.entity.Cliente;
import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Turno;
import nsc.com.operaciones.services.ReservaService;
import nsc.com.operaciones.services.TurnoService;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
	@Autowired
	private TurnoService turnoService;
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/cliente")
	public ResponseEntity <List<Turno>> turnoCliente(	@RequestParam("empresa_id")   Long empresa_id,
											  			@RequestParam("cliente_id")   Long cliente_id,
											  			@RequestParam("sucursal_id")  Long sucursal_id,
											  			@RequestParam("nivel1_id")    Long nivel1_id,
											  			@RequestParam("nivel2_id")    Long nivel2_id,
											  			@RequestParam("operador_id")  Long operador_id){

		@SuppressWarnings("rawtypes")
		List<Turno> turnos = turnoService.turnosAsignados(	empresa_id,
													  		cliente_id,
													  		sucursal_id,
													  		nivel1_id,
													  		nivel2_id,
													  		operador_id);

		if (turnos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(turnos);
		}
		
		
	}


	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@PostMapping(value = "/toma_SobreTurno")
    public   ResponseEntity<Turno>  cargaTurno(@Valid @RequestBody Envio_Turno e_turno, BindingResult result)  {

		try {
		

			Turno turno = e_turno.conversor(e_turno);
	
		

    		if (result.hasErrors()){     
    			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
	         } else {
	        	 Turno turnoDB = turnoService.createTurno(turno);
	        	 return  ResponseEntity.status( HttpStatus.CREATED).body(turnoDB);    	
	         }
    		
		} catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
    }
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/turnosNoAprobados")
	public Boolean noAprobados(@RequestParam("marcas_id") String[] marcas){
		Boolean operacion=false;
	
		for (int r=0; r < marcas.length; r++) {

			if (turnoService.turnoCanceladoxManejo(Long.parseLong(marcas[r]),
				                                   "L")){
				operacion=true;	
			} else {
				operacion=false;
				break;
			}

		}
	    return operacion;
	}
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/turnosAprobados")
	public Boolean aprobados(@RequestParam("marcas_id") String[] marcas){
		Boolean operacion=false;
	
		for (int r=0; r < marcas.length; r++) {

			if (turnoService.turnoAprobado(Long.parseLong(marcas[r]))){
				operacion=true;
			} else {
				operacion=false;
				break;
			}

		}
	    return operacion;
	}
	

	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/cancelacionesxcliente")
	public Boolean cancelacionxCliente(	@RequestParam("tipo_id") String tipo,
									    @RequestParam("marcas_id") String[] marcas){

		
		
		Boolean operacion=true;
	
		for (int r=0; r < marcas.length; r++) {

			if (turnoService.turnoCanceladoxManejo(Long.parseLong(marcas[r]),
					                               tipo)){
				
			} else {
				operacion=false;
				break;
			}

		}

	    return operacion;
		
		
	}
	

	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/cancelacionxcliente")
	public Boolean cancelacionxCliente( @RequestParam("cliente_id")    Long cliente_id,
										@RequestParam("reserva_id")    Long reserva_id,
										@RequestParam("turno_id")  	   Long turno_id){
		
		Boolean operacion = false;
			if (turnoService.turnoCancelado(cliente_id,reserva_id,turno_id)) {
				operacion=true;
			} else {
				operacion=false;
			}

	
	    return operacion;
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/otorgados")
	public ResponseEntity <List<Turno>>  otorgadosxFecha(@RequestParam("idemp_id")  Long idemp_id,
								   				 		 @RequestParam("idsuc_id")  Long idsuc_id,
								   				 		 @RequestParam("idn1_id")   Long idn1_id,
								   				 		 @RequestParam("idn2_id")   Long idn2_id,
								   				 		 @RequestParam("idage_id")  Long idage_id,
								   				 		 @RequestParam("fecha_id")  String fecha_id){
		System.out.println(fecha_id);
		
		try{
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
			Date fecha = (Date)formato.parse(fecha_id);
			System.out.println(fecha);
			
			@SuppressWarnings("rawtypes")
			List<Turno> casos = 	  turnoService.otorgadosxFechas(idemp_id,
									 					  		   idsuc_id,
																   idn1_id,
																   idn2_id,
																   idage_id,
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
	@GetMapping(value="/fechas")
	public ResponseEntity <List<Turno>>  navegacionxFecha(	@RequestParam("empresa_id")  Long empresa_id,
		   				 		  							@RequestParam("sucursal_id") Long sucursal_id,
		   				 		  							@RequestParam("nivel1_id")   Long nivel1_id,
		   				 		  							@RequestParam("nivel2_id")   Long nivel2_id,
		   				 		  							@RequestParam("agente_id")   Long agente_id,
		   				 		  							@RequestParam("fecha_id")    String fecha_id){
	
		
		try{
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
			Date fecha = (Date)formato.parse(fecha_id);
			System.out.println(fecha);
			
		
			@SuppressWarnings("rawtypes")
			List<Turno> casos = 	  turnoService.fechasOtorgados(empresa_id,
									 					  		   sucursal_id,
																   nivel1_id,
																   nivel2_id,
																   agente_id,
																   fecha);
																   
																   
			System.out.println(casos);										
            
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
	@GetMapping(value="/pendientes")
	public ResponseEntity <List<Turno>>  turnosPendientes(@RequestParam("empresa_id") Long empresa_id){

		try {
	
			@SuppressWarnings("rawtypes")
			List<Turno> casos = 	turnoService.turnosPendiente(empresa_id);
			
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
	@GetMapping(value="/totalPendientes")
	public Long qpendientes(@RequestParam("empresa_id") Long empresa_id){

		try {
	
			@SuppressWarnings("rawtypes")
			Long casos = turnoService.totalPendientes(empresa_id);
			System.out.println(casos);
			return casos;
			
		} catch (Exception e) { //error handling code
			System.out.println(e);
			return null;
		}	
	
	}
	
/*	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/apruebaTurnos")
	public Boolean aprobacion(@RequestParam("turno_id") Long turno_id){
	
		try {
			return  turnoService.turnoAprobado(turno_id);

		} catch (Exception e) { //error handling code
			System.out.println(e);
			return false;
		}	
	
	}
*/
	
	

    
    private String formatMessage( BindingResult result){
 	
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                                        error.put(err.getField(), err.getDefaultMessage());
                                        return error;

                }).collect(Collectors.toList());
        			ErrorMessage errorMessage = ErrorMessage.builder()
        					.code("01")
        					.messages(errors).build();
        					ObjectMapper mapper = new ObjectMapper();
        					String jsonString="";
       
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
        return jsonString;
   }
    
	
	
}
