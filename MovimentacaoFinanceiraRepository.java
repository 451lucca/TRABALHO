package com.unilopers.academia.repository;

import com.unilopers.academia.model.MovimentacaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoFinanceiraRepository extends JpaRepository<MovimentacaoFinanceira, Long> {
}
