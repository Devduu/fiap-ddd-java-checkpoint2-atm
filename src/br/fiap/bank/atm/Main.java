package br.fiap.bank.atm;

import br.fiap.bank.atm.application.AutorizacaoService;
import br.fiap.bank.atm.application.ContaFactory;
import br.fiap.bank.atm.application.ContaService;
import br.fiap.bank.atm.model.Conta;
import br.fiap.bank.atm.model.ContaAcesso;
import br.fiap.bank.atm.model.Cliente;
import br.fiap.bank.atm.model.Dinheiro;
import br.fiap.bank.atm.presentation.TerminalBancarioController;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nomeCompleto = "";
        while (nomeCompleto.isBlank()) {
            System.out.print("Digite seu nome completo: ");
            nomeCompleto = scanner.nextLine().trim();
            if (nomeCompleto.isBlank()) {
                System.out.println("Nome invalido! Digite pelo menos um nome.");
            }
        }

        String regexSenhaForte = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_+=?><]).{8,}$";
        String senha = "";
        while (!senha.matches(regexSenhaForte)) {
            System.out.print("Cadastre uma senha forte: ");
            senha = scanner.nextLine();
            if (!senha.matches(regexSenhaForte)) {
                System.out.println("Senha fraca! Use no minimo 8 caracteres, uma maiuscula, um numero e um caracter especial.");
            }
        }
        System.out.println("Senha cadastrada com sucesso!");

        Cliente cliente = new Cliente(nomeCompleto);
        ContaAcesso contaAcesso = new ContaAcesso(senha);
        Dinheiro saldoInicial = new Dinheiro(BigDecimal.ZERO);

        Conta conta = ContaFactory.getInstance()
                .criarContaCorrente(cliente, saldoInicial, contaAcesso);

        System.out.println("Bem-vindo(a), " + cliente.getPrimeiroNome() + "!");
        Boolean acessoLiberado = false;

        while (!acessoLiberado) {
            if (conta.getContaAcesso().isBloqueado()) {
                System.out.println("ACESSO BLOQUEADO! Conta bloqueada por excesso de tentativas.");
                scanner.close();
                return;
            }
            System.out.print("Digite sua senha para acessar: ");
            String senhaDigitada = scanner.nextLine();
            acessoLiberado = contaAcesso.validarSenha(senhaDigitada);
            if (!acessoLiberado) {
                Integer restantes = contaAcesso.getTentativasRestantes();
                System.out.println("Senha incorreta! Tentativas restantes: " + restantes);
            }
        }

        System.out.println("Acesso liberado!");

        ContaService contaService = new ContaService(conta);
        AutorizacaoService autorizacaoService = new AutorizacaoService(conta);
        TerminalBancarioController terminal = new TerminalBancarioController(contaService, autorizacaoService);
        terminal.exibirMenuPrincipal();

        scanner.close();
    }
}