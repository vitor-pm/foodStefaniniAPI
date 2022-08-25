package br.com.stefaninifood.model.dto;

import br.com.stefaninifood.model.Loja;
import br.com.stefaninifood.model.Produto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProdutoDetalharDTO implements Serializable {
    private final int id;
    @NotNull
    @NotEmpty
    private final String nome;
    @NotNull
    private final double preco;
    private final List<LojaDto1> lojas;

    public ProdutoDetalharDTO(int id, String nome, double preco, List<LojaDto1> lojas) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.lojas = lojas;
    }

    public ProdutoDetalharDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.lojas = produto.getLojas().stream().map(l -> {
            LojaDto1 loja = new LojaDto1(l);
            return loja;
        }).collect(Collectors.toList());
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

    public List<LojaDto1> getLojas() {
        return lojas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDetalharDTO entity = (ProdutoDetalharDTO) o;
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
                "lojas = " + lojas + ")";
    }

    public static class LojaDto1 implements Serializable {
        private final int id;
        @NotNull
        @NotEmpty
        private final String cnpj;
        @NotNull
        @NotEmpty
        private final String nome;
        @NotNull
        @NotEmpty
        private final String descricao;
        @NotNull
        @NotEmpty
        private final String telefone;

        public LojaDto1(int id, String cnpj, String nome, String descricao, String telefone) {
            this.id = id;
            this.cnpj = cnpj;
            this.nome = nome;
            this.descricao = descricao;
            this.telefone = telefone;
        }

        public LojaDto1(Loja loja) {
            this.id = loja.getId();
            this.cnpj = loja.getCnpj();
            this.nome = loja.getNome();
            this.descricao = loja.getDescricao();
            this.telefone = loja.getTelefone();
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LojaDto1 entity = (LojaDto1) o;
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
}
