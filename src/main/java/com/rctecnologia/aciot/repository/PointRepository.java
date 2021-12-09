package com.rctecnologia.aciot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rctecnologia.aciot.model.Point;


public interface PointRepository extends JpaRepository<Point, Long> {

}