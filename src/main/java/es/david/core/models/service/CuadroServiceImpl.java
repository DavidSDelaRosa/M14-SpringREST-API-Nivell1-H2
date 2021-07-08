package es.david.core.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.core.models.entities.Cuadro;
import es.david.core.models.repository.CuadroRepository;

@Service
public class CuadroServiceImpl implements ICuadroService {

	@Autowired
	CuadroRepository cuadroRepository;
	
	@Override
	public Iterable<Cuadro> findAll() {
		return cuadroRepository.findAll();
	}

	@Override
	public Optional<Cuadro> findById(Long id) {
		return cuadroRepository.findById(id);
	}

	@Override
	public Cuadro save(Cuadro cuadro) {
		return cuadroRepository.save(cuadro);
	}

	@Override
	public void deleteById(Long id) {
		cuadroRepository.deleteById(id);
	}
	

	@Override
	public List<Cuadro> saveAll(List<Cuadro> cuadros) {
		return cuadroRepository.saveAll(cuadros);
	}

	@Override
	public void deleteAll() {
		cuadroRepository.deleteAll();
	}

}
