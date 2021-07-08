package es.david.core.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.david.core.models.entities.Cuadro;
import es.david.core.models.service.ICuadroService;

@RestController
@RequestMapping("/api/cuadros")
public class CuadroController {
	
	@Autowired
	private ICuadroService cuadroService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cuadro cuadro){
		System.out.println(cuadro.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(cuadroService.save(cuadro));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> createAll(@RequestBody List<Cuadro> cuadros){
		
		System.out.println("Creando varias...");
		for(Cuadro c: cuadros) System.out.println(c.toString());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cuadroService.saveAll(cuadros));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Cuadro> oCuadro = cuadroService.findById(id);
		
		if(!oCuadro.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(oCuadro);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Cuadro cuadro, @PathVariable Long id){
		Optional<Cuadro> oCuadro = cuadroService.findById(id);
		
		if(!oCuadro.isPresent())
			return ResponseEntity.notFound().build();
		
		oCuadro.get().setNombreCuadro(cuadro.getNombreCuadro());
		oCuadro.get().setAutor(cuadro.getAutor());
		oCuadro.get().setPrecio(cuadro.getPrecio());
		oCuadro.get().setFechaCreacion(cuadro.getFechaCreacion());
		oCuadro.get().setTienda(cuadro.getTienda());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cuadroService.save(oCuadro.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(!cuadroService.findById(id).isPresent())
			return ResponseEntity.notFound().build();
		
		cuadroService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteAll(){
		System.out.println("Borrando varias...");
		cuadroService.deleteAll();
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Cuadro> readAll(){
		List<Cuadro> cuadros = StreamSupport
				.stream(cuadroService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return cuadros;
	}
}
