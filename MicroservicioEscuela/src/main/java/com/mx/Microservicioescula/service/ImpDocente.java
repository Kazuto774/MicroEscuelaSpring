package com.mx.Microservicioescula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Microservicioescula.dao.DocenteDao;
import com.mx.Microservicioescula.dao.MateriaDao;
import com.mx.Microservicioescula.entidad.Docente;
import com.mx.Microservicioescula.respuesta.Respuesta;

@Service
public class ImpDocente implements MetodosDocente{
	
	@Autowired
	DocenteDao docenteDao;
	
	@Autowired
	MateriaDao materiaDao;
	
	@Override
	public Respuesta guardar(Docente docente) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if(docenteDao.findAll().isEmpty()) {
			if(docente.getMateria() == null) {
				docenteDao.save(docente);
				rs.setMensaje("El docente fue registrado");
				rs.setSuccess(true);
				rs.setObj(docente);
				return rs;
			}else {
				if(materiaDao.existsById(docente.getMateria().getNrc())) {
					docenteDao.save(docente);
					rs.setMensaje("El docente fue registrado");
					rs.setSuccess(true);
					rs.setObj(docente);
					return rs;
				}else {
					rs.setMensaje("El NRC no existe");
					rs.setSuccess(false);
					rs.setObj(docente);
					return rs;
				}
			}
		}else {
			if(docenteDao.existsById(docente.getId_docente())) {
				rs.setMensaje("EL docente ya existe");
				rs.setSuccess(false);
				rs.setObj(docente);
				return rs;
			}else {
				if(docente.getMateria()== null) {
					docenteDao.save(docente);
					rs.setMensaje("EL docente Fue registrado");
					rs.setSuccess(true);
					rs.setObj(docente);
					return rs;
				}else {
					if(materiaDao.existsById(docente.getMateria().getNrc())) {
						docenteDao.save(docente);
						rs.setMensaje("El docente fue registrado");
						rs.setSuccess(true);
						rs.setObj(docente);
						return rs;
					}else {
						rs.setMensaje("EL NRC no existe");
						rs.setSuccess(false);
						rs.setObj(docente);
						return rs;
					}
				}
			}
		}
	}

	@Override
	public Respuesta editar(Docente docente) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if(docente.getMateria() == null) {
			docenteDao.save(docente);
			rs.setMensaje("El docente fue editado");
			rs.setSuccess(true);
			rs.setObj(docente);
			return rs;
		}else {
			if(materiaDao.existsById(docente.getMateria().getNrc())) {
				docenteDao.save(docente);
				rs.setMensaje("El docente fue editado");
				rs.setSuccess(true);
				rs.setObj(docente);
				return rs;
			}else {
				rs.setMensaje("El NRC no existe");
				rs.setSuccess(false);
				rs.setObj(docente);
				return rs;
			}
		}
	}

	@Override
	public void eliminar(Docente docente) {
		// TODO Auto-generated method stub
		docente = docenteDao.findById(docente.getId_docente()).orElse(null);
		if(docente.getMateria()==null) {
			docenteDao.delete(docente);
		}else {
			docente.getMateria().getDocentes().remove(docente);
			docenteDao.delete(docente);
		}
	}

	@Override
	public Respuesta buscar(Docente docente) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		docente = docenteDao.findById(docente.getId_docente()).orElse(null);
		if(docente == null) {
			rs.setMensaje("El docente no existe");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
		}else {
			rs.setMensaje("El docente si existe");
			rs.setSuccess(true);
			rs.setObj(docente);
			return rs;
		}
	}

	@Override
	public Respuesta mostrar() {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if(docenteDao.findAll().isEmpty()) {
			rs.setMensaje("No exisyen docentes en la BD");
			rs.setSuccess(false);
			rs.setObj(null);
			return rs;
		}else {
			rs.setMensaje("Se encontraron los siguientes registros");
			rs.setSuccess(true);
			rs.setObj(docenteDao.findAll());
			return rs;
		}
	}
	
}
