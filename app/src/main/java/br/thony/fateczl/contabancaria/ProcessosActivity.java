package br.thony.fateczl.contabancaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.thony.fateczl.contabancaria.model.ContaBancaria;
import br.thony.fateczl.contabancaria.model.ContaEspecial;
import br.thony.fateczl.contabancaria.model.ContaPoupanca;

public class ProcessosActivity extends AppCompatActivity {

        private EditText etValorOperacao;
        private Button btnDepositar;
        private Button btnSacar;
        private Button btnCalcularRendimento;
        private TextView lblDadosConta;
        private ContaBancaria conta;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_processos);

            etValorOperacao = findViewById(R.id.etValorOperacao);
            btnDepositar = findViewById(R.id.btnDepositar);
            btnSacar = findViewById(R.id.btnSacar);
            btnCalcularRendimento = findViewById(R.id.btnCalcularRendimento);
            lblDadosConta = findViewById(R.id.lblDadosConta);

            float saldoInicial = getIntent().getFloatExtra("saldo", 0);
            int numConta = getIntent().getIntExtra("numConta", 0);
            String nome = getIntent().getStringExtra("nome");
            String tipoConta = getIntent().getStringExtra("tipoConta");

            assert tipoConta != null;
            if (tipoConta.equals("poupanca")) {
                conta = new ContaPoupanca(nome, numConta, saldoInicial, 20);
                btnCalcularRendimento.setVisibility(View.VISIBLE);
            } else if (tipoConta.equals("especial")) {
                conta = new ContaEspecial(nome, numConta, saldoInicial, 750);
            }

            atualizarDadosConta();

            btnDepositar.setOnClickListener(op -> depositar());

            btnSacar.setOnClickListener(op -> sacar());

            btnCalcularRendimento.setOnClickListener(op -> calcularRendimento());
        }

        private void calcularRendimento() {
            if (conta instanceof ContaPoupanca) {
                ((ContaPoupanca) conta).calcularRendimento(2);
                atualizarDadosConta();
                Toast.makeText(ProcessosActivity.this, "Rendimento calculado!", Toast.LENGTH_SHORT).show();
            }
        }

        private void sacar() {
            float valor = Float.parseFloat(etValorOperacao.getText().toString());
            if (conta.sacar(valor)) {
                atualizarDadosConta();
                Toast.makeText(ProcessosActivity.this, "Saque realizado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProcessosActivity.this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show();
            }
        }

        private void depositar() {
            float valor = Float.parseFloat(etValorOperacao.getText().toString());
            conta.depositar(valor);
            atualizarDadosConta();
            Toast.makeText(ProcessosActivity.this, "Depósito realizado!", Toast.LENGTH_SHORT).show();
        }

        private void atualizarDadosConta() {
            lblDadosConta.setText(conta.getDadosConta());
        }
    }