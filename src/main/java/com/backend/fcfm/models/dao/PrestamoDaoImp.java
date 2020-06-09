package com.backend.fcfm.models.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.fcfm.entitys.Prestamo;

@Repository
public class PrestamoDaoImp implements PrestamoDao {

	@Autowired
	private EntityManager en;

	@Override
	public List<Prestamo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prestamo find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Prestamo nuevo) {
		en.persist(nuevo);
	}

	@Override
	public void update(Prestamo nuevo) {
		Prestamo antes = find(nuevo.getIdPrestamo());
		BeanUtils.copyProperties(nuevo, antes);
		en.flush();

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
