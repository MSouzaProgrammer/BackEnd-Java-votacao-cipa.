package com.ivr.cipa.votacao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_eleitores")
public class Eleitores {
    @Id
    @Getter
    @Setter
    private Long id;
    private Boolean voto;   
    public Eleitores() {
    }
    public Eleitores(Long id, Boolean voto) {
        this.id = id;
        this.voto = voto;
    }
    public Boolean getVoto() {
        return voto;
    }
    public void setVoto(Boolean voto) {
        this.voto = voto;
    }
}
