package com.mx.Microservicioescula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Microservicioescula.entidad.Alumno;
import com.mx.Microservicioescula.respuesta.Respuesta;
import com.mx.Microservicioescula.service.ImpAlumno;

@RestController
@RequestMapping("alumno")
@CrossOrigin
public class AlumnoWs {
	@Autowired
	ImpAlumno imp;
	
	@GetMapping("listar")
	public Respuesta mostrar() {
		return imp.mostrar();
	}
	
	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Alumno alumno) {
		return imp.guardar(alumno);
	}
	
	@PostMapping("editar")
	public void editar(@RequestBody Alumno alumno) {
		imp.editar(alumno);
	}
	
	@PostMapping("eliminar")
	public void eliminar(@RequestBody Alumno alumno) {
		imp.eliminar(alumno);
	}
	@PostMapping("buscar")
	public Respuesta buscar(@RequestBody Alumno alumno) {
		return imp.buscar(alumno);
	}
	@PostMapping("mostrarMaterias")
	public Respuesta mostrarMaterias(@RequestBody Alumno alumno) {
		return imp.materiasDeAlumno(alumno);
	}
}
