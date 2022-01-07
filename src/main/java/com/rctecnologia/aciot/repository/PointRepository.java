package com.rctecnologia.aciot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rctecnologia.aciot.model.Point;



/**
 * Interface padrão de um JPA Repository.
 * Executa funções de persistência de dados dos pontos do ambiente IoT
 * @author rogerio
 *
 */
public interface PointRepository extends MongoRepository<Point, Long> {

	Point findByName(String name);
}