package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.form.LoginForm;
import br.com.stefaninifood.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping()
    public ResponseEntity<?> logar(@RequestBody @Valid LoginForm loginForm) {
        return service.login(loginForm);
    }
}
