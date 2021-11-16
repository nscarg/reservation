package nsc.com.operaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.operaciones.entity.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT e from Cliente e WHERE e.idemp =  ?1 AND e.email = ?2")
	public List<Cliente> existexMail(Long empresa, String  email);	
	
	@Query("SELECT e from Cliente e WHERE e.idemp =  ?1 AND e.doc = ?2 AND e.telef = ?3")
	public List<Cliente> existexPhone(Long empresa, String documento, String  telefono);	
	
	@Query("SELECT e from Cliente e WHERE e.idemp =  ?1 AND e.twitter = ?2")
	public List<Cliente> existexTwitter(Long empresa, String  twitter);	
	
	@Query("SELECT e from Cliente e WHERE e.idemp =  ?1 AND e.email LIKE %?2%")
	public List<Cliente> xMail(Long empresa, String  MAIL);	
	
	@Query("SELECT e from Cliente e WHERE e.idemp =  ?1 AND e.apell LIKE %?2%")
	public List<Cliente> xApellido(Long empresa, String  apellido);	
	
}

