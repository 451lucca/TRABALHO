package com.unilopers.academia.repository;

import com.unilopers.academia.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
