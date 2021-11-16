package nsc.com.operaciones.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.operaciones.entity.Feriado;

import nsc.com.operaciones.entity.Feriado;

public interface FeriadoRepositorio extends JpaRepository<Feriado, Long> {
	
	@Query("SELECT e from Feriado e WHERE e.pais = ?1 AND e.fecha = ?2 ")
	public Feriado buscoxPais(Long pais, Date fecha);	
	
}