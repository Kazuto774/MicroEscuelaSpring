package com.mx.Microservicioescula.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MATERIAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {
	@Id
	int nrc;
	String materia;
	int creditos;
	String nivel;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ALUMNOS3_MATERIAS",
	joinColumns = @JoinColumn(name = "NRC_MATERIA", referencedColumnName = "NRC"),
	inverseJoinColumns = @JoinColumn(name = "MATRICULA_ALUMNO", referencedColumnName = "MATRICULA"))
	List<Alumno> alumnos = new ArrayList<>();
	
	@OneToMany(mappedBy = "materia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	List<Docente> docentes = new ArrayList<>();
}

























