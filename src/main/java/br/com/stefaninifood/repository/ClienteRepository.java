package br.com.stefaninifood.repository;

import br.com.stefaninifood.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);
}
