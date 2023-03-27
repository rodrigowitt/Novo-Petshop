package com.projeto.petShopAtividade.repositorios;

import com.projeto.petShopAtividade.modelos.PetshopModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Repository
public interface PetshopRepositorio extends JpaRepository<PetshopModelo, UUID> {



}
