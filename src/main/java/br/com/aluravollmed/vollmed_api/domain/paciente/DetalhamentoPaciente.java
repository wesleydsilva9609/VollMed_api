package br.com.aluravollmed.vollmed_api.domain.paciente;

import br.com.aluravollmed.vollmed_api.domain.endereco.Endereco;

public record DetalhamentoPaciente(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DetalhamentoPaciente(Paciente dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getCpf(), dados.getTelefone(), dados.getEndereco());
    }
}
