package com.mx.Microservicioescula.service;

import com.mx.Microservicioescula.entidad.Docente;
import com.mx.Microservicioescula.respuesta.Respuesta;

public interface MetodosDocente {
	public Respuesta guardar(Docente docente);
	
	public Respuesta editar(Docente docente);
	
	public void eliminar(Docente docente);
	
	public Respuesta buscar(Docente docente);
	
	public Respuesta mostrar();
}
