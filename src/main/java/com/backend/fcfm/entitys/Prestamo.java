package com.backend.fcfm.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;


import com.backend.fcfm.models.Cliente;

@Entity
@Table(name="prestamo")
public class Prestamo {

	@Id
	private Cliente idCliente;

	@Column(name="monto")
	private Cliente monto;
	
	@Column(name="fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date fechaCreacion;
	
	@Column(name="fecha_expiracion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date fechaExpiracion;
	
	@NotEmpty
	private String tipoPrestamo;
	@NotNull
	private int abonoTotal;
	@NotNull
	private int pagado;
	
	
	public Cliente getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	
	public Cliente getMonto() {
		return monto;
	}
	public void setMonto(Cliente monto) {
		this.monto = monto;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}
	public int getAbonoTotal() {
		return abonoTotal;
	}
	public void setAbonoTotal(int abonoTotal) {
		this.abonoTotal = abonoTotal;
	}
	public int getPagado() {
		return pagado;
	}
	public void setPagado(int pagado) {
		this.pagado = pagado;
	}
}
