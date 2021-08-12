package com.clientapp.web.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="clientes_direcciones")
public class ClienteDireccion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	
	private String direcciones;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public String getDirecciones() {
		return direcciones;
	}


	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
	}


	@Override
	public String toString() {
		return "ClienteDireccion [id=" + id + ", cliente=" + cliente + ", direcciones=" + direcciones + "]";
	}
	
	

}
