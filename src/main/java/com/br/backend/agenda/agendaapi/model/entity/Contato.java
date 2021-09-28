package com.br.backend.agenda.agendaapi.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String email;

    @Column
    private Boolean favorito;

    @Column
    @Lob  //diz para o jpa que é um array de bytes e não um coluna normal
    private byte[] foto;

}
