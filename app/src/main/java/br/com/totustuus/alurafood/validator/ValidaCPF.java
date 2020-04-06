package br.com.totustuus.alurafood.validator;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;

public class ValidaCPF {

    private final TextInputLayout textInputLayout;
    private String conteudo;
    private final ValidacaoPadrao validacaoPadrao;
    private final CPFFormatter cpfFormatter = new CPFFormatter();

    public ValidaCPF(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.conteudo = textInputLayout.getEditText().getText().toString();
        this.validacaoPadrao = new ValidacaoPadrao(textInputLayout);
    }

    private boolean hasCPFOnzeDigitos() {
        if(conteudo.length() != 11) {
            textInputLayout.setError("CPF precisa ter 11 dígitos.");
            return false;
        }
        return true;
    }

    private boolean isCPFValido() {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(conteudo);
            return true;
        } catch (Exception ex) {
            textInputLayout.setError("CPF inválido.");
        }

        return false;
    }

    public boolean isValido() {


        if(!validacaoPadrao.isConteudoValido()) return false;
        if(!hasCPFOnzeDigitos()) return false;
        if(!isCPFValido()) return false;

        conteudo = cpfFormatter.format(conteudo);
        textInputLayout.getEditText().setText(conteudo);
        return true;
    }
}
