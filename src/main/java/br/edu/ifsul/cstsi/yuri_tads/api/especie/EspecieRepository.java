package br.edu.ifsul.cstsi.yuri_tads.api.especie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EspecieRepository extends JpaRepository<Especie,Long> {
    @Query(value = "SELECT p FROM Especie p where p.nome like ?1 order by p.nome")
    List<Especie> findByNome(String nome);
}