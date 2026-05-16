package br.fiap.bank.atm.infrastructure;

import br.fiap.bank.atm.model.Conta;

public class ContaRepository {

    private Conta conta;

    public void salvar(Conta conta) {
        this.conta = conta;
    }

    public Conta buscar() {
        return conta;
    }
}