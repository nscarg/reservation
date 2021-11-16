package nsc.com.operaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import nsc.com.operaciones.entity.AWSCliente;
import nsc.com.operaciones.services.AWSClienteService;


@RestController
@RequestMapping("/awsc")
public class AWSClienteController {
	
	@Autowired
	private AWSClienteService awsClienteService;
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/leerParametros")
	public ResponseEntity<AWSCliente> buscarParametros(@RequestParam("empresa_id") Long empresa_id){
		
		
		@SuppressWarnings("rawtypes")
		AWSCliente parametroCliente =awsClienteService.getAWSCliente(empresa_id);

		if (parametroCliente == null) {
			return ResponseEntity.noContent().build();
		} 
		return ResponseEntity.ok(parametroCliente);
    
		
	}
	
}