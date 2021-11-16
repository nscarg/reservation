package nsc.com.operaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import nsc.com.operaciones.entity.User;
import nsc.com.operaciones.services.UserService;


@RestController
@RequestMapping("/usuarios")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/acceso")
	public ResponseEntity <User> xLogin(@RequestParam("email_id")       String email_id){
		
		
		@SuppressWarnings("rawtypes")
		
		User operador = userService.loginUser(email_id);

		if (operador == null) {
			return ResponseEntity.noContent().build();
		} 
		return ResponseEntity.ok(operador);
    
		
	}
	
	
	

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
	@GetMapping(value="/recuperoclave")
	public ResponseEntity <User> recupero(@RequestParam("email_id")       String email_id){
		
		
		@SuppressWarnings("rawtypes")
		
		User operador = userService.loginUser(email_id);

		if (operador == null) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.ok(operador);
    
		
	}
	
	

	
}
