package nsc.com.operaciones.services;
import nsc.com.operaciones.entity.AWS;
import nsc.com.operaciones.entity.Agente;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.AWSCliente;
import nsc.com.operaciones.repositorio.AWSClienteRepositorio;


@Service
@RequiredArgsConstructor
public class AWSClienteServiceImpl implements AWSClienteService{
	
	private final AWSClienteRepositorio awsClienteRepositorio;

	@Override
	public List<AWSCliente> listAllAWSCliente() {
		return awsClienteRepositorio.findAll();
	}

	@Override
	public AWSCliente getAWSCliente(Long id) {
		return awsClienteRepositorio.findById(id).orElse(null);
	}

	@Override
	public AWSCliente createAWSCliente(AWSCliente awscliente) {
		return awsClienteRepositorio.save(awscliente) ;
	}

	@Override
	public AWSCliente updateAWSCliente(AWSCliente awscliente) {
		AWSCliente awsclienteDB = getAWSCliente(awscliente.getIdemp());
		if (awsclienteDB == null) {
			return null;
		} else {
			return awsClienteRepositorio.save(awscliente);
		}
	}

	@Override
	public AWSCliente deleteAWSCliente(Long id) {
		AWSCliente awsclienteDB = getAWSCliente(id);
		if (awsclienteDB == null) {
			return null;
		} else {
			awsClienteRepositorio.deleteById(id);
			return awsclienteDB;
		}
	}

}
