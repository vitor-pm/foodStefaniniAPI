package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Cliente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void postCliente() {
        Cliente cliente = new Cliente();
        cliente.setSenha("123456");
        cliente.setEndereco("Endereco teste");
        cliente.setEmail("testejunit@email.com");
        cliente.setTelefone("1111111111111");
        cliente.setNome("Teste nome");
        HttpEntity<Cliente> request = new HttpEntity<>(cliente);

        ResponseEntity<Cliente> response = testRestTemplate.exchange("/cliente", HttpMethod.POST, request,
                Cliente.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(request.getBody().getNome(), response.getBody().getNome());
    }
}