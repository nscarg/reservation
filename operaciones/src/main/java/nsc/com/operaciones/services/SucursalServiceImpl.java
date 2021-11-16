package nsc.com.operaciones.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Empresa;
import nsc.com.operaciones.entity.Sucursal;
import nsc.com.operaciones.repositorio.SucursalRepositorio;

@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {
	
	private final SucursalRepositorio sucursalRepositorio;
		
	@Override
	public List<Sucursal> listAllSucursal(){
		return sucursalRepositorio.findAll();
	}

	@Override
	public Sucursal getSucursal(Long idsuc) {
		return sucursalRepositorio.findById(idsuc).orElse(null);
	}

	@Override
	public Sucursal createSucursal(Sucursal sucursal) {
		return sucursalRepositorio.save(sucursal);
	}

	@Override
	public Sucursal updateSucursal(Sucursal sucursal) {
		Sucursal sucursalDB = getSucursal(sucursal.getIdsuc());
		if (sucursalDB == null) {
			return null;
		} else {
			return sucursalRepositorio.save(sucursal);
		}
	}

	@Override
	public Sucursal deleteSucursal(Long idsuc) {
		Sucursal sucursalDB = getSucursal(idsuc);
		if (sucursalDB == null) {
			return null;
		} else {
			sucursalRepositorio.deleteById(idsuc);
			return sucursalDB;
		}
	}

	@Override
	public List<Sucursal> cargaPOP(Long idempresa){	
		return sucursalRepositorio.armarPOP(idempresa);		
	}

}
