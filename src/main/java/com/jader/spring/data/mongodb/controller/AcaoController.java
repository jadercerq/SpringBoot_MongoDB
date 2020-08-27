package com.jader.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jader.spring.data.mongodb.model.Acao;
import com.jader.spring.data.mongodb.repository.AcaoRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AcaoController {

    @Autowired
    AcaoRepository acaoRepository;

    @GetMapping("/acoes")
    public ResponseEntity<List<Acao>> getAllAcoes(@RequestParam(required = false) String ticker) {
        try {
            List<Acao> acoes = new ArrayList<>();

            if (ticker == null) {
                acaoRepository.findAll().forEach(acoes::add);
            } else {
                acaoRepository.findByTicker(ticker).forEach(acoes::add);
            }

            if (acoes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(acoes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/acoes/{id}")
    public ResponseEntity<Acao> getAcaoById(@PathVariable("id") String id) {
        Optional<Acao> acaoData = acaoRepository.findById(id);

        if (acaoData.isPresent()) {
            return new ResponseEntity<>(acaoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/acoes")
    public ResponseEntity<Acao> createAcao(@RequestBody Acao acao) {
        try {
            Acao _acao = acaoRepository.save(new Acao(acao.getTicker(), acao.getEmpresa(), acao.getSetor()));
            return new ResponseEntity<>(_acao, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/acoes/{id}")
    public ResponseEntity<Acao> updateAcao(@PathVariable("id") String id, @RequestBody Acao acao) {
        Optional<Acao> acaoData = acaoRepository.findById(id);

        if (acaoData.isPresent()) {
            Acao _acao = acaoData.get();
            _acao.setTicker(acao.getTicker());
            _acao.setEmpresa(acao.getEmpresa());
            _acao.setSetor(acao.getSetor());
            _acao.setInWallet(acao.isInWallet());
            _acao.setTarget(acao.isTarget());
            return new ResponseEntity<>(acaoRepository.save(_acao), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/acoes/{id}")
    public ResponseEntity<HttpStatus> deleteAcao(@PathVariable("id") String id) {
        try {
            acaoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/acoes")
    public ResponseEntity<HttpStatus> deleteAllAcoes() {
        try {
            acaoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/acoes/inWallet")
    public ResponseEntity<List<Acao>> findByPublished() {
        try {
            List<Acao> acoes = acaoRepository.findByInWallet(true);

            if (acoes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(acoes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
