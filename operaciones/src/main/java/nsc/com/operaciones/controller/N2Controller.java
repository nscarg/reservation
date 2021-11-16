package nsc.com.operaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nsc.com.operaciones.services.N2Service;


@RestController
@RequestMapping("/niveles2")
public class N2Controller {
	
	@Autowired
	private N2Service n2Service;
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/cargaPOP")
	public ResponseEntity <List<Object>> armaPOP(@RequestParam("empresa_id")  Long empresa_id,
												 @RequestParam("sucursal_id") Long sucursal_id,
												 @RequestParam("nivel1_id")   Long nivel1_id){


		@SuppressWarnings("rawtypes")
		List n2s = n2Service.cargaPOP(empresa_id, sucursal_id, nivel1_id);

		if (n2s.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.ok(n2s);
    
	}
	
}