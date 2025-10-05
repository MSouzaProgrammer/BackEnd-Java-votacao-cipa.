package com.ivr.cipa.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ivr.cipa.votacao.entities.Eleitores;
import com.ivr.cipa.votacao.services.EleitoresService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/eleitor")
public class EleitorController {

    @Autowired
    private EleitoresService eleitoresService;

    @PostMapping
    public ResponseEntity<String> saveEleitor(@RequestBody Eleitores eleitores){
        Boolean saving = eleitoresService.save(eleitores);
        if (saving) {
            return ResponseEntity.ok("Salvo");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FALHA AO SALVAR");
    }

    @PutMapping("/{id}/{idVoto}")
    public ResponseEntity<String> votando(@PathVariable Long id, @PathVariable Long idVoto){
        Boolean votando = eleitoresService.votado(id, idVoto);
        if(votando){
            return ResponseEntity.ok().body("Votado");
        }else if(votando == null){
            return ResponseEntity.badRequest().body("Não encontrado!");
        }
        return ResponseEntity.badRequest().body("Ja votou!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> confimacao(@PathVariable Long id){
        return eleitoresService.eleitorFindById(id)
            .map(e -> ResponseEntity.ok(e.getVoto()))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
