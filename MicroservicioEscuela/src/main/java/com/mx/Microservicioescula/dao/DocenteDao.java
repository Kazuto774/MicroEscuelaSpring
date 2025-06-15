package com.mx.Microservicioescula.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mx.Microservicioescula.entidad.Docente;

@Repository
public interface DocenteDao extends JpaRepository<Docente, Integer> {

}
