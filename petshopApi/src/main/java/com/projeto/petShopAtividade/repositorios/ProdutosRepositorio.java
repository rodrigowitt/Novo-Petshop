package com.projeto.petShopAtividade.repositorios;

import com.projeto.petShopAtividade.modelos.ProdutosModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepositorio  extends JpaRepository<ProdutosModelo, Integer> {
}
