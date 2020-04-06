package br.com.totustuus.alurafood.validator;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaEmail {

    private final TextInputLayout textInputLayout;
    private String conteudo;
    private final ValidacaoPadrao validacaoPadrao;

    public ValidaEmail(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.conteudo = textInputLayout.getEditText().getText().toString();
        this.validacaoPadrao = new ValidacaoPadrao(textInputLayout);
    }

    private boolean isEmailValido() {
        if(conteudo.matches(".+@.+\\..+")) {
            return true;
        }
        textInputLayout.setError("E-mail inválido.");
        return false;
    }

    public boolean isValido() {
        if(!validacaoPadrao.isConteudoValido()) return false;
        if(!isEmailValido()) return false;

        return true;
    }
}
