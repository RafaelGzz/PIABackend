package com.backend.fcfm.models.dao;

import java.util.List;
import com.backend.fcfm.entitys.Cliente;


public interface ClienteDao {
	List<Cliente> findAll();
	Cliente find(Integer id);
	void insert(Cliente nuevo);
	void update(Cliente nuevo);
	void delete(Integer id);
	Cliente login(String user, String password);
	Cliente mayCliente();
	Long totalMoney();
}


