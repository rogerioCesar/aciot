package com.rctecnologia.aciot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.model.Politica;


/**
 * Interface padrão de um JPA Repository.
 * Executa funções de persistência de dados dos pontos do ambiente IoT
 * @author rogerio
 *
 */
public interface PointRepository extends JpaRepository<Point, Long> {

	Point findByName(String name);
}