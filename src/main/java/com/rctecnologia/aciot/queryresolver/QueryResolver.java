package com.rctecnologia.aciot.queryresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.repository.PointRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class QueryResolver implements GraphQLQueryResolver {
	
	
	private PointRepository pointRepository;
	
	

	@Autowired
	public QueryResolver(PointRepository pointRepository) {
		this.pointRepository = pointRepository;
		
	}	
	
	public Iterable<Point> findAllPoints() {
		return pointRepository.findAll();
	}	
	
	
}
