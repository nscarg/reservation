package nsc.com.operaciones.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.N1;
import nsc.com.operaciones.repositorio.N1Repositorio;

@Service
@RequiredArgsConstructor
public class N1ServiceImpl implements N1Service {
	
	private final N1Repositorio n1Repositorio;
		
	@Override
	public List<N1> listAllN1(){
		return n1Repositorio.findAll();
	}

	@Override
	public N1 getN1(Long idn1) {
		return n1Repositorio.findById(idn1).orElse(null);
	}

	@Override
	public N1 createN1(N1 n1) {
		return n1Repositorio.save(n1);
	}

	@Override
	public N1 updateN1(N1 n1) {
		N1 n1DB = getN1(n1.getIdn1());
		if (n1DB == null) {
			return null;
		} else {
			return n1Repositorio.save(n1);
		}
	}

	@Override
	public N1 deleteN1(Long idn1) {
		N1 n1DB = getN1(idn1);
		if (n1DB == null) {
			return null;
		} else {
			n1Repositorio.deleteById(idn1);
			return n1DB;
		}
	}

	@Override
	public List<N1> cargaPOP(Long idempresa, Long idsucursal){	
		return n1Repositorio.armarPOP(idempresa,idsucursal);		
	}

}