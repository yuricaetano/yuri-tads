package br.edu.ifsul.cstsi.yuri_tads.api.especie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service //indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean da camada de serviço de dados
public class EspecieService {

    @Autowired //indica ao Spring Boot que ele deve injetar essa dependência para a classe funcionar
    private EspecieRepository rep;

    public Page<Especie> getEspecies(Pageable paginacao) {
        return rep.findAll(paginacao);
    }

    public Optional<Especie> getEspecieById(Long id) {
        return rep.findById(id);
    }

    public List<Especie> getEspeciesByNome(String nome) {
        return rep.findByNome(nome+"%");
    }

    public Especie insert(Especie especie) {
        Assert.isNull(especie.getId(),"Não foi possível inserir o registro");
        return rep.save(especie);
    }

    public Especie update(Especie especie) {
        Assert.notNull(especie.getId(),"Não foi possível atualizar o registro");

        // Busca o especie no banco de dados
        var optional = rep.findById(especie.getId());
        if(optional.isPresent()) {
            var db = optional.get();
            // Copia as propriedades
            db.setNome(especie.getNome());
            return rep.save(db);
        }
        return null;

    }

    public boolean delete(Long id) {
        var optional = rep.findById(id);
        if(optional.isPresent()) {
            rep.deleteById(id);
            return true;
        }
        return false;
    }
}