package com.backend.fcfm.models.dao;

import java.util.List;

import com.backend.fcfm.entitys.Cliente;


public interface ClienteDao {
	List<Cliente> findAll();
	Cliente find(Long id);
	void insert(Cliente nuevo);
	void update(Cliente nuevo);
	void delete(Long id);
	Cliente login(String user, String password);
}
