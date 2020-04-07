package br.com.totustuus.alurafood.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.totustuus.alurafood.R;
import br.com.totustuus.alurafood.validator.ValidaCPF;
import br.com.totustuus.alurafood.validator.ValidaEmail;
import br.com.totustuus.alurafood.validator.ValidaTelefone;
import br.com.totustuus.alurafood.validator.ValidacaoPadrao;
import br.com.totustuus.alurafood.validator.Validador;

public class FormularioAlunoActivity extends AppCompatActivity {

    private List<Validador> validadores = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        validacaoCampoNome();
        validacaoCampoCPF();
        validacaoCampoEmail();
        validaCampoTelefone();
        validacaoCampoSenha();

        Button botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastrar.setOnClickListener(v -> {
            boolean cadastroOK = true;
            for(Validador validador : validadores) {
                if(!validador.isValido()) {
                    cadastroOK = false;
                }
            }

            if(cadastroOK) {
                Toast.makeText(this, "Cadastro com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validacaoCampoNome() {
        TextInputLayout textInputNome = findViewById(R.id.formulario_cadastro_campo_nome_completo);
        adicionaValidacaoPadrao(textInputNome);
    }

    private void validacaoCampoSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.formulario_cadastro_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void validacaoCampoEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.formulario_cadastro_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        final ValidaEmail validaEmail = new ValidaEmail(textInputEmail);
        validadores.add(validaEmail);

        campoEmail.setOnFocusChangeListener((view, hasFocus) -> {
            if(!hasFocus) {
                validaEmail.isValido();
            }
        });
    }

    private void validaCampoTelefone() {
        TextInputLayout textInputTel = findViewById(R.id.formulario_cadastro_campo_telefone);
        EditText campoTel = textInputTel.getEditText();
        ValidaTelefone validaTelefone = new ValidaTelefone(textInputTel);
        validadores.add(validaTelefone);

        campoTel.setOnFocusChangeListener((view, hasFocus) -> {
            if(!hasFocus) {
                validaTelefone.isValido();
            } else {
                String conteudo = campoTel.getText().toString();
                conteudo = conteudo.replace("(", "").replace(") ", "").replace("-", "");
                campoTel.setText(conteudo);
            }
        });
    }

    private void validacaoCampoCPF() {
        TextInputLayout textInputCPF = findViewById(R.id.formulario_cadastro_campo_cpf);
        EditText campoCPF = textInputCPF.getEditText();
        ValidaCPF validaCPF = new ValidaCPF(textInputCPF);
        validadores.add(validaCPF);

        campoCPF.setOnFocusChangeListener((view, hasFocus) -> {
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
    }

    private void adicionaValidacaoPadrao(TextInputLayout textInputLayout) {
        EditText campo = textInputLayout.getEditText();
        ValidacaoPadrao validacaoPadrao = new ValidacaoPadrao(textInputLayout);
        validadores.add(validacaoPadrao);

        campo.setOnFocusChangeListener((view, hasFocus) -> {
            if(!hasFocus) { // Somente realiza a validação quando o campo perde o foco
                validacaoPadrao.isValido();
            }
        });
    }
}
