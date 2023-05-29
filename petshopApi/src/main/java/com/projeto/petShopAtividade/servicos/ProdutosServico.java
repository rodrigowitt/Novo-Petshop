package com.projeto.petShopAtividade.servicos;

import com.projeto.petShopAtividade.modelos.ProdutosModelo;
import com.projeto.petShopAtividade.repositorios.ProdutosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutosServico {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    final ProdutosRepositorio produtosRepositorio;

    public ProdutosServico(ProdutosRepositorio produtosRepositorio) {
        this.produtosRepositorio = produtosRepositorio;
    }

    public ProdutosModelo save(ProdutosModelo produtosModelo) {
        return produtosRepositorio.save(produtosModelo);
    }

    public List<ProdutosModelo> findAll() {
        return produtosRepositorio.findAll();
    }

    public Optional<ProdutosModelo> findById(Integer id) {
        return produtosRepositorio.findById(id);
    }

    public void delete(ProdutosModelo produtosModelo) {
        produtosRepositorio.delete(produtosModelo);
    }
}
