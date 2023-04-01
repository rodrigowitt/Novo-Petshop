package com.projeto.petShopAtividade.repositorios;

import com.projeto.petShopAtividade.modelos.PetshopModelo;
import jakarta.mail.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PetshopRepositorio extends JpaRepository<PetshopModelo, UUID> {



}
