export interface Petshop{
    id: string;
    entrada: string;
    nome: string;
    especie: string;
    raca: string;
    altura: string;
    peso: string;
    pelagem: string;
    responsavel: string;
    tratamento: string;
    statusTratamento: string;
    contato: string;
    clienteid: string;
    valor: number;
}

export interface Cliente{
    id: string;
    nome: string;
    telefone: string;
    email: string;
    cpf: string;
    nascimento: string;
    rua: string;
    cidade: string;
    bairro: string;
    cep: string;
    
}