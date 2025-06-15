package com.mx.Microservicioescula.service;

import com.mx.Microservicioescula.entidad.Materia;
import com.mx.Microservicioescula.respuesta.Respuesta;

public interface MetodosMaterias {
	public Respuesta guardar(Materia materia);
	
	public void editar(Materia materia);
	
	public Respuesta eliminar(Materia materia);
	
	public Respuesta buscar(Materia materia);
	
	public Respuesta mostrar();
}
