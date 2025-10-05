package com.ivr.cipa.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivr.cipa.votacao.entities.Eleitores;

@Repository
public interface EleitoresRepository extends JpaRepository<Eleitores, Long>{

}
