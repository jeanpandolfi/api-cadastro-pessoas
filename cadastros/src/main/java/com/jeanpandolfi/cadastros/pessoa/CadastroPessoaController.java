package com.jeanpandolfi.cadastros.pessoa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/cadastrar-pessoa")
public class CadastroPessoaController {

    public CadastroPessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    private final PessoaRepository pessoaRepository;


    @PostMapping
    ResponseEntity<Void> cadastrarDadosPessoais(@RequestBody @Valid NovaPessoaRequest novaPessoaRequest){
        Pessoa pessoa = novaPessoaRequest.toEntity();
        Pessoa pessoaSalva = pessoaRepository.save(novaPessoaRequest.toEntity());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand( pessoaSalva.getId() ).toUri();

        return  ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<NovaPessoaResponse> obterPessoaPorId(@PathVariable("id") Long id){
        NovaPessoaResponse response = pessoaRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .toResponse();
        return ResponseEntity.ok(response);
    }
}
