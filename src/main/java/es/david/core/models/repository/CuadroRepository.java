package es.david.core.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.david.core.models.entities.Cuadro;

public interface CuadroRepository extends JpaRepository<Cuadro, Long> {

}
