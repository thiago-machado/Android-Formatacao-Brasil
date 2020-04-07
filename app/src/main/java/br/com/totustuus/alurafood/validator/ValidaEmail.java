package br.com.totustuus.alurafood.validator;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaEmail implements Validador {

    private final TextInputLayout textInputLayout;
    private String conteudo;
    private final ValidacaoPadrao validacaoPadrao;

    public ValidaEmail(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.validacaoPadrao = new ValidacaoPadrao(textInputLayout);
    }

    private boolean isEmailValido() {
        if(conteudo.matches(".+@.+\\..+")) {
            return true;
        }
        textInputLayout.setError("E-mail inválido.");
        return false;
    }

    @Override
    public boolean isValido() {
        this.conteudo = textInputLayout.getEditText().getText().toString();
        if(!validacaoPadrao.isValido()) return false;
        if(!isEmailValido()) return false;

        return true;
    }
}
