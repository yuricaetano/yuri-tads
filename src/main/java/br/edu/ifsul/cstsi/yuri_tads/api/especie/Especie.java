package br.edu.ifsul.cstsi.yuri_tads.api.especie;

import br.edu.ifsul.cstsi.yuri_tads.api.animal.Animal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Especie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cod_especie", nullable = false)
    private Long id;
    @Basic
    @Column(name = "nome_especie", nullable = true, length = 255)
    private String nome;
    @OneToMany(mappedBy = "especieByCodEspecie")
    private Collection<Animal> animalsByCodEspecie;
}
