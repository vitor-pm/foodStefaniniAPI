package br.com.stefaninifood.model.dto;

public class LoginDTO {

    private String token;
    private String tipo;

    public LoginDTO(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
