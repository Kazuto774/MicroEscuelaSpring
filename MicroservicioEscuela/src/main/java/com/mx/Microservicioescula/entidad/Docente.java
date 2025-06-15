package com.mx.Microservicioescula.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DOCENTES2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente {
	@Id
	int id_docente;
	String nombre;
	String apellido_paterno;
	String apellido_materno;
	String ciudad;
	String especialidad;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NRC_MATERIA")
	Materia materia;
	
}
