package com.jader.spring.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jader.spring.data.mongodb.model.Acao;

public interface AcaoRepository extends MongoRepository<Acao, String> {
  List<Acao> findByTicker(String ticker);
  List<Acao> findBySetor(String setor);
  List<Acao> findByInWallet(boolean inWallet);
}
