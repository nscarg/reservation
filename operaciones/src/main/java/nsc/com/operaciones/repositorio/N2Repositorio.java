package nsc.com.operaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.operaciones.entity.N2;

public interface N2Repositorio extends JpaRepository<N2, Long> {
	@Query("SELECT e from N2 e WHERE e.idemp =  ?1 AND e.idsuc = ?2 AND e.idn1 = ?3")
	public List<N2> armarPOP(Long idemp, Long idsuc, Long n1);		
}
