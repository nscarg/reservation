package nsc.com.operaciones.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nsc.com.operaciones.entity.Cliente;
import nsc.com.operaciones.entity.Sucursal;
import nsc.com.operaciones.services.SucursalService;


@RestController
@RequestMapping("/sucursales")
public class SucursalController {
	
	@Autowired
	private SucursalService sucursalService;
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/cargaPOP")
	public ResponseEntity<List<Sucursal>> armaPOP(@RequestParam("empresa_id") Long empresa_id){
		

		//@SuppressWarnings("rawtypes")
		List<Sucursal> sucursales = sucursalService.cargaPOP(empresa_id);


		if (sucursales.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		
				
		
		return ResponseEntity.ok(sucursales);
    
		
	}
	
}
