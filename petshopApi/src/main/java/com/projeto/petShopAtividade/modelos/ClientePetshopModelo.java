package com.projeto.petShopAtividade.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="TB_CLIENTE_PETSHOP")
public class ClientePetshopModelo {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    @Column
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime entrada;

    private String nome;

    private String telefone;

    private String email;

    private String cpf;

    private String nascimento;

    private String rua;
    private String cidade;
    private String bairro;

    private String cep;


    @OneToMany(mappedBy = "clientePetshopModelo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PetshopModelo> petshopModelo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<PetshopModelo> getPetshopModelo() {
        return petshopModelo;
    }

    public void setPetshopModelo(List<PetshopModelo> petshopModelo) {
        this.petshopModelo = petshopModelo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
