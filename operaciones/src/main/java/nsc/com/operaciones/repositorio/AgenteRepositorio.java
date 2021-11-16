package nsc.com.operaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.operaciones.entity.Agente;

public interface AgenteRepositorio extends JpaRepository<Agente, Long> {
	@Query("SELECT e from Agente e WHERE e.idemp = ?1 AND e.idsuc = ?2 AND e.idn1 = ?3 AND e.idn2 = ?4")
	public List<Agente> armarPOP(Long empresa,Long Sucursal,Long N1, Long N2);		
}
