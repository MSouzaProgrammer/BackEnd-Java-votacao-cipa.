package com.ivr.cipa.votacao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ivr.cipa.votacao.entities.Participantes;
import com.ivr.cipa.votacao.repository.ParticipantesRepository;

@Service
public class ParticipanteService {
    private final ParticipantesRepository participantesRepository;

    public ParticipanteService(ParticipantesRepository participantesRepository) {
        this.participantesRepository = participantesRepository;
    }

    public Boolean save(Participantes participante){
        participantesRepository.save(participante);
        return true;
    }

    public List<Participantes> findAll(){
        return participantesRepository.findAll();
    }

    public Participantes findParticipantes(Long id){
        return participantesRepository.findById(id).orElse(null);
    }


    public boolean somarVoto(Long id){
        Participantes participante = findParticipantes(id);
        if(participante != null){
            Integer votos = participante.getVotos();
            votos += 1;
            participante.setVotos(votos);
            participantesRepository.save(participante);
            return true;
        }
        return false;
    }
}
