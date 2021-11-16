package nsc.com.operaciones.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
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

import nsc.com.operaciones.entity.Cliente;
import nsc.com.operaciones.entity.Turno;
import nsc.com.operaciones.services.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;


	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/loginxMail")
	public ResponseEntity <List<Cliente>> xMail(@RequestParam("empresa_id") Long empresa_id,
			                                    @RequestParam("email_id")   String email_id){
		
		@SuppressWarnings("rawtypes")
		List clientes = clienteService.loginxMail(empresa_id, email_id);
		
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.ok(clientes);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/loginxTelefono")
	public ResponseEntity <List<Cliente>> xTelefono(@RequestParam("empresa_id")  Long   empresa_id,
													@RequestParam("documento_id") String documento_id,
			                                        @RequestParam("telefono_id") String telefono_id){
		
		@SuppressWarnings("rawtypes")
		List clientes = clienteService.loginxTelefono(empresa_id, documento_id, telefono_id);
		
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.ok(clientes);
	}
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/loginxTwitter")
	public ResponseEntity <List<Cliente>> xTwitter(@RequestParam("empresa_id")  Long   empresa_id,
			                                       @RequestParam("username_id") String username_id){
		
		@SuppressWarnings("rawtypes")
		List clientes = clienteService.loginxTwitter(empresa_id, username_id);
		
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.ok(clientes);
	}
	
	 


	@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    @PutMapping(value = "/actualiza")
    public ResponseEntity<?> actualizaCliente(@Valid @RequestBody Cliente cliente) {
		
		        if ( null == cliente ) {
		            return  ResponseEntity.notFound().build();
		        }
		
		        cliente=clienteService.updateCliente(cliente);
		   
		        return  ResponseEntity.ok(cliente);

		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@PostMapping(value = "/alta")
    public ResponseEntity<Cliente> altaCliente(@Valid @RequestBody Cliente cliente, BindingResult result)  {
		
		try {

    		if (result.hasErrors()){     
    			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
	         } else {
	        	 Cliente clienteDB = clienteService.createCliente(cliente);
	        	 return  ResponseEntity.status( HttpStatus.CREATED).body(clienteDB);    	
	         }
    		
		} catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value = "/byMail")
    public ResponseEntity <List<Cliente>> encontrarxMail(	@RequestParam("empresa_id")  Long empresa_id,
    											  			@RequestParam("mail_id")    String mail_id)  {

		try{
			
			
			@SuppressWarnings("rawtypes")
			List<Cliente> casos = clienteService.buscarxMail(empresa_id,
														     mail_id);
																	
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
	@GetMapping(value = "/byApellido")
    public ResponseEntity <List<Cliente>> encontrarxApellido(	@RequestParam("empresa_id")  Long empresa_id,
    											  				@RequestParam("apellido_id") String apellido_id)  {
		try{

			@SuppressWarnings("rawtypes")
			List<Cliente> casos = 	  clienteService.buscarxApellido(empresa_id,
																 	 apellido_id);															
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


	
	
	
