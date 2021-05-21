package ufps.microservicios.servicioequipo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ufps.microservicios.servicioequipo1.clientes.ClubClienteRest;

@Service
public class ClubServiceImple implements ClubService {

	@Autowired
	private ClubClienteRest club;
	
	@Override
	public ResponseEntity<?> listar() {
		return this.club.listar();
	}

	@Override
	public ResponseEntity<?> listarById(int id) {
		return this.club.listarById(id);
	}

}
