package br.com.aluravollmed.vollmed_api.infra.security;

import br.com.aluravollmed.vollmed_api.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recuperarToken(request);
        System.out.println("chamando filtro");

        if(token != null){

           var getsubject = tokenService.getSubject(token);
           var usuario = usuarioRepository.findByLogin(getsubject);
           var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authenticationHeader = request.getHeader("Authorization");
        if (authenticationHeader != null){
            return authenticationHeader.replace("Bearer","").trim();
        }
        return null;
    }
}
