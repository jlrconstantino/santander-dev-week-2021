package com.example.SantanderDevWeekBackend.repository;

import com.example.SantanderDevWeekBackend.model.Stock;
import com.example.SantanderDevWeekBackend.model.dto.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // Autointerpretado pelo spring: seleciona por nome e data
    Optional<Stock> findByNameAndDate(String name, LocalDate date);

    // Query criada manualmente: baseada em nome, data e ID
    @Query (
        "SELECT stock " +
        "FROM Stock stock " +
        "WHERE stock.name = :name AND stock.date = :date AND stock.id <> :id "
    )
    Optional<Stock> findByStockUpdate(String name, LocalDate date, Long id);

    // Seleção baseada na data corrente
    @Query (
        "SELECT stock " +
        "FROM Stock stock " +
        "WHERE stock.date = :date "
    )
    Optional<List<Stock>> findByToday(LocalDate date);
}
