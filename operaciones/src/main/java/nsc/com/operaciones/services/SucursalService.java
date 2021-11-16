package nsc.com.operaciones.services;

import java.util.List;
import nsc.com.operaciones.entity.Sucursal;


public interface SucursalService {
	public List<Sucursal> listAllSucursal();
	public Sucursal   	  getSucursal(Long idsuc);
	public Sucursal 	  createSucursal(Sucursal sucursal);
	public Sucursal       updateSucursal(Sucursal sucursal);
	public Sucursal       deleteSucursal(Long id);
	public List<Sucursal> cargaPOP(Long empresa);
}
