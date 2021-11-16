package nsc.com.operaciones.services;
import java.io.Console;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Empresa;
import nsc.com.operaciones.entity.Hora;
import nsc.com.operaciones.entity.Reserva;
import nsc.com.operaciones.repositorio.EmpresaRepositorio;
import nsc.com.operaciones.repositorio.HoraRepositorio;


@Service
@RequiredArgsConstructor
public class HoraServiceImpl implements HoraService{
	
private final HoraRepositorio 		horaRepositorio;
private final EmpresaRepositorio 	empresaRepositorio;
	
	
	@Override
	public List<Hora> listAllHora() {
		return horaRepositorio.findAll();
	}

	@Override
	public Hora getHora(Long idhora) {
		return horaRepositorio.findById(idhora).orElse(null); 
	}

	@Override
	public Hora createHora(Hora hora) {
		return horaRepositorio.save(hora);
	}

	@Override
	public Hora updateHora(Hora hora) {
		Hora horaDB = getHora(hora.getIdhora());
		if (horaDB == null) {
			return null;
		} else {
			return horaRepositorio.save(hora);
		}
	}

	@Override
	public Hora deleteHora(Long idhora) {
		Hora horaDB = getHora(idhora);
		if (horaDB == null) {
			return null;
		} else {
			horaRepositorio.deleteById(idhora);
			return horaDB;
		}
	}
	
	@Override
	public List<Hora> armadoHorario(Long idemp,
									  Long idsuc,
									  Long idn1,
									  Long idn2,
									  Long idage,
									  Integer dia) {
		
		   return horaRepositorio.generacion(idemp, idsuc, idn1, idn2, idage,dia);
		
	}
	
	@Override
	public List<Hora> horaDia(Long idemp,
							  Long idsuc,
							  Long idn1,
							  Long idn2,
							  Long idage,
							  Integer dia) {
		
		   return horaRepositorio.horasxDia(idemp, idsuc, idn1, idn2, idage,dia);
	}
	
	
	@Override
	public Boolean   eliminacion(Long id) {
		System.out.println(id);
		Boolean operacion = false;
		Hora elegida = deleteHora(id);
		System.out.println(elegida);
		if (elegida != null) {
			operacion=true;;
		} else {
			operacion=false;
		}
		
		return operacion;
   			
	}
	
	
}
