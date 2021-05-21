package ufps.microservicios.servicioequipo1.dao;

import org.springframework.data.repository.CrudRepository;

import ufps.microservicios.servicioequipo1.models.Equipo;

public interface IEquipoDao extends CrudRepository<Equipo, Integer> {

}
