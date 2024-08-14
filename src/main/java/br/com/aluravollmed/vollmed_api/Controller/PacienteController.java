package br.com.aluravollmed.vollmed_api.Controller;

import br.com.aluravollmed.vollmed_api.domain.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new Paciente(dados);
        pacienteRepository.save(paciente);
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(dados.id()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>>listar(@PageableDefault(size = 10,sort = "nome")Pageable pageable){
        var page = pacienteRepository.findAllByAtivoTrue(pageable).map(DadosListagemPacientes::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizadoPaciente dados){
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizar(dados);
        return ResponseEntity.ok(new DetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }
}
