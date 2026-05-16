# FIAP Bank ATM - Checkpoint 2

> Simulador de terminal de autoatendimento bancário, desenvolvido em Java com arquitetura DDD.

---

## 🧑‍💻 Aluno
**Eduardo Delorenzo Moraes**  
RM: 561749 | Turma: 2ESPH  
FIAP - Engenharia de Software  
Disciplina: Domain Driven Design - Java

---

## 📋 Sobre o Projeto
Refatoração completa do FIAP Bank ATM (versão Alpha → Beta), aplicando **Orientação a Objetos** e **Domain-Driven Design**, com separação clara de responsabilidades em camadas.

---

## 🏗️ Arquitetura DDD

| Camada | Responsabilidade |
|---|---|
| `presentation` | Interface com o usuário, Scanner, menus |
| `application` | Orquestração, Services, Factory (Singleton) |
| `model` | Entidades, Value Objects, regras de negócio |
| `infrastructure` | Repositório em memória |

---

## ⚙️ Funcionalidades

| Funcionalidade | Descrição |
|---|---|
| 🧑 Cadastro | Nome completo com boas-vindas personalizado |
| 🔒 Senha Forte | Regex: mínimo 8 caracteres, maiúscula, número e especial |
| 🚫 Bloqueio | Conta bloqueada após 3 tentativas incorretas |
| 💰 Saldo | Consulta do saldo formatado |
| 💵 Depósito | Com validação de valor positivo |
| 💸 Saque | Com validação de saldo suficiente |
| 🏦 Taxa | ContaCorrente cobra R$ 25,00 por saque |
| 📈 Rendimento | ContaPoupanca rende 1% a cada saque |
| 📜 Histórico | Lista todas as movimentações com data e hora |

---

## 🧱 Padrões Aplicados
- **Singleton** → `ContaFactory`
- **Factory** → criação de `ContaCorrente` e `ContaPoupanca`
- **Template Method** → `realizarSaque()` na classe abstrata `Conta`
- **Value Objects** → `Dinheiro`, `ContaAcesso`, `Movimentacao`

---

## 🛠️ Tecnologias
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
