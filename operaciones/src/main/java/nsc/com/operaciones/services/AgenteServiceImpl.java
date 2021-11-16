package nsc.com.operaciones.services;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Agente;
import nsc.com.operaciones.repositorio.AgenteRepositorio;


@Service
@RequiredArgsConstructor
public class AgenteServiceImpl implements AgenteService{
	private final AgenteRepositorio agenteRepositorio;
	
	@Override
	public List<Agente> listAllAgente() {
		return agenteRepositorio.findAll();
	}

	@Override
	public Agente getAgente(Long id) {
		return agenteRepositorio.findById(id).orElse(null);
	}

	@Override
	public Agente createAgente(Agente agente) {
		return agenteRepositorio.save(agente) ;
	}

	@Override
	public Agente updateAgente(Agente agente) {
		Agente agenteDB = getAgente(agente.getIdsuc());
		if (agenteDB == null) {
			return null;
		} else {
			return agenteRepositorio.save(agente);
		}
	}

	@Override
	public Agente deleteAgente(Long idagente) {
		Agente agenteDB = getAgente(idagente);
		if (agenteDB == null) {
			return null;
		} else {
			agenteRepositorio.deleteById(idagente);
			return agenteDB;
		}
	}

	@Override
	public List<Agente> cargaPOP(Long empresa, Long sucursal, Long n1, Long n2) {
		return  agenteRepositorio.armarPOP(empresa,sucursal,n1,n2); 
	}

}
