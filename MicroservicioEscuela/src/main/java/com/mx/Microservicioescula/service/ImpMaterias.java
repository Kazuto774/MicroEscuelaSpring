package com.mx.Microservicioescula.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Microservicioescula.dao.AlumnoDao;
import com.mx.Microservicioescula.dao.MateriaDao;
import com.mx.Microservicioescula.dto.DocenteDto;
import com.mx.Microservicioescula.entidad.Alumno;
import com.mx.Microservicioescula.entidad.Docente;
import com.mx.Microservicioescula.entidad.Materia;
import com.mx.Microservicioescula.peticion.PeticionAlumnoMateria;
import com.mx.Microservicioescula.respuesta.Respuesta;

@Service
public class ImpMaterias implements MetodosMaterias{
	@Autowired
	MateriaDao materiaDao;
	
	@Autowired
	AlumnoDao aluDao;
	
	@Override
	public Respuesta guardar(Materia materia) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if(materiaDao.findAll().isEmpty()) {
			materiaDao.save(materia);
			rs.setMensaje("Esta materia ha sido registrada");
			rs.setSuccess(true);
			rs.setObj(materia);
			return rs;
		}else {
			if(materiaDao.existsById(materia.getNrc())) {
				rs.setMensaje("Este NRC ya existe");
				rs.setSuccess(false);
				rs.setObj(materiaDao.findById(materia.getNrc()));
				return rs;
			}else {
				if(materiaDao.existsByMateria(materia.getMateria())) {
					rs.setMensaje("Esta materia ya existe");
					rs.setSuccess(false);
					rs.setObj(materia.getMateria());
					return rs;
				}
				materiaDao.save(materia);
				rs.setMensaje("La materia ha sido registrada");
				rs.setSuccess(true);
				rs.setObj(materia);
				return rs;
			}
			
		}
	}

	@Override
	public void editar(Materia materia) {
		// TODO Auto-generated method stub
		Materia materia_aux = materiaDao.findById(materia.getNrc()).orElse(null);
		materia_aux.setMateria(materia.getMateria());
		materia_aux.setCreditos(materia.getCreditos());
		materia_aux.setNivel(materia.getNivel());
		materiaDao.save(materia_aux);
	}

	@Override
	public Respuesta eliminar(Materia materia) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		materia = materiaDao.findById(materia.getNrc()).orElse(null);
		if(materia.getAlumnos().isEmpty()) {
			materiaDao.delete(materia);
			rs.setMensaje("La materia he sido eliminada");
			rs.setSuccess(true);
			rs.setObj(null);
			return rs;
		}else {
			if(materia.getDocentes().isEmpty()) {
				for(Alumno a: materia.getAlumnos()) {
					a.getMaterias().remove(materia);
				}
				materia.getAlumnos().clear();
				materiaDao.delete(materia);
				rs.setMensaje("La materia he sido eliminada");
				rs.setSuccess(true);
				rs.setObj(null);
				return rs;
			}else {
				rs.setMensaje("La materia no puede ser eliminada por que un profe la imparte");
				rs.setSuccess(false);
				rs.setObj(null);
				return rs;
			}
		}
	}

	@Override
	public Respuesta buscar(Materia materia) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		materia = materiaDao.findById(materia.getNrc()).orElse(null);
		if(materia == null) {
			rs.setMensaje("La materia no existe");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
		}else {
			rs.setMensaje("La materia si existe");
			rs.setSuccess(true);
			rs.setObj(materia);
			return rs;
		}
		
	}

	@Override
	public Respuesta mostrar() {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if(materiaDao.findAll().isEmpty()) {
			rs.setMensaje("Si existen materias en la BD");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
		}else {
			rs.setMensaje("Si existen materias en la BD");
			rs.setSuccess(true);
			rs.setObj(materiaDao.findAll());
			return rs;
		}
	}
	
	public Respuesta mostrarAlumnos(Materia materia) {
		Respuesta rs = new Respuesta();
		materia = materiaDao.findById(materia.getNrc()).orElse(null);
		if(materia.getAlumnos().isEmpty()) {
			rs.setMensaje("Esta materia no tiene alumnos aun");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
		}else {
			rs.setMensaje("Esta materia si tiene alumnos y son");
			rs.setSuccess(true);
			rs.setObj(materia.getAlumnos());
			return rs;
		}
		
	}
	
	public Respuesta mostrarDocentes(Materia materia) {
		Respuesta rs = new Respuesta();
		materia = materiaDao.findById(materia.getNrc()).orElse(null);
		if(materia.getDocentes().isEmpty()) {
			rs.setMensaje("Esta materia no tiene docentes aun");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
		}else {
			List<DocenteDto> listaDao = new ArrayList<>();
			for(Docente d: materia.getDocentes()) {
				listaDao.add(new DocenteDto(d.getId_docente(),
						d.getNombre(),
						d.getApellido_paterno(),
						d.getApellido_materno(),
						d.getCiudad(),
						d.getEspecialidad()));
				
			}
			rs.setMensaje("Estos docentes imparten la materia");
			rs.setSuccess(true);
			rs.setObj(listaDao);
			return rs;
		}
	}
	public Respuesta darDeBajaAlumno(PeticionAlumnoMateria am) {
		Respuesta rs = new Respuesta();
		Materia materia_aux = materiaDao.findById(am.getNrc_materia()).orElse(null);
		Alumno alumno_aux = aluDao.findById(am.getMatricula_alumno()).orElse(null);
		
		if(materia_aux == null && alumno_aux == null) {
			rs.setMensaje("Ni la materia ni el alumno existen");
			rs.setSuccess(false);
			rs.setObj(am);
			return rs;
		}else if(materia_aux != null && alumno_aux == null){
			rs.setMensaje("el alumno no existen");
			rs.setSuccess(false);
			rs.setObj(am.getMatricula_alumno());
			return rs;
		}else if(materia_aux == null && alumno_aux != null){
			rs.setMensaje("La materia no existe");
			rs.setSuccess(false);
			rs.setObj(am.getNrc_materia());
			return rs;
		}else {
			int bandera = 0;
			for(Alumno a: materia_aux.getAlumnos()) {
				if(alumno_aux.getMatricula() == a.getMatricula()) {
					bandera = 1;
				}
			}
			if(bandera == 0) {
				rs.setMensaje("El alumno no existe en la materia");
				rs.setSuccess(false);
				rs.setObj(am);
				return rs;
			}else {
				materia_aux.getAlumnos().remove(alumno_aux);
				materiaDao.save(materia_aux);
				rs.setMensaje("El alumno fue dado de baja");
				rs.setSuccess(true);
				rs.setObj(am);
				return rs;
			}
		}
	}
	public Respuesta asignar(PeticionAlumnoMateria am) {
		Respuesta rs = new Respuesta();
		Materia materia_aux = materiaDao.findById(am.getNrc_materia()).orElse(null);
		Alumno alumno_aux = aluDao.findById(am.getMatricula_alumno()).orElse(null);
		if(materia_aux == null && alumno_aux == null) {
			rs.setMensaje("Ni la materia ni el alumno existen");
			rs.setSuccess(false);
			rs.setObj(am);
			return rs;
		}else if(materia_aux != null && alumno_aux == null){
			rs.setMensaje("el alumno no existen");
			rs.setSuccess(false);
			rs.setObj(am.getMatricula_alumno());
			return rs;
		}else if(materia_aux == null && alumno_aux != null){
			rs.setMensaje("La materia no existe");
			rs.setSuccess(false);
			rs.setObj(am.getNrc_materia());
			return rs;
		}else {
			for(Alumno a: materia_aux.getAlumnos()) {
				if(alumno_aux.getMatricula() == a.getMatricula()) {
					rs.setMensaje("El alumno ya esta deado de alta en esta materia");
					rs.setSuccess(false);
					rs.setObj(am);
					return rs;
				}
			}
			materia_aux.getAlumnos().add(alumno_aux);
			materiaDao.save(materia_aux);
			rs.setMensaje("El alumno fue inscrito en la materia");
			rs.setSuccess(true);
			rs.setObj(am);
			return rs;
		}
	}
}
