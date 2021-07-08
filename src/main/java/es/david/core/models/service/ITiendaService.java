package es.david.core.models.service;

import java.util.List;
import java.util.Optional;

import es.david.core.models.entities.Tienda;

public interface ITiendaService {
	
	public Iterable<Tienda> findAll();
	
	public Optional<Tienda> findById(Long id);
	
	public Tienda save(Tienda tienda);
	
	public List<Tienda> saveAll(List<Tienda> tiendas);
	
	public void deleteById(Long id);
	
	public void deleteAll();

}
