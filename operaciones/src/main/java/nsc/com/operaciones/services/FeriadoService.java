package nsc.com.operaciones.services;

import java.util.Date;
import java.util.List;

import nsc.com.operaciones.entity.Empresa;
import nsc.com.operaciones.entity.Feriado;



public interface FeriadoService {
	public List<Feriado> 	listAllFeriados();
	public Feriado   		getFeriado(Long idferiado);
	public Feriado 	  		createFeriado(Feriado feriado);
	public Feriado       	updateFeriado(Feriado feriado);
	public Feriado       	deleteFeriado(Long idferiado);
	public Feriado 			buscoxPais(Long pais,Date Fecha);

}
