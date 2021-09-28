package com.br.backend.agenda.agendaapi.model.repository;
import com.br.backend.agenda.agendaapi.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
