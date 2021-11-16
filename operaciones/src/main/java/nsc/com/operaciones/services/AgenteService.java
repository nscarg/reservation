package nsc.com.operaciones.services;

import java.util.List;

import nsc.com.operaciones.entity.Agente;
import nsc.com.operaciones.entity.N2;

public interface AgenteService {
	public List<Agente> listAllAgente();
	public Agente   	getAgente(Long idagente);
	public Agente 	  	createAgente(Agente agente);
	public Agente       updateAgente(Agente agente);
	public Agente       deleteAgente(Long idagente);
	public List<Agente> cargaPOP(Long empresa, Long Sucursal, Long N1, Long n2);
}