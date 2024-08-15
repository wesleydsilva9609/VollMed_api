package br.com.aluravollmed.vollmed_api.Controller;

import br.com.aluravollmed.vollmed_api.domain.usuario.DadosAutenticacao;
import br.com.aluravollmed.vollmed_api.domain.usuario.DadosTokenJWT;
import br.com.aluravollmed.vollmed_api.domain.usuario.Usuario;
import br.com.aluravollmed.vollmed_api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dadosAutenticacao){
        var token = new  UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authentication = manager.authenticate(token);
        var tokenjwt = service.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenjwt));
    }

}
