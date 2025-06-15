package com.mx.Microservicioescula.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Microservicioescula.entidad.Alumno;

@Repository
public interface AlumnoDao extends JpaRepository<Alumno, Integer> {

}
