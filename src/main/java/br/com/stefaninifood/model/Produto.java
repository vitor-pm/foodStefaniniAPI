package br.com.stefaninifood.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    private double preco;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "loja_tem",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "loja_id")
    )
    private List<Loja> lojas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(List<Loja> loja) {
        this.lojas = loja;
    }
}
