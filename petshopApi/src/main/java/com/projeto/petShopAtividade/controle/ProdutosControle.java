package com.projeto.petShopAtividade.controle;

import com.projeto.petShopAtividade.dto.AgendamentoDto;
import com.projeto.petShopAtividade.dto.ProdutoDto;
import com.projeto.petShopAtividade.modelos.AgendamentoModelo;
import com.projeto.petShopAtividade.modelos.ProdutosModelo;
import com.projeto.petShopAtividade.servicos.ProdutosServico;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/produtos")
public class ProdutosControle {

    final ProdutosServico produtosServico;

    public ProdutosControle(ProdutosServico produtosServico) {
        this.produtosServico = produtosServico;
    }

    @PostMapping
    public ResponseEntity<Object> saveProdutos(
            @RequestBody
            @Valid
            ProdutoDto produtoDto){

        var produtosModelo = new ProdutosModelo();
        BeanUtils.copyProperties(produtoDto, produtosModelo);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtosServico.save(produtosModelo));
    }
    @GetMapping
    public ResponseEntity<List<ProdutosModelo>> getAllPRodutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtosServico.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaProdutos(@PathVariable(value = "id") Integer id, @RequestBody @Valid ProdutoDto produtoDto ) {

        Optional<ProdutosModelo> produtoModeloOptional = produtosServico.findById(id);
        if (!produtoModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PRoduto não encontrado");
        }

        var produtoPetshopModelo = produtoModeloOptional.get();

        BeanUtils.copyProperties(produtoDto, produtoPetshopModelo);

        return ResponseEntity.status(HttpStatus.OK).body(produtosServico.save(produtoPetshopModelo));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id") Integer id) {
        Optional<ProdutosModelo> produtoModeloOptional = produtosServico.findById(id);
        if (!produtoModeloOptional .isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        } else {
            produtosServico.delete(produtoModeloOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
