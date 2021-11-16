package nsc.com.operaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.operaciones.entity.N1;

public interface N1Repositorio extends JpaRepository<N1, Long> {
	@Query("SELECT e from N1 e WHERE e.idemp =  ?1 AND e.idsuc = ?2")
	public List<N1> armarPOP(Long empresa, Long sucursal);		
}
