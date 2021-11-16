package nsc.com.operaciones.services;

import java.util.List;

import nsc.com.operaciones.entity.Empresa;

public interface EmpresaService {
	public List<Empresa> 	listAllEmpresas();
	public Empresa   		getEmpresa(Long idempresa);
	public Empresa 	  		createEmpresa(Empresa empresa);
	public Empresa       	updateEmpresa(Empresa empresa);
	public Empresa       	deleteEmpresa(Long idempresa);
	public List<Empresa> 	cargaxHora(Integer hora);
}
