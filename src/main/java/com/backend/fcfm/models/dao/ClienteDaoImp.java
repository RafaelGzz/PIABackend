package com.backend.fcfm.models.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.fcfm.entitys.Cliente;

@Repository
public class ClienteDaoImp implements ClienteDao {
	
	@Autowired
	private EntityManager en;

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Cliente nuevo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Cliente nuevo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cliente login(String user, String password) {
		Cliente cliente;
		try{
			cliente = (Cliente) en.createQuery("SELECT cliente from Cliente cliente where cliente.user = ?1 and cliente.password = ?2").setParameter(1, user).setParameter(2, password).getSingleResult();
		}catch(Exception e) {
			cliente = null;
		}
		return cliente;
	}

}
