package br.com.stefaninifood.service;

import br.com.stefaninifood.model.Loja;
import br.com.stefaninifood.model.dto.LojaDto;
import br.com.stefaninifood.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;

    public ResponseEntity<List<?>> getAllLojas(String nome) {
        List<LojaDto> lojas = new ArrayList<>();

        if (nome == null) {
            List<Loja> LojasBanco = repository.findAll();
            LojasBanco.forEach(l -> lojas.add(new LojaDto(l)));

            return ResponseEntity.ok(lojas);
        }
        List<Loja> LojasBanco = repository.findByNomeContaining(nome);
        LojasBanco.forEach(l -> lojas.add(new LojaDto(l)));

        return ResponseEntity.ok(repository.findByNomeContaining(nome));
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
