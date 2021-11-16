package nsc.com.operaciones.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Cliente;

import nsc.com.operaciones.repositorio.ClienteRepositorio;


@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
	
	private final ClienteRepositorio clienteRepositorio;
	
	@Override
	public List<Cliente> listAllCliente() {
		return clienteRepositorio.findAll();
	}

	@Override
	public Cliente getCliente(Long id) {
		return clienteRepositorio.findById(id).orElse(null);
	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		return clienteRepositorio.save(cliente) ;
	}

	@Override
	public Cliente updateCliente(Cliente cliente) {

		Cliente clienteDB = getCliente(cliente.getIdcli());
		if (clienteDB == null) {
			return null;
		} else {
			return clienteRepositorio.saveAndFlush(cliente);
		}
	}

	@Override
	public Cliente deleteCliente(Long idcli) {
		Cliente clienteDB = getCliente(idcli);
		if (clienteDB == null) {
			return null;
		} else {
			clienteRepositorio.deleteById(idcli);
			return clienteDB;
		}
	}

	@Override
	public List<Cliente> loginxMail(Long idempresa, String email) {
		return clienteRepositorio.existexMail(idempresa, email);
	}

	
	@Override
	public List<Cliente> loginxTelefono(Long empresa, String documento, String telefono) {
		return clienteRepositorio.existexPhone(empresa, documento, telefono);
	}
	

	@Override
	public List<Cliente> loginxTwitter(Long empresa, String twitter) {
		return clienteRepositorio.existexTwitter(empresa, twitter);
	}

	@Override
	public List<Cliente> buscarxMail(Long empresa, String mail) {
		return clienteRepositorio.xMail(empresa, mail);
	}
	
	@Override
	public List<Cliente> buscarxApellido(Long empresa, String apellido) {
		return clienteRepositorio.xApellido(empresa, apellido);
	}
}
