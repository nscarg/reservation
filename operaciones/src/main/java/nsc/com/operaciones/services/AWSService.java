package nsc.com.operaciones.services;

import java.util.List;

import nsc.com.operaciones.entity.AWS;

public interface AWSService {
	public List<AWS> listAllAWS();
	public AWS   	getAWS(Long id);
	public AWS 	  	createAWS(AWS aws);
	public AWS      updateAWS(AWS aws);
	public AWS      deleteAWS(Long id);
}
