package nsc.com.operaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.operaciones.entity.Sucursal;

import nsc.com.operaciones.entity.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
	@Query("SELECT e from Empresa e ")
	public List<Empresa> armaPOP();	
	
	@Query("SELECT e from Empresa e  WHERE e.horapro =  ?1 ")
	public List<Empresa> horaIProceso(Integer hora);	
}


