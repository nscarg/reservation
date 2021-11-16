package nsc.com.operaciones.services;

import java.util.List;
import nsc.com.operaciones.entity.User;

public interface UserService {
	
	public List<User> 	listAllUser();
	public User   		getUser(Long iduser);
	public User  		createUser(User user);
	public User       	updateUser(User user);
	public User       	deleteUser(Long iduser);
	public User         loginUser(String email);
	
}