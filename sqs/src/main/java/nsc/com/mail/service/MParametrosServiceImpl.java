package nsc.com.mail.service;

import java.util.List;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import nsc.com.mail.entity.MParametros;
import nsc.com.mail.reservorio.MParametrosReservorio;
import nsc.com.mail.service.MParametrosService;

@Service
@RequiredArgsConstructor
public class MParametrosServiceImpl implements MParametrosService{
	private final MParametrosReservorio parametrosReservorio;
	
	@Override
	public List<MParametros> listAllMParametros() {
		return parametrosReservorio.findAll();
	}

	@Override
	public MParametros getMParametros(Long id) {
		return parametrosReservorio.findById(id).orElse(null);
	}

	@Override
	public MParametros createMParametros(MParametros parametros) {
		return parametrosReservorio.save(parametros) ;
	}

	@Override
	public MParametros updateMParametros(MParametros parametros) {
		MParametros parametrosDB = getMParametros(parametros.getId());
		if (parametrosDB == null) {
			return null;
		} else {
			return parametrosReservorio.save(parametros);
		}
	}

	@Override
	public MParametros deleteMParametros(Long id) {
		MParametros parametrosDB = getMParametros(id);
		if (parametrosDB == null) {
			return null;
		} else {
			parametrosReservorio.deleteById(id);
			return parametrosDB;
		}
	}

	@Override
	public MParametros elegirTemplate(Long id_operador, Long id_empresa, Long id_sucursal, Long id_operacion, String lenguaje) {
		return  parametrosReservorio.templateOperacion(id_operador, id_empresa, id_sucursal, id_operacion, lenguaje); 
	}
}




