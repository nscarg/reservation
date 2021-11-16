package nsc.com.operaciones.services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Empresa;
import nsc.com.operaciones.entity.Sucursal;
import nsc.com.operaciones.repositorio.EmpresaRepositorio;


@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService{
	private final EmpresaRepositorio empresaRepositorio;
	
	
	@Override
	public List<Empresa> listAllEmpresas() {
		return empresaRepositorio.findAll();
	}

	@Override
	public Empresa getEmpresa(Long idempresa) {
		return empresaRepositorio.findById(idempresa).orElse(null); 
	}

	@Override
	public Empresa createEmpresa(Empresa empresa) {
		return empresaRepositorio.save(empresa);
	}

	@Override
	public Empresa updateEmpresa(Empresa empresa) {
		Empresa empresaDB = getEmpresa(empresa.getIdemp());
		if (empresaDB == null) {
			return null;
		} else {
			return empresaRepositorio.save(empresa);
		}
	}

	@Override
	public Empresa deleteEmpresa(Long idempresa) {
		Empresa empresaDB = getEmpresa(idempresa);
		if (empresaDB == null) {
			return null;
		} else {
			empresaRepositorio.deleteById(idempresa);
			return empresaDB;
		}
	}
	
	
	@Override
	public List<Empresa> cargaxHora(Integer hora){
		 return empresaRepositorio.horaIProceso(hora);	    
	}
	
	

}
