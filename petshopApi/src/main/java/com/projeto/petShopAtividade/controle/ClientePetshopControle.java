package com.projeto.petShopAtividade.controle;

import com.projeto.petShopAtividade.dto.ClientePetshopDto;
import com.projeto.petShopAtividade.dto.PetshopDto;
import com.projeto.petShopAtividade.enums.StatusTratamento;
import com.projeto.petShopAtividade.modelos.ClientePetshopModelo;
import com.projeto.petShopAtividade.modelos.PetshopModelo;
import com.projeto.petShopAtividade.servicos.ClientePetshopServico;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clientes")
public class ClientePetshopControle {

    final ClientePetshopServico clientePetshopServico;


    public ClientePetshopControle(ClientePetshopServico clientePetshopServico) {
        this.clientePetshopServico = clientePetshopServico;
    }

    @PostMapping
    public ResponseEntity<Object> savePetshop(
            @RequestBody
            @Valid
            ClientePetshopDto clientePetshopDto) {

        var clientePetshopModelo = new ClientePetshopModelo();
        BeanUtils.copyProperties(clientePetshopDto, clientePetshopModelo);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientePetshopServico.save(clientePetshopModelo));
    }

    @GetMapping
    public ResponseEntity<List<ClientePetshopModelo>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clientePetshopServico.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaCliente(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientePetshopDto clientePetshopDto ) {

        Optional<ClientePetshopModelo> petshopClienteModeloOptional = clientePetshopServico.findById(id);
        if (!petshopClienteModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }

        var clientePetshopModelo = petshopClienteModeloOptional .get();

        BeanUtils.copyProperties(clientePetshopDto, clientePetshopModelo);

        clientePetshopModelo.setId(petshopClienteModeloOptional.get().getId());

//        if(!petshopModelo.getStatusTratamento().equals(("PREPARANDO"))) {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setText("Olá " + petshopDto.getNome() + ", houve uma atualização no tratamento "+petshopModelo.getTratamento() +" para " + petshopModelo.getStatusTratamento() + " do seu pet " + petshopDto.getNome() + "!");
//            message.setTo(clientePetshopDto.getEmail());
//            message.setFrom("rodrigowitthoeft95@gmail.com");
//            message.setSubject("Petshop");
//            mailSender.send(message);
//        }


        return ResponseEntity.status(HttpStatus.OK).body(clientePetshopServico.save(clientePetshopModelo));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") UUID id) {
        Optional<ClientePetshopModelo> petshopClienteModeloOptional = clientePetshopServico.findById(id);
        if (!petshopClienteModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        } else {
            clientePetshopServico.delete(petshopClienteModeloOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
