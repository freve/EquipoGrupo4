package ufps.microservicios.servicioequipo1.dao;

import org.springframework.data.repository.CrudRepository;

import ufps.microservicios.servicioequipo1.models.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Integer>{

}
