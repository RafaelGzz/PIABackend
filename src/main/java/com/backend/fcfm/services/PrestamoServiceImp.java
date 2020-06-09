package com.backend.fcfm.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.fcfm.entitys.Prestamo;
import com.backend.fcfm.models.dao.PrestamoDao;

public class PrestamoServiceImp implements PrestamoService {
	
	@Autowired
	private PrestamoDao	prestamoDao;

	@Override
	public void prestamo(Prestamo prestamo) {
		prestamoDao.insert(prestamo);
	}

	@Override
	public void abonar(Integer id, Long abono) {
		Prestamo prestamo = prestamoDao.find(id);
		prestamo.setAbonoTotal(prestamo.getAbonoTotal() - abono);
		prestamoDao.update(prestamo);
	}

}
