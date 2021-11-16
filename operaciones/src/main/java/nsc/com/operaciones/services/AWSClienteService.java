package nsc.com.operaciones.services;

import java.util.List;

import nsc.com.operaciones.entity.AWS;
import nsc.com.operaciones.entity.AWSCliente;

public interface AWSClienteService {
	public List<AWSCliente> listAllAWSCliente();
	public AWSCliente   	getAWSCliente(Long id);
	public AWSCliente  	  	createAWSCliente(AWSCliente  awscliente);
	public AWSCliente       updateAWSCliente(AWSCliente  awscliente);
	public AWSCliente       deleteAWSCliente(Long id);
}
