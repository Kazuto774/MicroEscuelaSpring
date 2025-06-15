package com.mx.Microservicioescula.service;

import com.mx.Microservicioescula.entidad.Alumno;
import com.mx.Microservicioescula.respuesta.Respuesta;

public interface MetodosAlumnos {
	public Respuesta guardar(Alumno alumno);
	
	public void editar(Alumno alumno);
	
	public void eliminar(Alumno alumno);
	
	public Respuesta buscar(Alumno alumno);
	
	public Respuesta mostrar();
	
}
