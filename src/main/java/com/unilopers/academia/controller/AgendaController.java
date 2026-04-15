package com.unilopers.academia.controller;

import com.unilopers.academia.model.Agenda;
import com.unilopers.academia.repository.AgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agendas")
@CrossOrigin(origins = "*")
public class AgendaController {

    private final AgendaRepository repository;

    public AgendaController(AgendaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Agenda> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agenda criar(@RequestBody Agenda agenda) {
        return repository.save(agenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizar(@PathVariable Long id, @RequestBody Agenda agendaAtualizada) {
        return repository.findById(id)
                .map(agenda -> {
                    agenda.setTitulo(agendaAtualizada.getTitulo());
                    agenda.setDescricao(agendaAtualizada.getDescricao());
                    agenda.setDataHora(agendaAtualizada.getDataHora());
                    agenda.setNomeAluno(agendaAtualizada.getNomeAluno());
                    agenda.setAtividade(agendaAtualizada.getAtividade());
                    agenda.setStatus(agendaAtualizada.getStatus());
                    return ResponseEntity.ok(repository.save(agenda));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
