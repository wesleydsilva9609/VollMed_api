package br.com.aluravollmed.vollmed_api.domain.medico;

import br.com.aluravollmed.vollmed_api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosMedicoAtualizados(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
