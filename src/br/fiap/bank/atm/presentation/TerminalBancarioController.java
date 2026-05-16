package br.fiap.bank.atm.presentation;

import br.fiap.bank.atm.application.AutorizacaoService;
import br.fiap.bank.atm.application.ContaService;
import br.fiap.bank.atm.model.Dinheiro;
import br.fiap.bank.atm.model.Movimentacao;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TerminalBancarioController {

    private ContaService contaService;
    private AutorizacaoService autorizacaoService;
    private Scanner scanner;

    public TerminalBancarioController(ContaService contaService, AutorizacaoService autorizacaoService) {
        this.contaService = contaService;
        this.autorizacaoService = autorizacaoService;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuPrincipal() {
        String opcao = "";
        while (!opcao.equals("5")) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("( 1 ) Consultar Saldo");
            System.out.println("( 2 ) Fazer Deposito");
            System.out.println("( 3 ) Fazer Saque");
            System.out.println("( 4 ) Historico de Movimentacoes");
            System.out.println("( 5 ) Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    exibirSaldo();
                    break;
                case "2":
                    realizarDeposito();
                    break;
                case "3":
                    realizarSaque();
                    break;
                case "4":
                    exibirMovimentacoes();
                    break;
                case "5":
                    System.out.println("O FIAP Bank agradece sua preferencia!");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
        scanner.close();
    }

    public void exibirSaldo() {
        Dinheiro saldo = contaService.obterSaldo();
        System.out.printf("Saldo atual: R$ %.2f%n", saldo.getValor());
    }

    public void realizarDeposito() {
        System.out.print("Digite o valor do deposito: R$ ");
        String entrada = scanner.nextLine().trim().replace(",", ".");
        try {
            Dinheiro valor = new Dinheiro(new BigDecimal(entrada));
            contaService.realizarDeposito(valor);
            System.out.println("Deposito realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void realizarSaque() {
        System.out.print("Digite o valor do saque: R$ ");
        String entrada = scanner.nextLine().trim().replace(",", ".");
        try {
            Dinheiro valor = new Dinheiro(new BigDecimal(entrada));
            contaService.realizarSaque(valor);
            System.out.println("Saque realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirMovimentacoes() {
        List<Movimentacao> movimentacoes = contaService.obterMovimentacoes();
        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentacao encontrada.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("\n=== HISTORICO DE MOVIMENTACOES ===");
        for (Movimentacao m : movimentacoes) {
            System.out.printf("[%s] %s - R$ %.2f%n",
                    m.getDataHora().format(formatter),
                    m.getTipo(),
                    m.getValor().getValor());
        }
    }
}