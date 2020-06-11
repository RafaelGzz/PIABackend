package com.backend.fcfm.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.backend.fcfm.validators.pagado;

@Entity
@Table(name = "prestamo")
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPrestamo;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@NotNull
	private Float monto;

	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date fechaCreacion;

	@Column(name = "fecha_expiracion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date fechaExpiracion;
	
	@NotNull
	@pagado
	private Integer expirado;

	@NotNull
	private Integer tipoPrestamo;
	
	@NotNull
	private Float abonoTotal;

	@NotNull
	@pagado
	private Integer pagado;
	
	public Prestamo() {
		this.pagado = 0;
		this.abonoTotal = 0f;
	}

	public Integer getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(Integer idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
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

	public Integer getExpirado() {
		return expirado;
	}

	public void setExpirado(Integer expirado) {
		this.expirado = expirado;
	}

	public void setPagado(Integer pagado) {
		this.pagado = pagado;
	}

	public Integer getTipoPrestamo() {
		return tipoPrestamo;
	}

	public void setTipoPrestamo(Integer tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	public Float getAbonoTotal() {
		return abonoTotal;
	}

	public void setAbonoTotal(Float abonoTotal) {
		this.abonoTotal = abonoTotal;
	}

	public int getPagado() {
		return pagado;
	}

	public void setPagado(int pagado) {
		this.pagado = pagado;
	}
}
