package br.com.aluravollmed.vollmed_api.domain.paciente;

import br.com.aluravollmed.vollmed_api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizadoPaciente(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
