package nsc.com.operaciones.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Sucursal;
import nsc.com.operaciones.entity.User;
import nsc.com.operaciones.repositorio.UserRepositorio;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepositorio userRepositorio;
	
	public List<User> listAllUser() {
	return userRepositorio.findAll();
	
	}

	@Override
	public User getUser(Long iduser) {
		return userRepositorio.findById(iduser).orElse(null);
	}

	@Override
	public User createUser(User user) {
		return userRepositorio.save(user);
	}

	@Override
	public User updateUser(User user) {
		User userDB = getUser(user.getIduser());
		if (userDB == null) {
			return null;
		} else {
			return userRepositorio.save(user);
		}
	}

	@Override
	public User deleteUser(Long iduser) {
		User userDB = getUser(iduser);
		if (userDB == null) {
			return null;
		} else {
			userRepositorio.deleteById(iduser);
			return userDB;
		}
	}

	@Override
	public User loginUser(String email) {
		return userRepositorio.loginUser(email);
	}
	

	
}
