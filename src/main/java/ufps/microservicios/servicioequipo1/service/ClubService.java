package ufps.microservicios.servicioequipo1.service;

import org.springframework.http.ResponseEntity;



public interface ClubService {

	public ResponseEntity<?> listar();
	public ResponseEntity<?> listarById(int id);
}
