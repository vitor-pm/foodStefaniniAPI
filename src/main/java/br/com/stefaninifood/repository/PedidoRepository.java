package br.com.stefaninifood.repository;

import br.com.stefaninifood.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Page<Pedido> findByCliente_Nome(String cliente, Pageable paginacao);

}
