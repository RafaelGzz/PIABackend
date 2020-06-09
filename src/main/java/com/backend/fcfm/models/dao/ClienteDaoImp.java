package com.backend.fcfm.models.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.fcfm.entitys.Cliente;

@Repository
public class ClienteDaoImp implements ClienteDao {
	
	@Autowired
	private EntityManager en;
    
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		List<Cliente> result = en.createQuery("from Cliente").getResultList();
		return result;
	}

	@Transactional
	@Override
	public Cliente find(Integer id) {
		Cliente result = en.find(Cliente.class, id);
		return result;
	}
	
	@Transactional
	@Override
	public void insert(Cliente nuevo) {
		if(nuevo.getIdCliente() != null && nuevo.getIdCliente() > 0) {
			en.merge(nuevo);
		}else {
			en.persist(nuevo);
		}
		en.flush();

	}
    
	@Transactional
	@Override
	public void update(Cliente nuevo) {
		Cliente antes = find(nuevo.getIdCliente());
	
		BeanUtils.copyProperties(nuevo, antes);

		en.flush();

	}

	@Transactional
	@Override
	public void delete(Integer id) {
		Cliente entity = find(id);
		en.remove(entity);

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
