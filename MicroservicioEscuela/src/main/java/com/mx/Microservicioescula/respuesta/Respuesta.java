package com.mx.Microservicioescula.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
	String mensaje;
	boolean success;
	Object obj;
}
