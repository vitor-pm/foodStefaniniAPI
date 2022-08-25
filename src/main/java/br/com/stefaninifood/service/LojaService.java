package br.com.stefaninifood.service;

import br.com.stefaninifood.model.Loja;
import br.com.stefaninifood.model.dto.LojaDto;
import br.com.stefaninifood.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;

    public ResponseEntity<Page<?>> getAllLojas(String nome, Pageable paginacao) {
        if (nome == null) {
            Page<Loja> LojasBanco = repository.findAll(paginacao);

            return ResponseEntity.ok(LojaDto.converter(LojasBanco));
        }
        Page<Loja> LojasBanco = repository.findByNomeContaining(nome, paginacao);

        return ResponseEntity.ok(LojaDto.converter(LojasBanco));
    }

    public ResponseEntity<Loja> getLojaById(int id) {
        Optional<Loja> loja = repository.findById(id);
        return loja.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Loja> insertLoja(Loja loja) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(loja));
    }

    public ResponseEntity<Loja> updateLoja(Loja loja) {
        Optional<Loja> lojaBanco = repository.findById(loja.getId());
        if (lojaBanco.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(loja));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> deleteLoja(int id) {
        Optional<Loja> loja = repository.findById(id);
        if (loja.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Loja " + id + " excluída.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loja " + id + " não encontrada.");
        }
    }
}
