package nsc.com.operaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import nsc.com.operaciones.entity.AWS;
import nsc.com.operaciones.services.AWSService;


@RestController
@RequestMapping("/aws")
public class AWSController {
	
	@Autowired
	private AWSService awsService;


	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/leerParametros")
	public ResponseEntity<AWS> buscarParametros(@RequestParam("empresa_id") Long empresa_id){
		
		@SuppressWarnings("rawtypes")
		AWS parametros = awsService.getAWS(empresa_id);
		
		if (parametros == null) {
			return ResponseEntity.noContent().build();
		} 
		return ResponseEntity.ok(parametros);
    
		
	}
	
}