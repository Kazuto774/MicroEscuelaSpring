package com.mx.Microservicioescula.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mx.Microservicioescula.entidad.Materia;

@Repository
public interface MateriaDao extends JpaRepository<Materia, Integer> {
	boolean existsByMateria(String nombre);
	
	Materia findByMateria(String materia);
	
	List<Materia> findByCreditos(int creditos);
}
