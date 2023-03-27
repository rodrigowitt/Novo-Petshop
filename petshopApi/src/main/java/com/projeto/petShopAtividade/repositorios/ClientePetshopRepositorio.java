package com.projeto.petShopAtividade.repositorios;

import com.projeto.petShopAtividade.modelos.ClientePetshopModelo;
import com.projeto.petShopAtividade.modelos.PetshopModelo;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientePetshopRepositorio extends JpaRepository<ClientePetshopModelo, UUID> {

    Optional<ClientePetshopModelo> findById(java.util.UUID id);
}
