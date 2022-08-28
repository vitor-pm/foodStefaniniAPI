package br.com.stefaninifood.configuration.security;

import br.com.stefaninifood.model.Cliente;
import br.com.stefaninifood.repository.ClienteRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFilter extends OncePerRequestFilter {

    private TokenJwtService tokenService;
    private ClienteRepository repository;

    public AutenticacaoFilter(TokenJwtService tokenService, ClienteRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValid(token);
        if (valido) {
            autenticarClinte(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarClinte(String token) {
        int id = tokenService.getIdCliente(token);
        Cliente clienteBanco = repository.findById(id).get();
        UsernamePasswordAuthenticationToken cliente = new UsernamePasswordAuthenticationToken(clienteBanco, null, null);
        SecurityContextHolder.getContext().setAuthentication(cliente);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty() || !authorization.startsWith("Bearer ")) {
            return null;
        }

        return authorization.substring(7);
    }
}
