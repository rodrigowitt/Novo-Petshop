package com.projeto.petShopAtividade.servicos;

import com.projeto.petShopAtividade.modelos.ClientePetshopModelo;
import com.projeto.petShopAtividade.modelos.PetshopModelo;
import com.projeto.petShopAtividade.repositorios.ClientePetshopRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ClientePetshopServico {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    public List<ClientePetshopModelo> clientesMaisRecentes() {
        String sql = "SELECT *\n" +
                "FROM tb_cliente_petshop\n" +
                "ORDER BY entrada DESC";


        List <ClientePetshopModelo>  resultado= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClientePetshopModelo.class));
        return resultado;
    }


}
