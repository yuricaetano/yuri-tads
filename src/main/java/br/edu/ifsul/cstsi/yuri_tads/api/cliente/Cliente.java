package br.edu.ifsul.cstsi.yuri_tads.api.cliente;

import br.edu.ifsul.cstsi.yuri_tads.api.animal.Animal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cod_cliente", nullable = false)
    private Long cod_cliente;
    @Basic
    @Column(name = "nome_cliente", nullable = true, length = 255)
    private String nome_cliente;
    @Basic
    @Column(name = "endereco", nullable = true, length = 255)
    private String endereco;
    @Basic
    @Column(name = "telefone", nullable = true, length = 255)
    private String telefone;
    @Basic
    @Column(name = "cep", nullable = true, length = 255)
    private String cep;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @OneToMany(mappedBy = "clienteByCodCliente")
    private Collection<Animal> animalsByCodCliente;
}

/*
    O Validation vai depender dos requisitos do sistema. A implementação realizada aqui é uma demonstração
    de sua aplicação.
 */

