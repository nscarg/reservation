package nsc.com.operaciones.services;

import java.util.List;
import nsc.com.operaciones.entity.Cliente;


public interface ClienteService {
	public List< Cliente> listAllCliente();
	public Cliente   	  getCliente(Long idagente);
	public Cliente 	  	  createCliente(Cliente cliente);
	public Cliente        updateCliente(Cliente cliente);
	public Cliente        deleteCliente(Long idagente);
	public List<Cliente>  loginxMail(Long empresa, String mail );
	public List<Cliente>  loginxTelefono(Long empresa, String documento, String telefono);
	public List<Cliente>  loginxTwitter(Long empresa, String twitter);
	public List<Cliente>  buscarxMail(Long empresa,String mail);
	public List<Cliente>  buscarxApellido(Long empresa,String apellido);
}