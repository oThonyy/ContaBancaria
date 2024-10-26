package br.thony.fateczl.contabancaria.model;

public class ContaEspecial extends ContaBancaria {

    private float limite = getSaldo() * 2;

    public ContaEspecial(String nome, int numConta, float saldoInicial, float limite) {
        super(nome, numConta, saldoInicial);
        this.limite = limite;
    }

    @Override
    public boolean sacar(float valor) {
        if (valor <= saldo + limite) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

}
