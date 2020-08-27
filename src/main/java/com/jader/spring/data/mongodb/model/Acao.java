package com.jader.spring.data.mongodb.model;

import java.util.Currency;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "acoes")
public class Acao {

    @Id
    private String id;

    private String ticker;
    private String empresa;
    private String setor;
    private Currency entrada;
    private Currency alvo;
    private boolean inWallet;
    private boolean target;

    public Acao() {

    }

    public Acao(String ticker, String empresa, String setor) {
        this.ticker = ticker;
        this.empresa = empresa;
        this.setor = setor;
    }

    public String getId() {
        return id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Currency getEntrada() {
        return entrada;
    }

    public void setEntrada(Currency entrada) {
        this.entrada = entrada;
    }

    public Currency getAlvo() {
        return alvo;
    }

    public void setAlvo(Currency alvo) {
        this.alvo = alvo;
    }

    public boolean isInWallet() {
        return inWallet;
    }

    public void setInWallet(boolean inWallet) {
        this.inWallet = inWallet;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Acao [id=" + id + ", ticke=" + ticker + ", empresa=" + empresa + "]";
    }

}
