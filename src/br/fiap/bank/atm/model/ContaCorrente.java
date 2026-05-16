package br.fiap.bank.atm.model;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {

    private static final Double TAXA_MANUTENCAO = 25.00;

    public ContaCorrente(Cliente cliente, Dinheiro saldo, ContaAcesso contaAcesso) {
        super(cliente, contaAcesso, saldo, TAXA_MANUTENCAO);
    }

    @Override
    protected void aplicarRegraDeTaxa() {
        Dinheiro taxaCobrada = new Dinheiro(BigDecimal.valueOf(taxa));
        if (saldo.isMaiorQue(taxaCobrada)) {
            sacar(taxaCobrada);
            registrarMovimentacao(TipoMovimentacao.TAXA, taxaCobrada);
        }
    }

    public void aplicarTaxaMensal() {
        aplicarRegraDeTaxa();
    }
}