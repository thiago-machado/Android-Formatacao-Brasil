package br.com.totustuus.alurafood.validator;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaTelefone {

    private final TextInputLayout textInputLayout;
    private String conteudo;
    private final ValidacaoPadrao validacaoPadrao;

    public ValidaTelefone(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.conteudo = textInputLayout.getEditText().getText().toString();
        this.validacaoPadrao = new ValidacaoPadrao(textInputLayout);
    }

    private boolean hasTelefoneDezOnzeDigitos() {
        if(conteudo.length() < 10 || conteudo.length() > 11) {
            textInputLayout.setError("Telefone precisa ter entre 10 e 11 dígitos.");
            return false;
        }
        return true;
    }

    public boolean isValido() {


        if(!validacaoPadrao.isConteudoValido()) return false;
        if(!hasTelefoneDezOnzeDigitos()) return false;

        adicionarFormatacao();
        return true;
    }

    private void adicionarFormatacao() {
        conteudo = conteudo.replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
        textInputLayout.getEditText().setText(conteudo);
    }

}
