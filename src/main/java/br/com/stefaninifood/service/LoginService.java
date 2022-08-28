package br.com.stefaninifood.service;

import br.com.stefaninifood.configuration.security.TokenJwtService;
import br.com.stefaninifood.model.dto.LoginDTO;
import br.com.stefaninifood.model.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenJwtService tokenService;

    public ResponseEntity<?> login(LoginForm loginForm) {
        UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();

        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok(new LoginDTO(token,"Bearer "));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
