package com.mx.Microservicioescula.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.Microservicioescula.dao.AlumnoDao;
import com.mx.Microservicioescula.dto.MateriaDto;
import com.mx.Microservicioescula.entidad.Alumno;
import com.mx.Microservicioescula.entidad.Materia;
import com.mx.Microservicioescula.respuesta.Respuesta;

@Service
public class ImpAlumno implements MetodosAlumnos{
	@Autowired
	AlumnoDao aluDao;
	
	
	@Override
	public Respuesta guardar(Alumno alumno) {
		Respuesta rs = new Respuesta();
		if(aluDao.findAll().isEmpty()) {
			aluDao.save(alumno);
			rs.setMensaje("Alumno registrado");
			rs.setSuccess(true);
			rs.setObj(alumno);
			return rs;
		}else {
			if(aluDao.existsById(alumno.getMatricula())) {
				rs.setMensaje("EsteAlumno ya existe");
				rs.setSuccess(false);
				rs.setObj(aluDao.findById(alumno.getMatricula()));
				return rs;
			}else {
				aluDao.save(alumno);
				rs.setMensaje("Alumno registrado");
				rs.setSuccess(true);
				rs.setObj(alumno);
				return rs;
			}
		}
	}

	@Override
	public void editar(Alumno alumno) {
		// TODO Auto-generated method stub
		Alumno alumno_aux = aluDao.findById(alumno.getMatricula()).orElse(null);
		alumno_aux.setNombre(alumno.getNombre());
		alumno_aux.setApellido_paterno(alumno.getApellido_paterno());
		alumno_aux.setApellido_materno(alumno.getApellido_materno());
		alumno_aux.setFecha_de_nacimiento(alumno.getFecha_de_nacimiento());
		alumno_aux.setCiudad(alumno.getCiudad());
		alumno_aux.setEmail(alumno.getEmail());
		aluDao.save(alumno_aux);
	}

	@Override
	public void eliminar(Alumno alumno) {
		// TODO Auto-generated method stub
		alumno = aluDao.findById(alumno.getMatricula()).orElse(null);
		if(alumno.getMaterias().isEmpty()) {
			aluDao.delete(alumno);
		}else {
			for(Materia m: alumno.getMaterias()) {
				m.getAlumnos().remove(alumno);
			}
			alumno.getMaterias().clear();
			aluDao.delete(alumno);
		}
		
	}

	@Override
	public Respuesta buscar(Alumno alumno) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		alumno = aluDao.findById(alumno.getMatricula()).orElse(null);
		if(alumno == null) {
			rs.setMensaje("El alumno no existe");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
		}else {
			rs.setMensaje("El alumno si existe");
			rs.setSuccess(true);
			rs.setObj(alumno);
			return rs;
		}
		
	}

	@Override
	public Respuesta mostrar() {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if(aluDao.findAll().isEmpty()) {
			rs.setMensaje("No existen alumnos en la BD");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
		}else {
			rs.setMensaje("Se encontraron los siguientes registros");
			rs.setSuccess(true);
			rs.setObj(aluDao.findAll());
			return rs;
		}
	}
	
	public Respuesta materiasDeAlumno(Alumno alumno) {
		Respuesta rs = new Respuesta();
		alumno = aluDao.findById(alumno.getMatricula()).orElse(null);
		if(alumno.getMaterias().isEmpty()) {
			rs.setMensaje("Este alumno no esta inscrito en nunguna materia");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
			
		}else {
			List<MateriaDto> listaDao = new ArrayList<>();
			for(Materia m: alumno.getMaterias()) {
				listaDao.add(new MateriaDto(m.getNrc(), m.getMateria(), m.getCreditos(), m.getNivel()));
			}
			rs.setMensaje("Estos alumnos estan inscritos en las siguientes materias");
			rs.setSuccess(true);
			rs.setObj(listaDao);
			return rs;
		}
	}

}
