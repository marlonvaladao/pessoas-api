package com.universidade.pessoas.controller;

import com.universidade.pessoas.dto.PessoaDTO;
import com.universidade.pessoas.model.Pessoa;
import com.universidade.pessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    // POST /pessoas
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaDTO dto) {
        Pessoa pessoa = new Pessoa(null, dto.getNome(), dto.getCpf(), dto.getIdade());
        Pessoa salva = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(salva);
    }

    // GET /pessoas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
