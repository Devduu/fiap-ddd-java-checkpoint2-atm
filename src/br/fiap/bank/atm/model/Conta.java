package br.fiap.bank.atm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta extends BaseEntity {

    protected Cliente cliente;
    protected Dinheiro saldo;
    protected Double taxa;
    protected StatusConta status;
    protected LocalDate dataAbertura;
    protected ContaAcesso contaAcesso;
    protected List<Movimentacao> movimentacoes;

    public Conta(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo, Double taxa) {
        super();
        if (cliente == null) throw new IllegalArgumentException("A conta precisa de um cliente.");
        if (contaAcesso == null) throw new IllegalArgumentException("A conta precisa de uma forma de acesso.");
        if (saldo == null) throw new IllegalArgumentException("O saldo inicial e obrigatorio.");
        if (taxa == null) throw new IllegalArgumentException("A taxa e obrigatoria.");
        this.cliente = cliente;
        this.contaAcesso = contaAcesso;
        this.saldo = saldo;
        this.taxa = taxa;
        this.status = StatusConta.ATIVA;
        this.dataAbertura = LocalDate.now();
        this.movimentacoes = new ArrayList<>();
    }

    public void realizarSaque(Dinheiro valor) {
        if (!valor.isMaiorQueZero()) throw new RuntimeException("Valor do saque deve ser maior que zero.");
        if (valor.isMaiorQue(saldo)) throw new RuntimeException("Saldo insuficiente.");
        sacar(valor);
        registrarMovimentacao(TipoMovimentacao.SAQUE, valor);
        aplicarRegraDeTaxa();
    }

    public void realizarDeposito(Dinheiro valor) {
        if (!valor.isMaiorQueZero()) throw new RuntimeException("Valor do deposito deve ser maior que zero.");
        depositar(valor);
        registrarMovimentacao(TipoMovimentacao.DEPOSITO, valor);
    }

    protected void depositar(Dinheiro valor) {
        this.saldo = this.saldo.somar(valor);
    }

    protected void sacar(Dinheiro valor) {
        this.saldo = this.saldo.subtrair(valor);
    }

    protected void registrarMovimentacao(TipoMovimentacao tipo, Dinheiro valor) {
        movimentacoes.add(new Movimentacao(tipo, valor));
    }

    protected abstract void aplicarRegraDeTaxa();

    public Dinheiro getSaldo() { return saldo; }
    public Cliente getCliente() { return cliente; }
    public Double getTaxa() { return taxa; }
    public StatusConta getStatus() { return status; }
    public LocalDate getDataAbertura() { return dataAbertura; }
    public ContaAcesso getContaAcesso() { return contaAcesso; }
    public List<Movimentacao> getMovimentacoes() { return Collections.unmodifiableList(movimentacoes); }
}