package br.edu.ifsul.cstsi.yuri_tads.api.especie;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController //indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean da camada de controle API REST
@RequestMapping("api/v1/especies") //Endpoint padrão da classe
public class EspecieController {

    @Autowired //indica ao Spring Boot que ele deve injetar essa dependência para a classe funcionar
    private EspecieService service;

    @GetMapping
    //O PageableDefault é sobrescrito pelos parâmetros da requisição (ou seja, a requisição é mandatória)
    //Experimente fazer a requisição assim: /api/v1/especies?size=2&sort=nome,desc (verá que sobrescreve o PageableDefault)
    public ResponseEntity<Page<EspecieDTOResponse>> selectAll(@PageableDefault(size = 50, sort = "nome") Pageable paginacao) {
        return ResponseEntity.ok(service.getEspecies(paginacao).map(EspecieDTOResponse::new));
    }

    @GetMapping("{id}")
    public ResponseEntity<EspecieDTOResponse> selectById(@PathVariable("id") Long id) {
        var p = service.getEspecieById(id);
        if(p.isPresent()){
            return ResponseEntity.ok(new EspecieDTOResponse(p.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<EspecieDTOResponse>> selectByNome(@PathVariable("nome") String nome) {
        var especies = service.getEspeciesByNome(nome);
        return especies.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(especies.stream().map(EspecieDTOResponse::new).collect(Collectors.toList()));
    }

    @PostMapping
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<URI> insert(@Valid @RequestBody EspecieDTOPost especieDTOPost, UriComponentsBuilder uriBuilder){
        var p = service.insert(new Especie(
                null,
                especieDTOPost.nome(),
                null
        ));
        var location = uriBuilder.path("api/v1/especies/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EspecieDTOResponse> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody EspecieDTOPut especieDTOPut){
        var p = service.update(new Especie(
                id,
                especieDTOPut.nome(),
                null
        ));
        return p != null ?
                ResponseEntity.ok(new EspecieDTOResponse(p)):
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return service.delete(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
