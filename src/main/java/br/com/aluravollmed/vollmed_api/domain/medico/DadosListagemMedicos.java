package br.com.aluravollmed.vollmed_api.domain.medico;

public record DadosListagemMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedicos(Medico dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getCrm(), dados.getEspecialidade());
    }
}
