package com.projeto.petShopAtividade.controle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projeto.petShopAtividade.dto.ClientePetshopDto;
import com.projeto.petShopAtividade.dto.PetshopDto;
import com.projeto.petShopAtividade.enums.StatusTratamento;
import com.projeto.petShopAtividade.modelos.ClientePetshopModelo;
import com.projeto.petShopAtividade.modelos.PetshopModelo;
import com.projeto.petShopAtividade.servicos.ClientePetshopServico;
import com.projeto.petShopAtividade.servicos.PetshopServico;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/petshop")
public class PetshopControle {

    final PetshopServico petshopServico;


    @Autowired
    ClientePetshopServico clientePetshopServico;


    private JavaMailSender mailSender;

    public PetshopControle(PetshopServico petshopServico, JavaMailSender mailSender) {
        this.petshopServico = petshopServico;
        this.mailSender = mailSender;
    }

    @PostMapping
    public ResponseEntity<Object> savePetshop(
            @RequestBody
            @Valid
            PetshopDto petshopDto , ClientePetshopDto clientePetshopDto) {

        var petshopModelo = new PetshopModelo();
        BeanUtils.copyProperties(petshopDto, petshopModelo);

        petshopModelo.setEntrada(LocalDateTime.from(LocalDateTime.now()));
        petshopModelo.setStatusTratamento(String.valueOf(StatusTratamento.PREPARANDO));
        String entradaMail = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss"));
        System.out.println(entradaMail);
        Optional<ClientePetshopModelo> clienteModelo = clientePetshopServico.findById(petshopDto.getClienteid());
        petshopModelo.setClientePetshopModelo(clienteModelo.get());

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText("Olá "+petshopDto.getNome()+ ", o seu pet "+ petshopDto.getNome()+" foi cadastrado as "+ entradaMail + " Obrigado!");
//        message.setTo(clientePetshopDto.getEmail());
//        message.setFrom("rodrigowitthoeft95@gmail.com");
//        message.setSubject("Petshop");
//
//        try {
//            mailSender.send(message);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body(petshopServico.save(petshopModelo));
    }

    @GetMapping
    public ResponseEntity<List<PetshopModelo>> getAllPetshop() {
        //System.out.println(petshopServico.calcularSoma());
        return ResponseEntity.status(HttpStatus.OK).body(petshopServico.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getIdPetshop(@PathVariable(value = "id") UUID id) {
        Optional<PetshopModelo> petshopModeloOptional = petshopServico.findById(id);

        if (!petshopModeloOptional.isPresent()) {
            //Retorna com o status Ok e um Json vazio para evitar erros no javascript.
            return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.OK).body(petshopModeloOptional.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePetshop(@PathVariable(value = "id") UUID id) {
        Optional<PetshopModelo> petshopModeloOptional = petshopServico.findById(id);
        if (!petshopModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        } else {
            petshopServico.delete(petshopModeloOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @JsonSerialize
    public class EmptyJsonResponse { }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaPetshop(@PathVariable(value = "id") UUID id, @RequestBody @Valid PetshopDto petshopDto,ClientePetshopDto clientePetshopDto ) {

        Optional<PetshopModelo> petshopModeloOptional = petshopServico.findById(id);
        if (!petshopModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }

        var petshopModelo = petshopModeloOptional.get();

        BeanUtils.copyProperties(petshopDto, petshopModelo);

        petshopModelo.setId(petshopModeloOptional.get().getId());

        if(!petshopModelo.getStatusTratamento().equals(("PREPARANDO"))) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("Olá " + petshopDto.getNome() + ", houve uma atualização no tratamento "+petshopModelo.getTratamento() +" para " + petshopModelo.getStatusTratamento() + " do seu pet " + petshopDto.getNome() + "!");
            message.setTo(clientePetshopDto.getEmail());
            message.setFrom("rodrigowitthoeft95@gmail.com");
            message.setSubject("Petshop");
            mailSender.send(message);
        }


            return ResponseEntity.status(HttpStatus.OK).body(petshopServico.save(petshopModelo));

    }


}
