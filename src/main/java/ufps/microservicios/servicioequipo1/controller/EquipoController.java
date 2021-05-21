package ufps.microservicios.servicioequipo1.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ufps.microservicios.servicioequipo1.dao.ICategoriaDao;
import ufps.microservicios.servicioequipo1.dao.IEquipoDao;
import ufps.microservicios.servicioequipo1.models.Categoria;
import ufps.microservicios.servicioequipo1.models.Club;
import ufps.microservicios.servicioequipo1.models.Equipo;
import ufps.microservicios.servicioequipo1.service.ClubService;
import ufps.microservicios.servicioequipo1.service.IUploadService;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

	@Autowired
	private IEquipoDao equipoDao;
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Autowired
	private ClubService club;
	
	@Autowired
	private IUploadService upload;
	
	@GetMapping("/listar")
	public List<Club> listar(){
		List<Equipo> equipos=(List<Equipo>)this.equipoDao.findAll();
		List<Club> map=new ArrayList<>();
		
		for(Equipo e:equipos) {
			
			ResponseEntity<?> res =this.listarByid(e.getClub());
			if(res.getStatusCodeValue()==404) {
				continue;
			}else {
				Club cl=new Club();
				cl.setClub(res.getBody());
				cl.setEqupo(e);
				
				map.add(cl);
			}
			
			
		}
		
		return map;
	}
	
	@PostMapping("/save/{id}")
	public ResponseEntity<?> save(@PathVariable int id,@ModelAttribute Equipo equipo,@RequestParam MultipartFile file){
		
		Categoria cate=this.categoriaDao.findById(id).orElse(null);
		
		Map<String, Object> map=new HashMap<>();
		ResponseEntity<?> re=this.listarByid(equipo.getClub());
		
		if(re.getStatusCodeValue()==404)
			return re;
				
		if(cate==null) {
			map.put("mensaje", "La categoria no fue encontrada");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			String foto=this.upload.copy(file);
			
			equipo.setCategoria(cate);
			equipo.setEscudo(foto);
			Equipo e=this.equipoDao.save(equipo);
			
			Club c=new Club();
			c.setClub(re.getBody());
			c.setEqupo(e);
			
			map.put("mensaje", "Equipo agregado correctamente");
			map.put("equipo", c);
	
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
			
		}catch(DataAccessException e) {
			map.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		} catch (IOException e1) {
			map.put("error", e1.getMessage());
			map.put("mensaje", "No se pudo subir el file");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		
	}
	
	@GetMapping("/club")
	
	public ResponseEntity<?> listarClubes(){
		
	return this.club.listar();
	}
	
	@GetMapping("/club/{id}")
	public ResponseEntity<?> listarByid(@PathVariable int id){
		return this.club.listarById(id);
		
		
	}
}
