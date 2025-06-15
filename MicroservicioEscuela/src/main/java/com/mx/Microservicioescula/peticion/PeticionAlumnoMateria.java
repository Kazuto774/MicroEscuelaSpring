package com.mx.Microservicioescula.peticion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeticionAlumnoMateria {
	int matricula_alumno;
	int nrc_materia;
}
