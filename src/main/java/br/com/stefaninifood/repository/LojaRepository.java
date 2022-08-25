package br.com.stefaninifood.repository;

import br.com.stefaninifood.model.Loja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {
    Page<Loja> findByNomeContaining(String nome, Pageable paginacao);
}
