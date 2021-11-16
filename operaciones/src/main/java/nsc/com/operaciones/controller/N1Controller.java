package nsc.com.operaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nsc.com.operaciones.entity.N1;
import nsc.com.operaciones.services.N1Service;


@RestController
@RequestMapping("/niveles1")
public class N1Controller {
	
	@Autowired
	private N1Service n1Service;
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/cargaPOP")
	public ResponseEntity <List<N1>> armaPOP(@RequestParam("empresa_id")  Long empresa_id,
											 @RequestParam("sucursal_id") Long sucursal_id){
	
		@SuppressWarnings("rawtypes")
		List<N1> n1s = n1Service.cargaPOP(empresa_id, sucursal_id);

		if (n1s.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.ok(n1s);
    
	}
	
}