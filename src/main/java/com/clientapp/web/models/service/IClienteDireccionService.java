package com.clientapp.web.models.service;

import java.util.List;


import com.clientapp.web.models.entity.ClienteDireccion;

public interface IClienteDireccionService {

	public List<ClienteDireccion> listar();
	
	public void guardar(ClienteDireccion clienteDireccion);
	public ClienteDireccion buscarPorId(Long id);
	public void eliminar(Long id);
}
