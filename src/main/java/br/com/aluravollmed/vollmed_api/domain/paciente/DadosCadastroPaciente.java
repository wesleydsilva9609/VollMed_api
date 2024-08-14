package br.com.aluravollmed.vollmed_api.domain.paciente;

import br.com.aluravollmed.vollmed_api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(
                                   Long id,
                                   @NotBlank
                                    String nome,
                                    @NotBlank
                                    @Email
                                    String email,

                                    @NotBlank
                                    String telefone,
                                    @NotBlank
                                    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
                                    String cpf,

                                    @NotNull @Valid DadosEndereco endereco) {
}
