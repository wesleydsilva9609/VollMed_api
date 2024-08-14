package br.com.aluravollmed.vollmed_api.Controller;

import br.com.aluravollmed.vollmed_api.domain.medico.*;
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
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
   private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder){
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(size = 10,sort = "nome") Pageable pageable){
        var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
        return ResponseEntity.ok().body(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity BuscarporId(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return  ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizarCadastro(@RequestBody @Valid DadosMedicoAtualizados dados ){
       var medico = repository.getReferenceById(dados.id());
       medico.atualiza(dados);
       return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity Deletar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.deletar();
        return ResponseEntity.noContent().build();
    }

}
