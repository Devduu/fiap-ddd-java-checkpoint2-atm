package br.fiap.bank.atm.model;

public class Cliente extends BaseEntity {

    private String nomeCompleto;

    public Cliente(String nomeCompleto) {
        super();
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome completo do cliente e obrigatorio.");
        }
        this.nomeCompleto = nomeCompleto.trim();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getPrimeiroNome() {
        return nomeCompleto.split("\\s+")[0];
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}