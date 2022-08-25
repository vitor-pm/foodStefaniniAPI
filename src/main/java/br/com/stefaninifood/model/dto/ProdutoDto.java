package br.com.stefaninifood.model.dto;

import br.com.stefaninifood.model.Produto;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoDto implements Serializable {
    private final int id;
    @NotNull
    @NotEmpty
    private final String nome;
    @NotNull
    private final double preco;
    @NotNull
    @NotEmpty
    private List<String> lojas;

    public ProdutoDto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public ProdutoDto(Produto p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.preco = p.getPreco();
        this.lojas = new ArrayList<>();
        p.getLojas().forEach(l -> this.lojas.add(l.getNome()));
    }

    public static Page<ProdutoDto> converter(Page<Produto> produtos) {
        return produtos.map(ProdutoDto::new);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public List<String> getLojas() {
        return lojas;
    }

    public void setLojas(List<String> lojas) {
        this.lojas = lojas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDto entity = (ProdutoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.preco, entity.preco) &&
                Objects.equals(this.lojas, entity.lojas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco, lojas);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "preco = " + preco + ", " +
                "lojaNome = " + lojas.toString() + ")";
    }
}
