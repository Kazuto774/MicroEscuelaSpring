package com.mx.Microservicioescula.entidad;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ALUMNOS3")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
	@Id
	int matricula;
	/**
	 * 
	 */
	String nombre;
	String apellido_paterno;
	String apellido_materno;
	Date fecha_de_nacimiento;
	String ciudad;
	String email;
	
	@ManyToMany(mappedBy = "alumnos")
	@JsonIgnore
	List<Materia> materias = new ArrayList<>();
}
