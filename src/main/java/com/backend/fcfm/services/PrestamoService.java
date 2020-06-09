package com.backend.fcfm.services;

import com.backend.fcfm.entitys.Prestamo;

public interface PrestamoService {
	
	public void abonar(Integer id, Long abono);
	
	public void prestamo(Prestamo prestamo);
}
