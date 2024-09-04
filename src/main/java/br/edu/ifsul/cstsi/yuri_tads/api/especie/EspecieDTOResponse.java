package br.edu.ifsul.cstsi.yuri_tads.api.especie;

import java.math.BigDecimal;

public record EspecieDTOResponse(
        Long id,
        String nome
) {
    public EspecieDTOResponse(Especie especie){
        this(especie.getId(), especie.getNome());
    }
}