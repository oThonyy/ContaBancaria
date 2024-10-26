package br.thony.fateczl.contabancaria;

import static br.thony.fateczl.contabancaria.R.id.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

import br.thony.fateczl.contabancaria.model.ContaBancaria;
import br.thony.fateczl.contabancaria.model.ContaEspecial;
import br.thony.fateczl.contabancaria.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgtipoConta;
    private EditText etNumConta;
    private EditText etSaldoInicial;
    private EditText etNomeConta;
    private Button btnCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNumConta = findViewById(R.id.etNumConta);
        etSaldoInicial = findViewById(R.id.etSaldoInicial);
        etNomeConta = findViewById(R.id.etNomeConta);
        rgtipoConta = findViewById(R.id.radioGroupTipoConta);
        btnCriar = findViewById(R.id.btnCriar);

        btnCriar.setOnClickListener(op -> criarConta());

    }

    private void criarConta() {

        float saldoInicial = Float.parseFloat(etSaldoInicial.getText().toString());
        int numConta = Integer.parseInt(etNumConta.getText().toString());
        String nome = etNomeConta.getText().toString();

        int selectedId = rgtipoConta.getCheckedRadioButtonId();
        String tipoConta;

        if (selectedId == R.id.rbPoupanca) {
            tipoConta = "poupanca";
        } else if (selectedId == R.id.rbEspecial) {
            tipoConta = "especial";
        } else {
            Toast.makeText(this, "Erro, selecione algum tipo de conta", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, ProcessosActivity.class);
        intent.putExtra("saldoInicial", saldoInicial);
        intent.putExtra("numConta", numConta);
        intent.putExtra("nome", nome);
        intent.putExtra("tipoConta", tipoConta);
        startActivity(intent);

    }
}