package com.mx.Microservicioescula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Microservicioescula.entidad.Docente;
import com.mx.Microservicioescula.respuesta.Respuesta;
import com.mx.Microservicioescula.service.ImpDocente;

@RestController
@RequestMapping("docente")
@CrossOrigin
public class DocenteWs {
	@Autowired
	ImpDocente imp;
	
	@GetMapping("listar")
	public Respuesta mostrar() {
		return imp.mostrar();
	}
	
	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Docente docente) {
		return imp.guardar(docente);
	}
	
	@PostMapping("editar")
	public Respuesta editar(@RequestBody Docente docente) {
		return imp.editar(docente);
	}
	
	@PostMapping("eliminar")
	public void eliminar(@RequestBody Docente docente) {
		imp.eliminar(docente);
	}
	
	@PostMapping("buscar")
	public Respuesta buscar(@RequestBody Docente docente) {
		return imp.buscar(docente);
	}
}
