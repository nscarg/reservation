package nsc.com.operaciones.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.operaciones.entity.AWS;

public interface AWSRepositorio extends JpaRepository<AWS, Long>{

}
