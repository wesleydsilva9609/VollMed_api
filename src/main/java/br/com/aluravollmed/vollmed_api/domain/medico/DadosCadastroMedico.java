package br.com.aluravollmed.vollmed_api.domain.medico;

import br.com.aluravollmed.vollmed_api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedico(
        Long id,
        @NotBlank
        String nome,
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid DadosEndereco endereco

) {
}
