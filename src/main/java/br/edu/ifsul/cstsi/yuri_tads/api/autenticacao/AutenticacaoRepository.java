package br.edu.ifsul.cstsi.yuri_tads.api.autenticacao;

import br.edu.ifsul.cstsi.yuri_tads.api.usuario.Usuario;
import org.springframework.data.repository.Repository;
public interface AutenticacaoRepository extends Repository<Usuario,Long> {
    Usuario findByEmail(String email);
}