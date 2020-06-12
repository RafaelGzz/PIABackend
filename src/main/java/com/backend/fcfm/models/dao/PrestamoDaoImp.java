package com.backend.fcfm.models.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.fcfm.entitys.Prestamo;

@Repository
public class PrestamoDaoImp implements PrestamoDao {

	@Autowired
	private EntityManager en;

	@SuppressWarnings("unchecked")
	@Transactional()
	@Override
	public List<Prestamo> findAll() {
		List<Prestamo> result = en.createQuery("from Prestamo prestamo order by prestamo.monto desc").getResultList();
		Date date = new Date();
		for (Prestamo p : result) {
			if (date.after(p.getFechaExpiracion()) && p.getExpirado() == 0) {
				p.setExpirado(1);
				p.setMonto(p.getMonto()*1.10f);
				update(p);
			} 
		}
		return result;
	}

	@Transactional
	@Override
	public Prestamo find(Integer id) {
		Prestamo result = en.find(Prestamo.class, id);
		return result;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Prestamo> findFecha(String fechaInicio, String fechaFin) {
		List<Prestamo> result = en
				.createQuery("SELECT prestamo FROM Prestamo prestamo where prestamo.fechaCreacion between '"
						+ fechaInicio + "' and '" + fechaFin + "' order by prestamo.monto desc")
				.getResultList();
		return result;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Prestamo> findPorPagado(Integer opcion) {
		Query q = en.createQuery(
				"SELECT prestamo from Prestamo prestamo where prestamo.pagado = :pagado order by prestamo.monto desc");
		q.setParameter("pagado", opcion);
		List<Prestamo> result = q.getResultList();
		return result;
	}

	@Transactional
	@Override
	public void insert(Prestamo nuevo) {
		if (nuevo.getIdPrestamo() != null && nuevo.getIdPrestamo() > 0) {
			en.merge(nuevo);
		} else {
			en.persist(nuevo);
		}
		en.flush();

	}

	@Transactional
	@Override
	public void update(Prestamo nuevo) {
		Prestamo antes = find(nuevo.getIdPrestamo());
		BeanUtils.copyProperties(nuevo, antes);
		en.flush();

	}

	@Transactional
	@Override
	public void delete(Integer id) {
		Prestamo entity = find(id);
		en.remove(entity);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Prestamo> findByClient(Integer id) {
		List<Prestamo> result = en.createQuery(
				"Select prestamo from Prestamo prestamo where prestamo.cliente.idCliente = ?1 order by prestamo.monto desc")
				.setParameter(1, id).getResultList();
		Date date = new Date();
		for (Prestamo p : result) {
			if (date.after(p.getFechaExpiracion()) && p.getExpirado() == 0) {
				p.setExpirado(1);
				p.setMonto(p.getMonto()*1.10f);
				update(p);
			} 
		}
		return result;
	}

}
