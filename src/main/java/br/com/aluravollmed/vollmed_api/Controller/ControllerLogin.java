package br.com.aluravollmed.vollmed_api.Controller;

import br.com.aluravollmed.vollmed_api.domain.usuario.DadosAutenticacao;
import br.com.aluravollmed.vollmed_api.infra.security.DadosTokenjwt;
import br.com.aluravollmed.vollmed_api.infra.security.TokenService;
import br.com.aluravollmed.vollmed_api.domain.usuario.Usuario;
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
public class ControllerLogin {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){

        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authetication = authenticationManager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) authetication.getPrincipal());


        return ResponseEntity.ok(new DadosTokenjwt(tokenJWT));
    }
}
