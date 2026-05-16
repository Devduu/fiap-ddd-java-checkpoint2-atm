package br.fiap.bank.atm.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaPoupanca extends Conta {

    private static final Double RENDIMENTO_MENSAL = 1.0;

    public ContaPoupanca(Cliente cliente, Dinheiro saldo, ContaAcesso contaAcesso) {
        super(cliente, contaAcesso, saldo, RENDIMENTO_MENSAL);
    }

    @Override
    protected void aplicarRegraDeTaxa() {
    }

    public void aplicarTaxaMensal() {
        BigDecimal rendimento = saldo.getValor()
                .multiply(BigDecimal.valueOf(taxa / 100.0))
                .setScale(2, RoundingMode.HALF_UP);
        if (rendimento.compareTo(BigDecimal.ZERO) > 0) {
            Dinheiro valorRendimento = new Dinheiro(rendimento);
            depositar(valorRendimento);
            registrarMovimentacao(TipoMovimentacao.RENDIMENTO, valorRendimento);
        }
    }
}