package nsc.com.operaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import nsc.com.operaciones.entity.Agente;
import nsc.com.operaciones.services.AgenteService;


@RestController
@RequestMapping("/agentes")
public class AgenteController {
	
	@Autowired
	private AgenteService agenteService;
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/cargaPOP")
	public ResponseEntity<List<Agente>> popAgentes(@RequestParam("empresa_id") Long empresa_id,
												   @RequestParam("sucursal_id") Long sucursal_id,
												   @RequestParam("nivel1_id") Long nivel1_id,
												   @RequestParam("nivel2_id") Long nivel2_id){
		
		
		@SuppressWarnings("rawtypes")
		List<Agente> agentes =agenteService.cargaPOP(empresa_id,sucursal_id,nivel1_id,nivel2_id);

		if (agentes.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		return ResponseEntity.ok(agentes);
    
		
	}
	
}
