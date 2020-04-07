package br.com.totustuus.alurafood.validator;

import com.google.android.material.textfield.TextInputLayout;

public class ValidacaoPadrao implements Validador {

    private final TextInputLayout textInputLayout;
    private String conteudo;

    public ValidacaoPadrao(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
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

    @Override
    public boolean isValido() {
        this.conteudo = textInputLayout.getEditText().getText().toString();
        return isCampoObrigatorioOK();
    }
}
