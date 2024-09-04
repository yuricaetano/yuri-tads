package br.edu.ifsul.cstsi.yuri_tads.api.especie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EspecieDTOPut(
        @NotBlank(message = "O nome não pode ser nulo ou vazio") //verifica se está vazio e estabelece como obrigatório (não pode ser nulo)
        @Size(min = 2, max = 50, message = "Tamanho mínimo de 2 e máximo de 200")
        String nome
) {
    public EspecieDTOPut(Especie especie){
        this(especie.getNome());
    }
}