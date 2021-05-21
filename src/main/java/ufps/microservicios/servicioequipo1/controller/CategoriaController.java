package ufps.microservicios.servicioequipo1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufps.microservicios.servicioequipo1.dao.ICategoriaDao;
import ufps.microservicios.servicioequipo1.models.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoriaDao categoriaDao;
	

	@PostMapping("/save")
	public ResponseEntity<?> guardar(@RequestBody Categoria categoria) {
		
		Map<String, Object> map =new HashMap<>();
		try {
			map.put("categoria", this.categoriaDao.save(categoria));
			map.put("mensaje","categoria agregada correctamente");
			
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
		}catch(DataAccessException e) {
			map.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	@GetMapping("/listar")
	public List<Categoria> listar(){
		return (List<Categoria>)this.categoriaDao.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id){
		
		Map<String, Object> map =new HashMap<>();
		try {
			
			this.categoriaDao.deleteById(id);
			map.put("mensaje","Eliminado Correctamente");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			
		}catch(DataAccessException e) {
			
			map.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.NOT_FOUND);
		}
	}
}
