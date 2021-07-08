package es.david.core.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.core.models.entities.Cuadro;
import es.david.core.models.repository.CuadroRepository;
import es.david.core.models.repository.TiendaRepository;

@Service
public class CuadroServiceImpl implements ICuadroService {

	@Autowired
	CuadroRepository cuadroRepository;
	@Autowired
	TiendaRepository tiendaRepository;
	
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

	@Override
	public List<Cuadro> getCuadrosByNombreCuadro(String nombreCuadro) {
		
		List<Cuadro> cuadrosByName = cuadroRepository.findByNombreCuadroContainingIgnoreCase(nombreCuadro);
		
		if(cuadrosByName.isEmpty()|| cuadrosByName.size()==0) System.err.println("Cuadros no encontrados");
		
		return cuadrosByName;
	}

	@Override
	public List<Cuadro> getCuadrosByAutor(String autor) {

		List<Cuadro> cuadrosByAutor = cuadroRepository.findByAutor(autor);
		
		if(cuadrosByAutor.isEmpty()|| cuadrosByAutor.size()==0) System.err.println("Cuadros no encontrados");

		
		return cuadrosByAutor;
	}

	@Override
	public List<Cuadro> getCuadrosByPrecioGreaterThan(double precio) {

		List<Cuadro> cuadrosByPrecio = cuadroRepository.findByPrecioGreaterThan(precio);
		
		if(cuadrosByPrecio.isEmpty()|| cuadrosByPrecio.size()==0) System.err.println("Cuadros no encontrados");

		return cuadrosByPrecio;
	}

	@Override
	public Cuadro saveOnTienda(Cuadro cuadro, Long idTienda) {
		
		cuadro.setTienda(tiendaRepository.findById(idTienda).get());
		System.err.println("Actualizada tienda sobre cuadro");
		return cuadroRepository.save(cuadro);
	}

	@Override
	public List<Cuadro> findByTiendaId(Long idTienda) {
		
		List<Cuadro> cuadrosPorTienda = new ArrayList<>();
		
		for(Cuadro c: cuadroRepository.findAll()) {
			if(c.getTienda().getIdTienda()==idTienda) cuadrosPorTienda.add(c);
		}
		
		if(cuadrosPorTienda.isEmpty()||cuadrosPorTienda.size()==0) System.err.println("No se han encontrado cuadros por esa tienda");
		
		return cuadrosPorTienda;
	}

	/*@Override
	public List<Cuadro> getCuadrosSortedByDate() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
