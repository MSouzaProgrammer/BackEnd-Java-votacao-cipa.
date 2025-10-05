package com.ivr.cipa.votacao.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ivr.cipa.votacao.entities.Eleitores;
import com.ivr.cipa.votacao.repository.EleitoresRepository;

@Service
public class EleitoresService {
    private final EleitoresRepository eleitoresRepository;
    private final ParticipanteService participanteService; 

    public EleitoresService(EleitoresRepository eleitoresRepository, ParticipanteService participanteService) {
        this.eleitoresRepository = eleitoresRepository;
        this.participanteService = participanteService;
    }

    public Boolean save(Eleitores participante){
        eleitoresRepository.save(participante);
        return true;
    }

    public Optional<Eleitores> eleitorFindById(Long id){
        return eleitoresRepository.findById(id);
    }

    public Boolean votado(Long id, Long idVoto){
        Optional<Eleitores> eleitorOpt = eleitorFindById(id);
        if(eleitorOpt.isPresent()){
            Eleitores eleitor = eleitorOpt.get();
            if(Boolean.FALSE.equals(eleitor.getVoto())){
                participanteService.somarVoto(idVoto);
                eleitor.setVoto(true);
                eleitoresRepository.save(eleitor);
                return true;
            }
            return false;
        }
        return null;       
    }

    public Boolean confirmacao(Long id){
        return eleitorFindById(id).map(Eleitores::getVoto).orElse(null);
    }
}
