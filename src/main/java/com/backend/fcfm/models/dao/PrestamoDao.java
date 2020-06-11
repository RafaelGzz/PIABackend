package com.backend.fcfm.models.dao;

import java.util.List;

import com.backend.fcfm.entitys.Prestamo;

public interface PrestamoDao {
	
	List<Prestamo> findAll();
	Prestamo find(Integer id);
	void insert(Prestamo nuevo);
	void update(Prestamo nuevo);
	void delete(Integer id);
	List<Prestamo> findFecha(String fechaInicio, String fechaFin);
	List<Prestamo> findPorPagado(Integer opcion);
	List<Prestamo> findByClient(Integer id);
}
