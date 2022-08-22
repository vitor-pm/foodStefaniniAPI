package br.com.stefaninifood.model.dto;

public class FormErrorDto {

    private final String campo;
    private final String mensagem;

    public FormErrorDto(String campoRecebido, String mensagemRecebida){
        campo = campoRecebido;
        mensagem = mensagemRecebida;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
