package br.thony.fateczl.contabancaria.model;

public class ContaPoupanca extends ContaBancaria{

    private int diaRendimento;

    public ContaPoupanca(String nome, int numConta, float saldoInicial, int diaRendimento) {
        super(nome, numConta, saldoInicial);
        this.diaRendimento = diaRendimento;
    }

    public void calcularRendimento(float taxaRendimento) {
        saldo += saldo * (taxaRendimento / 200);
    }
}
