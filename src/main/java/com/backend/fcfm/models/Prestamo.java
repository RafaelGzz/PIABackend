package com.backend.fcfm.models;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Prestamo {
	
	
	private Cliente idCliente;
	private Cliente monto;
	
	@NotNull
	private Date fechaCreacion;
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
