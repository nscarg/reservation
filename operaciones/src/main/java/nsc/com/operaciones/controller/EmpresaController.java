package nsc.com.operaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import nsc.com.operaciones.entity.Empresa;
import nsc.com.operaciones.services.EmpresaService;


@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/login")
	public ResponseEntity <Empresa> acceso(@RequestParam("empresa_id") Long empresa_id){
				
		@SuppressWarnings("rawtypes")
		Empresa empresa = empresaService.getEmpresa(empresa_id);

		if (empresa == null) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.ok(empresa);
	}
	
}
