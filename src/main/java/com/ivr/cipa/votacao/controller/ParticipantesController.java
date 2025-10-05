package com.ivr.cipa.votacao.controller;

import java.util.List;

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

import com.ivr.cipa.votacao.entities.Participantes;
import com.ivr.cipa.votacao.services.ParticipanteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/participante")
public class ParticipantesController {
    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody Participantes participantes){
        Boolean saving = participanteService.save(participantes);
        if (saving) {
            return ResponseEntity.ok("Salvo");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FALHA AO SALVAR");
    }

    @GetMapping
    public List<Participantes> findParticipantes(){
        return participanteService.findAll();
    }
    
    @PutMapping
    public ResponseEntity<String> somarVoto(@PathVariable Long id){
        Boolean somarVoto = participanteService.somarVoto(id);
        if(somarVoto){
            return ResponseEntity.ok("Voto somado!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FALHA AO SOMAR VOTO!");
    }
}
