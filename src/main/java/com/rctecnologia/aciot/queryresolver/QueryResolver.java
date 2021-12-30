package com.rctecnologia.aciot.queryresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.repository.PointRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;


/**
 * Classe responsável por controlar (Resolver), via graphql, as ações de queries no ambiente
 * As ações de queries basicamente são consultas simples ou complexas de acordo com a finalidade de aquisição de dados.
 * 
 * @author rogerio
 *
 */
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
