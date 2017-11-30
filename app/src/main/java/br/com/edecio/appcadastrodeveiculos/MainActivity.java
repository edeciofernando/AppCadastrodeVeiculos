package br.com.edecio.appcadastrodeveiculos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtModelo;
    private EditText edtAno;
    private EditText edtPreco;
    private Button btnAlterar;
    private Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtModelo = (EditText) findViewById(R.id.edtModelo);
        edtAno = (EditText) findViewById(R.id.edtAno);
        edtPreco = (EditText) findViewById(R.id.edtPreco);
        edtId = (EditText) findViewById(R.id.edtId);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
    }

    public void incluirVeiculo(View view) {
        String modelo = edtModelo.getText().toString();
        String strAno = edtAno.getText().toString();
        String strPreco = edtPreco.getText().toString();

        if (modelo.trim().isEmpty() || strAno.trim().isEmpty() || strPreco.trim().isEmpty()) {
            Toast.makeText(this, "Preencha corretamente os campos", Toast.LENGTH_LONG).show();
            edtModelo.requestFocus();
            return;
        }

        int ano = Integer.parseInt(strAno);
        double preco = Double.parseDouble(strPreco);

        CarroDB carroDB = new CarroDB(this);

        long id = carroDB.inclui(new Carros(0, modelo, ano, preco));

        if (id > 0) {
            Toast.makeText(this, "Ok! Inserido com Id: " + id, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Erro... Não Inserido", Toast.LENGTH_LONG).show();
        }

        // Limpa os editText e joga o foco no modelo
        edtModelo.setText("");
        edtAno.setText("");
        edtPreco.setText("");
        edtModelo.requestFocus();
    }

    public void buscarVeiculo(View view) {
        String strId = edtId.getText().toString();

        if (strId.trim().isEmpty()) {
            Toast.makeText(this, "Informe o código a ser pesquisado", Toast.LENGTH_LONG).show();
            edtId.requestFocus();
            return;
        }

        long id = Integer.parseInt(strId);

        CarroDB carroDB = new CarroDB(this);

        Carros carro = carroDB.busca(id);

        if (carro.getId() > 0) {
            edtModelo.setText(carro.getModelo());
            edtAno.setText(String.valueOf(carro.getAno()));
            edtPreco.setText(String.valueOf(carro.getPreco()));

            btnAlterar.setVisibility(View.VISIBLE);
            btnExcluir.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Erro... Código não cadastrado", Toast.LENGTH_LONG).show();

            limparCampos();
        }
    }

    private void limparCampos() {
        // Limpa os editText
        edtId.setText("");
        edtModelo.setText("");
        edtAno.setText("");
        edtPreco.setText("");

        // oculta os botões
        btnAlterar.setVisibility(View.INVISIBLE);
        btnExcluir.setVisibility(View.INVISIBLE);

        // joga o foco no edtId
        edtId.requestFocus();
    }

    public void alterarVeiculo(View view) {
        String strId = edtId.getText().toString();
        String modelo = edtModelo.getText().toString();
        String strAno = edtAno.getText().toString();
        String strPreco = edtPreco.getText().toString();

        int id = Integer.parseInt(strId);
        int ano = Integer.parseInt(strAno);
        double preco = Double.parseDouble(strPreco);

        CarroDB carroDB = new CarroDB(this);

        if (carroDB.altera(new Carros(id, modelo, ano, preco)) > 0) {
            Toast.makeText(this, "Ok! Veículo Alterado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Erro... Não Alterado", Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }

    public void excluirVeiculo(View view) {
        String strId = edtId.getText().toString();

        int id = Integer.parseInt(strId);

        CarroDB carroDB = new CarroDB(this);

        if (carroDB.exclui(id) > 0) {
            Toast.makeText(this, "Ok! Veículo Excluído", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Erro... Não foi excluído", Toast.LENGTH_LONG).show();
        }

        limparCampos();
    }
}
