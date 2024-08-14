package br.com.aluravollmed.vollmed_api.domain.paciente;

public record DadosListagemPacientes(Long id, String nome, String email, String cpf) {
    public DadosListagemPacientes(Paciente dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getCpf());
    }
}
