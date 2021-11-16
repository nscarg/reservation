package nsc.com.mail.reservorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.mail.entity.MParametros;

public interface MParametrosReservorio extends JpaRepository<MParametros, Long> {
	@Query("SELECT e from MParametros e WHERE e.id_operador = ?1 AND e.id_empresa = ?2 AND e.id_sucursal = ?3 AND e.id_operacion = ?4 AND e.lenguaje = ?5")
	public MParametros templateOperacion(Long id_operador,
										 Long id_empresa,
										 Long id_sucursal,
										 Long id_operacion,
										 String lenguaje);
}

