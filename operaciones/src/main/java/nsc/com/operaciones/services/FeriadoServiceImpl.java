package nsc.com.operaciones.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import nsc.com.operaciones.entity.Feriado;

import nsc.com.operaciones.repositorio.FeriadoRepositorio;


@Service
@RequiredArgsConstructor
public class FeriadoServiceImpl implements FeriadoService{
	private final FeriadoRepositorio feriadoRepositorio;
	
	
	@Override
	public List<Feriado> listAllFeriados() {
		return feriadoRepositorio.findAll();
	}

	@Override
	public Feriado getFeriado(Long idferiado) {
		return feriadoRepositorio.findById(idferiado).orElse(null); 
	}

	@Override
	public Feriado createFeriado(Feriado feriado) {
		return feriadoRepositorio.save(feriado);
	}

	@Override
	public Feriado updateFeriado(Feriado feriado) {
		Feriado feriadoDB = getFeriado(feriado.getIdferiado());
		if (feriadoDB == null) {
			return null;
		} else {
			return feriadoRepositorio.save(feriado);
		}
	}

	@Override
	public Feriado deleteFeriado(Long idferiado) {
		Feriado feriadoDB = getFeriado(idferiado);
		if (feriadoDB == null) {
			return null;
		} else {
			feriadoRepositorio.deleteById(idferiado);
			return feriadoDB;
		}
	}
	
	
	@Override
	public Feriado buscoxPais(Long pais, Date fecha){
		return feriadoRepositorio.buscoxPais(pais, fecha);
		
		 	    
	}
	
	

}
