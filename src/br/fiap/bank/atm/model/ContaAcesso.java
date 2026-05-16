package br.fiap.bank.atm.model;

public class ContaAcesso {

    private static final Integer MAXIMO_TENTATIVAS = 3;

    private String senha;
    private Integer tentativas;
    private Boolean bloqueado;

    public ContaAcesso(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha e obrigatoria.");
        }
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = false;
    }

    public Boolean validarSenha(String senhaDigitada) {
        if (bloqueado) {
            return false;
        }
        if (this.senha.equals(senhaDigitada)) {
            tentativas = 0;
            return true;
        } else {
            tentativas++;
            if (tentativas >= MAXIMO_TENTATIVAS) {
                bloqueado = true;
            }
            return false;
        }
    }

    public Boolean isBloqueado() {
        return bloqueado;
    }

    public Integer getTentativas() {
        return tentativas;
    }

    public Integer getTentativasRestantes() {
        return MAXIMO_TENTATIVAS - tentativas;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContaAcesso that = (ContaAcesso) obj;
        return senha.equals(that.senha);
    }
}