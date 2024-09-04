package br.edu.ifsul.cstsi.yuri_tads.api.animal;

import br.edu.ifsul.cstsi.yuri_tads.api.cliente.Cliente;
import br.edu.ifsul.cstsi.yuri_tads.api.especie.Especie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Animal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cod_animal", nullable = false)
    private Long cod_animal;
    @Basic
    @Column(name = "nome_animal", nullable = true, length = 255)
    private String nome_animal;
    @Basic
    @Column(name = "idade_animal", nullable = true)
    private Integer idade_animal;
    @Basic
    @Column(name = "sexo_animal", nullable = true)
    private Integer sexo_animal;

    @ManyToOne
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", nullable = false)
    private Cliente clienteByCodCliente;
    @ManyToOne
    @JoinColumn(name = "cod_especie", referencedColumnName = "cod_especie", nullable = false)
    private Especie especieByCodEspecie;
}
