package com.projeto.petShopAtividade.controle;

import com.projeto.petShopAtividade.dto.AgendamentoDto;
import com.projeto.petShopAtividade.dto.ClientePetshopDto;
import com.projeto.petShopAtividade.modelos.AgendamentoModelo;
import com.projeto.petShopAtividade.modelos.ClientePetshopModelo;
import com.projeto.petShopAtividade.servicos.AgendamentoServico;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agendamento")
public class AgendamentoControle {

    final AgendamentoServico agendamentoServico;

    public AgendamentoControle(AgendamentoServico agendamentoServico) {
        this.agendamentoServico = agendamentoServico;
    }
    @PostMapping
    public ResponseEntity<Object> saveAgendamento(
            @RequestBody
            @Valid
            AgendamentoDto agendamentoDto){

        var agendamentoModelo = new AgendamentoModelo();
        BeanUtils.copyProperties(agendamentoDto, agendamentoModelo);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoServico.save(agendamentoModelo));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoModelo>> getAllAgendamento() {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoServico.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaAgendamento(@PathVariable(value = "id") Integer id, @RequestBody @Valid AgendamentoDto agendamentoDto ) {

        Optional<AgendamentoModelo> agendamentoModeloOptional = agendamentoServico.findById(id);
        if (!agendamentoModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
        }

        var agendamentoPetshopModelo = agendamentoModeloOptional.get();

        BeanUtils.copyProperties(agendamentoDto, agendamentoPetshopModelo);

        return ResponseEntity.status(HttpStatus.OK).body(agendamentoServico.save(agendamentoPetshopModelo));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAgendamento(@PathVariable(value = "id") Integer id) {
        Optional<AgendamentoModelo> agendamentoModeloOptional = agendamentoServico.findById(id);
        if (!agendamentoModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        } else {
            agendamentoServico.delete(agendamentoModeloOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @GetMapping(value = "/recentes")
    public ResponseEntity<List<AgendamentoModelo>> getAgendamentoRecentesPetshop(){
        System.out.println("Executando mais recentes");

        return ResponseEntity.status(HttpStatus.OK).body(agendamentoServico.agendamentoMaisRecentes());
    }
}
