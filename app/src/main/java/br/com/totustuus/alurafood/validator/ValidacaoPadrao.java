package br.com.totustuus.alurafood.validator;

import com.google.android.material.textfield.TextInputLayout;

public class ValidacaoPadrao {

    private final TextInputLayout textInputLayout;
    private final String conteudo;

    public ValidacaoPadrao(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.conteudo = textInputLayout.getEditText().getText().toString();
    }

    /*
    Retorna FALSE caso não esteja OK
     */
    private boolean isCampoObrigatorioOK() {
        if (conteudo.isEmpty()) {
            textInputLayout.setError("Campo obrigatório");
            return false;
        }

        removeErroEditText();
        return true;
    }

    private void removeErroEditText() {
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }

    public boolean isConteudoValido() {
        return isCampoObrigatorioOK();
    }
}
