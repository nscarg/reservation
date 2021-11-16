package nsc.com.operaciones.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nsc.com.operaciones.entity.Agente;
import nsc.com.operaciones.entity.Cliente;
import nsc.com.operaciones.entity.Empresa;
import nsc.com.operaciones.entity.Feriado;
import nsc.com.operaciones.entity.Hora;
import nsc.com.operaciones.entity.N1;
import nsc.com.operaciones.entity.N2;
import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.entity.Sucursal;
import nsc.com.operaciones.entity.Turno;
import nsc.com.operaciones.services.AWSService;
import nsc.com.operaciones.services.AgenteService;
import nsc.com.operaciones.services.EmpresaService;
import nsc.com.operaciones.services.FeriadoService;
import nsc.com.operaciones.services.HoraService;
import nsc.com.operaciones.services.N1Service;
import nsc.com.operaciones.services.N2Service;
import nsc.com.operaciones.services.ReservaService;
import nsc.com.operaciones.services.SucursalService;
import nsc.com.utilidades.Fechas;


@RestController
@RequestMapping("/horas")
public class HoraController {

	
	@Autowired
	private EmpresaService 	empresaService;
	@Autowired
	private SucursalService sucursalService;
	@Autowired
	private N1Service       n1Service;
	@Autowired
	private N2Service       n2Service;
	@Autowired
	private AgenteService   agenteService;
	@Autowired
	private FeriadoService  feriadoService;
	@Autowired
	private HoraService  	horaService;
	@Autowired
	private ReservaService  reservaService;
	
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@PostMapping(value = "/alta")
    public ResponseEntity<Hora> altaHora(@Valid @RequestBody Hora hora, BindingResult result)  {
		
		try {

    		if (result.hasErrors()){     
    			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
	         } else {
	        	 Hora horaDB = horaService.createHora(hora);
	        	 return  ResponseEntity.status( HttpStatus.CREATED).body(horaDB);    	
	         }
    		
		} catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/eliminacionHoras")
	public Boolean cancelacionxCliente(@RequestParam("marcas_id") String[] marcas){
		Boolean operacion=false;
		Boolean resultado= false;
		for (int r=0; r < marcas.length; r++) {
			resultado = horaService.eliminacion(Long.parseLong(marcas[r]));
			System.out.println(Long.parseLong(marcas[r]));
			System.out.println(resultado);
			if (resultado){
				operacion=true;
			} else {
				operacion=false;
				break;
			}

		}

	    return operacion;
		
		
	}
	
	
	

	@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    @PutMapping(value = "/actualiza")
    public ResponseEntity<?> actualizaHora(@Valid @RequestBody Hora hora) {
		
		        if ( null == hora ) {
		            return  ResponseEntity.notFound().build();
		        }
		
		        		hora=horaService.updateHora(hora);
		   
		        return  ResponseEntity.ok(hora);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/disparaGeneracion")
	public void  inicioProceso( @RequestParam("empresa_id") Long    empresa_id,
								@RequestParam("desde_id")   String  desde_id ){	
		try {
		
				int ps,pn1,pn2,pa;
				Integer estado=1;
				
				Empresa empresas = empresaService.getEmpresa(empresa_id);
				Integer qdias 	 = empresas.getQdias();
				
				
    				
					List<Sucursal> sucursales  = sucursalService.cargaPOP(empresas.getIdemp());
					
					for (int s=0 ; s <sucursales.size(); s++){	
						ps=s;
						List<N1> 	   niveles1  = n1Service.cargaPOP(empresas.getIdemp(),
                            									  	  sucursales.get(s).getIdsuc());
					
							for (int n1=0 ; n1 <niveles1.size(); n1++){	
								pn1=n1;
								List<N2> 	   niveles2  = n2Service.cargaPOP(empresas.getIdemp(),
										  									  sucursales.get(ps).getIdsuc(),
										  									  niveles1.get(pn1).getIdn1());
								
								for (int n2=0 ; n2 <niveles2.size(); n2++){	
									pn2=n2;
								
									List<Agente> 	   agentes  = agenteService.cargaPOP(empresas.getIdemp(),
											  											 sucursales.get(ps).getIdsuc(),
											  											 niveles1.get(pn1).getIdn1(),
											  											 niveles2.get(pn2).getIdn2());
		
										for (int a=0 ; a <agentes.size(); a++){	
											pa=a;
											SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
											Date             startDate  = formatter.parse(desde_id);									
											Calendar 	calendar = Calendar.getInstance();
							      						calendar.setTime(startDate); 					
							      						calendar.add(Calendar.DAY_OF_YEAR, qdias); 	
							      						
							      			Date endDate   = calendar.getTime();
											Calendar start = Calendar.getInstance();
											start.setTime(startDate);

											Calendar end   = Calendar.getInstance();
											end.setTime(endDate);		
										
											// Armado Reservas
											for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
										
												if (feriadoService.buscoxPais(empresas.getPais(), date) == null){
													
													 GregorianCalendar fechaCalendario = new GregorianCalendar();
													 fechaCalendario.setTime(date);
													 Integer diaSemana = fechaCalendario.get(Calendar.DAY_OF_WEEK);
													
						
														List<Hora> 	   horarios  = horaService.armadoHorario(	empresas.getIdemp(),
					  											 												sucursales.get(ps).getIdsuc(),
					  											 												niveles1.get(pn1).getIdn1(),
					  											 												niveles2.get(pn2).getIdn2(),
					  											 												agentes.get(pa).getIdagente(),
					  											 												diaSemana);
								
													
														for (Integer hora=0; hora < horarios.size();hora++){

																														
															reservaService.armaReserva(	empresas.getIdemp(),
						 																sucursales.get(ps).getIdsuc(),
						 																niveles1.get(pn1).getIdn1(),
						 																niveles2.get(pn2).getIdn2(),
						 																agentes.get(pa).getIdagente(),
						 																date,
						 																diaSemana,
						 																horarios.get(hora).getHora(),
						 																horarios.get(hora).getQturnos(),
						 																estado);
																
														}
													
													 
													 
													 
												}
												
											   
											}
											
											
										} // Agente
								} // Nivel 2	
							} // Nivel 1 
					} // Sucursales
	
		} catch(Exception e){
			System.out.println(e);
		}
	}


	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/horasxDia")
	public  ResponseEntity <List<Hora>> horas(  @RequestParam("empresa_id") 	Long    empresa_id,
										 		@RequestParam("sucursal_id")   	Long    sucursal_id,
										 		@RequestParam("nivel1_id")   	Long    nivel1_id,
										 		@RequestParam("nivel2_id")   	Long    nivel2_id,
										 		@RequestParam("operador_id")   	Long    operador_id,
										 		@RequestParam("nrodia_id")		Integer nrodia_id){	
		try {
		
			@SuppressWarnings("rawtypes")
			List<Hora> casos = 	  horaService.horaDia(empresa_id,
									 				  sucursal_id,
													  nivel1_id,
													  nivel2_id,
													  operador_id,
													  nrodia_id);
			   
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



  


	private Date sumarRestarDiasFecha(Date fechaInicio, Long qdias) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
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
