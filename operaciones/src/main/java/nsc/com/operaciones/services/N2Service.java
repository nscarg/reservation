package nsc.com.operaciones.services;

import java.util.List;
import nsc.com.operaciones.entity.N2;

public interface N2Service {
	public List<N2> 	listAllN2();
	public N2   		getN2(Long idn2);
	public N2 	  		createN2(N2 agente);
	public N2       	updateN2(N2 agente);
	public N2       	deleteN2(Long idn2);
	public List<N2> cargaPOP(Long idempresa, Long idsucursal, Long idn1);
}