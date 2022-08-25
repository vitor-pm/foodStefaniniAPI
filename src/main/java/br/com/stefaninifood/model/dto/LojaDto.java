package br.com.stefaninifood.model.dto;

import br.com.stefaninifood.model.Loja;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class LojaDto implements Serializable {
    private final int id;
    @NotNull
    @NotEmpty
    private final String nome;
    @NotNull
    @NotEmpty
    private final String cnpj;
    @NotNull
    @NotEmpty
    private final String descricao;
    @NotNull
    @NotEmpty
    private final String telefone;

    private final int qtdPedidos;
    private final int qtdProdutos;

    public LojaDto(int id, String cnpj, String nome, String descricao, String telefone, int qtdPedidos, int qtdProdutos) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
        this.telefone = telefone;
        this.qtdPedidos = qtdPedidos;
        this.qtdProdutos = qtdProdutos;
    }

    public LojaDto(Loja loja) {
        this.id = loja.getId();
        this.nome = loja.getNome();
        this.cnpj = loja.getCnpj();
        this.descricao = loja.getDescricao();
        this.telefone = loja.getTelefone();
        this.qtdPedidos = loja.getPedidos().size();
        this.qtdProdutos = loja.getProdutos().size();
    }

    public static Page<LojaDto> converter(Page<Loja> lojas) {
        return lojas.map(LojaDto::new);
    }

    public int getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getQtdPedidos() {
        return qtdPedidos;
    }

    public int getQtdProdutos() {
        return qtdProdutos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LojaDto entity = (LojaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.cnpj, entity.cnpj) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.descricao, entity.descricao) &&
                Objects.equals(this.telefone, entity.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, nome, descricao, telefone);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "cnpj = " + cnpj + ", " +
                "nome = " + nome + ", " +
                "descricao = " + descricao + ", " +
                "telefone = " + telefone + ")";
    }
}
