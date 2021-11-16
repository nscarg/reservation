package nsc.com.operaciones.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import nsc.com.operaciones.entity.AWSCliente;

public interface AWSClienteRepositorio extends JpaRepository<AWSCliente,Long> {

}
