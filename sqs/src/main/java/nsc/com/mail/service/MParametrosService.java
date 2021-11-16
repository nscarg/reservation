package nsc.com.mail.service;

import java.util.List;

import nsc.com.mail.entity.MParametros;

public interface MParametrosService {
	
	public List<MParametros> 	listAllMParametros();
	public MParametros   	  	getMParametros(Long id);
	public MParametros 	  		createMParametros(MParametros parametros);
	public MParametros       	updateMParametros(MParametros parametros);
	public MParametros       	deleteMParametros(Long id);
	
	public MParametros         elegirTemplate(	Long id_operador,
			 									Long id_empresa,
			 									Long id_sucursal,
			 									Long id_operacion,
			 									String lenguaje);

}


