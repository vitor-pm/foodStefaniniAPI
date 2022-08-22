package br.com.stefaninifood.repository;

import br.com.stefaninifood.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {
    List<Loja> findByNomeContaining(String nome);
}
