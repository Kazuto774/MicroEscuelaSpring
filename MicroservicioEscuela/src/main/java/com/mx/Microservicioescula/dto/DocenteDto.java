package com.mx.Microservicioescula.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDto {
	int id_docente;
	String nombre;
	String apellido_paterno;
	String apellido_materno;
	String ciudad;
	String especialidad;
}
