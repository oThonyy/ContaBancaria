package br.thony.fateczl.contabancaria.model;

public class ContaBancaria {

    protected String nome;
    protected int numConta;
    protected float saldo;

    public ContaBancaria(String cliente, int numConta, float saldoInicial) {
        this.nome = cliente;
        this.numConta = numConta;
        this.saldo = saldoInicial;
    }

    public boolean sacar(float valor) {
        if (saldo >= valor) {
            return true;
        } else {
            return false;
        }
    }

    public void depositar(float valor) {
        saldo += valor;
    }

    public float getSaldo() {
        return saldo;
    }

    public String getDadosConta() {
        return "Cliente: " + nome + ", Conta: " + numConta + ", Saldo: R$ " + saldo;
    }

}
