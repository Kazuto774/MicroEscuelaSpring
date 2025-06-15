package com.mx.Microservicioescula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mx.Microservicioescula.entidad.Materia;
import com.mx.Microservicioescula.peticion.PeticionAlumnoMateria;
import com.mx.Microservicioescula.respuesta.Respuesta;
import com.mx.Microservicioescula.service.ImpMaterias;


@RestController
@RequestMapping("materia")
@CrossOrigin
public class MateriaWs {

	@Autowired
	ImpMaterias imp;
	
	@GetMapping("listar")
	public Respuesta mostrar() {
		return imp.mostrar();
	}
	
	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Materia materia) {
		return imp.guardar(materia);
	}
	
	@PostMapping("editar")
	public void editar(@RequestBody Materia materia) {
		imp.editar(materia);
	}
	
	@PostMapping("eliminar")
	public Respuesta eliminar(@RequestBody Materia materia) {
		return imp.eliminar(materia);
	}
	
	@PostMapping("buscar")
	public Respuesta buscar(@RequestBody Materia materia) {
		return imp.buscar(materia);
	}
	
	@PostMapping("mostrarAlumnos")
	public Respuesta mostrarAlumnos(@RequestBody Materia materia) {
		return imp.mostrarAlumnos(materia);
	}
	
	@PostMapping("mostrarDocentes")
	public Respuesta mostrarDocentes(@RequestBody Materia materia) {
		return imp.mostrarDocentes(materia);
	}
	
	@PostMapping("asignar")
	public Respuesta asignar(@RequestBody PeticionAlumnoMateria am) {
		return imp.asignar(am);
	}
	
	@PostMapping("darDeBaja")
	public Respuesta darDeBaja(@RequestBody PeticionAlumnoMateria am) {
		return imp.darDeBajaAlumno(am);
	}
}
