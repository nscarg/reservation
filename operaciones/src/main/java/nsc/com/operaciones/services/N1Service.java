package nsc.com.operaciones.services;

import java.util.List;

import nsc.com.operaciones.entity.N1;

public interface N1Service {
	public List<N1> 	listAllN1();
	public N1   		getN1(Long idn1);
	public N1 	  		createN1(N1 n1);
	public N1       	updateN1(N1 n1);
	public N1       	deleteN1(Long idn1);
	public List<N1> cargaPOP(Long idempresa, Long idsucursal);
}