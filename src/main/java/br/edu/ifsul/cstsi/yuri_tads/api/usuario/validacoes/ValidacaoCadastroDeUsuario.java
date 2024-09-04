package br.edu.ifsul.cstsi.yuri_tads.api.usuario.validacoes;

import  br.edu.ifsul.cstsi.yuri_tads.api.usuario.Usuario;

public interface ValidacaoCadastroDeUsuario {
    void validar(Usuario usuario);
}