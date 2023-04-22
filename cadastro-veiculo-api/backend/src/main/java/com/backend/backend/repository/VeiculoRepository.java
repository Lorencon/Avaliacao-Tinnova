package com.backend.backend.repository;

import com.backend.backend.domain.Veiculo;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByAnoBetween(Integer inicio, Integer fim);
    @Query("SELECT FLOOR(v.ano/10)*10 as decada, COUNT(v.id) as quantidade FROM Veiculo v GROUP BY decada")
    List<Object[]> countVeiculosPorDecada();

    List<Veiculo> findByCreatedBetween(LocalDate inicio, LocalDate fim);

    List<Veiculo> findByCreatedGreaterThanEqual(LocalDate dataInicio);

    List<Veiculo> findAllByUpdatedAfter(LocalDate umaSemanaAtras);

 //   void update(Object veiculos);
}
