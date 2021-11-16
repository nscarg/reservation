package nsc.com.operaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.operaciones.entity.User;

public interface UserRepositorio extends JpaRepository<User, Long> {
	
	@Query("SELECT e from User e WHERE e.email = ?1")
	public User loginUser(String  email);	
	
}
