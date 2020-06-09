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
	private Long monto;

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
	private Integer tipoPrestamo;
	
	@NotNull
	private Long abonoTotal;

	@NotNull
	@pagado
	private Integer pagado;
	
	public Prestamo() {
		this.pagado = 0;
		this.abonoTotal = 0l;
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

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
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

	public Integer getTipoPrestamo() {
		return tipoPrestamo;
	}

	public void setTipoPrestamo(Integer tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	public Long getAbonoTotal() {
		return abonoTotal;
	}

	public void setAbonoTotal(Long abonoTotal) {
		this.abonoTotal = abonoTotal;
	}

	public int getPagado() {
		return pagado;
	}

	public void setPagado(int pagado) {
		this.pagado = pagado;
	}
}
