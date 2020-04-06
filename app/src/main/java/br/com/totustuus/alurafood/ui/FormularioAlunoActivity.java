package br.com.totustuus.alurafood.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.totustuus.alurafood.R;
import br.com.totustuus.alurafood.validator.ValidaCPF;
import br.com.totustuus.alurafood.validator.ValidacaoPadrao;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        TextInputLayout textInputNome = findViewById(R.id.formulario_cadastro_campo_nome_completo);
        adicionaValidacaoPadrao(textInputNome);


        TextInputLayout textInputCPF = findViewById(R.id.formulario_cadastro_campo_cpf);
        EditText campoCPF = textInputCPF.getEditText();
        campoCPF.setOnFocusChangeListener((view, hasFocus) -> {

            ValidaCPF validaCPF = new ValidaCPF(textInputCPF);

            if(!hasFocus) { //  Somente realiza a validação quando o campo perde o foco
                validaCPF.isValido();
            } else {
                try {
                    final CPFFormatter cpfFormatter = new CPFFormatter();
                    String conteudo = campoCPF.getText().toString();
                    conteudo = cpfFormatter.unformat(conteudo);
                    campoCPF.setText(conteudo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        TextInputLayout textInputEmail = findViewById(R.id.formulario_cadastro_campo_email);
        adicionaValidacaoPadrao(textInputEmail);

        TextInputLayout textInputTel = findViewById(R.id.formulario_cadastro_campo_telefone);
        adicionaValidacaoPadrao(textInputTel);

        TextInputLayout textInputSenha = findViewById(R.id.formulario_cadastro_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);

    }

    private void adicionaValidacaoPadrao(TextInputLayout textInputLayout) {
        EditText campo = textInputLayout.getEditText();
        campo.setOnFocusChangeListener((view, hasFocus) -> {
            ValidacaoPadrao validacaoPadrao = new ValidacaoPadrao(textInputLayout);
            if(!hasFocus) { // Somente realiza a validação quando o campo perde o foco
                validacaoPadrao.isConteudoValido();
            }
        });
    }
}
