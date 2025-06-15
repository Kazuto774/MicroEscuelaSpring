package com.mx.Microservicioescula.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDto {
	int nrc;
	String materia;
	int creditos;
	String nivel;
}
