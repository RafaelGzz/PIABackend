package com.backend.fcfm.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Cliente {

	@NotNull
	private int idCliente;
	
	@NotEmpty
	private String nombreCompleto;
	
	@NotEmpty
	private String correo;
	
	@NotNull
	private Long telefono;
	
	@NotEmpty
	private String direccion;
	
	@NotNull
	private int monto;
	
	public Cliente() {
		
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	
	
}
