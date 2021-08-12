package com.clientapp.web.models.service;

import java.util.List;

import com.clientapp.web.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> listar();
	public void guardar(Cliente cliente);
	public Cliente buscarPorId(Long id);
	public void eliminar(Long id);
}
