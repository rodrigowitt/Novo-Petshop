package com.projeto.petShopAtividade.repositorios;

import com.projeto.petShopAtividade.modelos.AgendamentoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgendamentoRepositorio extends JpaRepository<AgendamentoModelo, Integer> {
}
