package com.clientapp.web.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientapp.web.models.entity.ClienteDireccion;
import com.clientapp.web.models.repository.ClienteDireccionRepository;

@Service
public class ClienteDireccionServiceImpl implements IClienteDireccionService {
	
	@Autowired
	private ClienteDireccionRepository clienteDireccionRepository;

	@Override
	public List<ClienteDireccion> listar() {
		// TODO Auto-generated method stub
		return (List<ClienteDireccion>) clienteDireccionRepository.findAll();
	}

	@Override
	public void guardar(ClienteDireccion clienteDireccion) {
		clienteDireccionRepository.save(clienteDireccion);

	}

	@Override
	public ClienteDireccion buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return clienteDireccionRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		clienteDireccionRepository.deleteById(id);

	}

}
