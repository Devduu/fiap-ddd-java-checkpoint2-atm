package br.fiap.bank.atm.model;

import java.time.LocalDateTime;

public class Movimentacao {

    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;
    private Dinheiro valor;

    public Movimentacao(TipoMovimentacao tipo, Dinheiro valor) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public Dinheiro getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movimentacao that = (Movimentacao) obj;
        return dataHora.equals(that.dataHora) && tipo.equals(that.tipo);
    }
}