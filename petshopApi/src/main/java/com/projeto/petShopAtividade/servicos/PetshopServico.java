package com.projeto.petShopAtividade.servicos;

import com.projeto.petShopAtividade.modelos.PetshopModelo;
import com.projeto.petShopAtividade.repositorios.PetshopRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetshopServico {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final PetshopRepositorio petshopRepositorio;




    public PetshopServico(PetshopRepositorio petshopRepositorio) {
        this.petshopRepositorio = petshopRepositorio;
    }


    public PetshopModelo save(PetshopModelo petshopModelo) {
        return petshopRepositorio.save(petshopModelo);
    }

    public List<PetshopModelo> findAll() {
        return petshopRepositorio.findAll();
    }

    public Optional<PetshopModelo> findById(UUID id) {
        return petshopRepositorio.findById(id);
    }

    public void delete(PetshopModelo petshopModelo) {
        petshopRepositorio.delete(petshopModelo);
    }

    public int calcularSoma() {
        String sql = "SELECT SUM(valor) FROM tb_petshop";
        Integer soma = jdbcTemplate.queryForObject(sql, Integer.class);
        return soma != null ? soma : 0;
    }


}
