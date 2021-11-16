package nsc.com.operaciones.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.N2;
import nsc.com.operaciones.repositorio.N2Repositorio;

@Service
@RequiredArgsConstructor
public class N2ServiceImpl implements N2Service {
	
	private final N2Repositorio n2Repositorio;
		
	@Override
	public List<N2> listAllN2(){
		return n2Repositorio.findAll();
	}

	@Override
	public N2 getN2(Long idn2) {
		return n2Repositorio.findById(idn2).orElse(null);
	}

	@Override
	public N2 createN2(N2 n2) {
		return n2Repositorio.save(n2);
	}

	@Override
	public N2 updateN2(N2 n2) {
		N2 n2DB = getN2(n2.getIdn2());
		if (n2DB == null) {
			return null;
		} else {
			return n2Repositorio.save(n2);
		}
	}

	@Override
	public N2 deleteN2(Long idn2) {
		N2 n2DB = getN2(idn2);
		if (n2DB == null) {
			return null;
		} else {
			n2Repositorio.deleteById(idn2);
			return n2DB;
		}
	}

	@Override
	public List<N2> cargaPOP(Long idempresa, Long idsucursal, Long idn1){	
		return n2Repositorio.armarPOP(idempresa,idsucursal,idn1);		
	}

}