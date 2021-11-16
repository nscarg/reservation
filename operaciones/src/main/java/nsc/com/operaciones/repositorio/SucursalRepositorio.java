package nsc.com.operaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nsc.com.operaciones.entity.Sucursal;


public interface SucursalRepositorio extends JpaRepository<Sucursal, Long> {
	@Query("SELECT e from Sucursal e WHERE e.idemp =  ?1 ")
	public List<Sucursal> armarPOP(Long empresa);		
}
