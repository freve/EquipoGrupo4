package ufps.microservicios.servicioequipo1.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "servicio-club",url="localhost:8001/club",decode404 = true)
public interface ClubClienteRest {

	@GetMapping("/listar")
	public ResponseEntity<?> listar();
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarById(@PathVariable int id);
}
