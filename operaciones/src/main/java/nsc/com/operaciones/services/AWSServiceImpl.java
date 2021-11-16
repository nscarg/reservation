package nsc.com.operaciones.services;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.AWS;
import nsc.com.operaciones.repositorio.AWSRepositorio;

@Service
@RequiredArgsConstructor
public class AWSServiceImpl implements AWSService {

	
	private final AWSRepositorio awsRepositorio;

	@Override
	public List<AWS> listAllAWS() {
		return awsRepositorio.findAll();
	}

	@Override
	public AWS getAWS(Long id) {
		return awsRepositorio.findById(id).orElse(null);
	}

	@Override
	public AWS createAWS(AWS aws) {
		return awsRepositorio.save(aws) ;
	}

	@Override
	public AWS updateAWS(AWS aws) {
		AWS awsDB = getAWS(aws.getId());
		if (awsDB == null) {
			return null;
		} else {
			return awsRepositorio.save(aws);
		}
	}

	@Override
	public AWS deleteAWS(Long id) {
		AWS awsDB = getAWS(id);
		if (awsDB == null) {
			return null;
		} else {
			awsRepositorio.deleteById(id);
			return awsDB;
		}
	}


}
