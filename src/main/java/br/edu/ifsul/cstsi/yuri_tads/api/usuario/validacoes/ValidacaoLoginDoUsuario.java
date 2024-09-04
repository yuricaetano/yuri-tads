package br.edu.ifsul.cstsi.yuri_tads.api.usuario.validacoes;

import br.edu.ifsul.cstsi.yuri_tads.api.usuario.UsuarioDTO;

public interface ValidacaoLoginDoUsuario {
    void validar(UsuarioDTO usuarioDTO);
}
