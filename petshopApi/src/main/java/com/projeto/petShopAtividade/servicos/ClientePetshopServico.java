package com.projeto.petShopAtividade.servicos;

import com.projeto.petShopAtividade.modelos.ClientePetshopModelo;
import com.projeto.petShopAtividade.modelos.PetshopModelo;
import com.projeto.petShopAtividade.repositorios.ClientePetshopRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientePetshopServico {

    final ClientePetshopRepositorio clientePetshopRepositorio;

    public ClientePetshopServico(ClientePetshopRepositorio clientePetshopRepositorio) {
        this.clientePetshopRepositorio = clientePetshopRepositorio;
    }

    public ClientePetshopModelo save(ClientePetshopModelo clientePetshopModelo) {
        return clientePetshopRepositorio.save(clientePetshopModelo);
    }

    public List<ClientePetshopModelo> findAll() {
        return clientePetshopRepositorio.findAll();
    }

    public Optional<ClientePetshopModelo> findById(UUID id) {
        return clientePetshopRepositorio.findById(id);
    }

    public void delete(ClientePetshopModelo clientePetshopModelo) {
        clientePetshopRepositorio.delete(clientePetshopModelo);
    }


}
