package br.edu.ifsul.cstsi.yuri_tads.api.infra.exception;

public class ValidacaoEmailJaCadastradoException extends RuntimeException {
    public ValidacaoEmailJaCadastradoException(String message) {
        super(message);
    }
}
