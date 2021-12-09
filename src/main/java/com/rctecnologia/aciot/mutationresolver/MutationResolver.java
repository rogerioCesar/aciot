package com.rctecnologia.aciot.mutationresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.repository.PointRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class MutationResolver implements GraphQLMutationResolver {

	private PointRepository pointRepository;

	@Autowired
	public MutationResolver(PointRepository pointRepository) {
		
		this.pointRepository = pointRepository;
		
	}

	public Point createPoint(String typepoint, String value, String name) {
		Point point = new Point();
		
		point.setTypepoint(typepoint);
		point.setValue(value);
		point.setName(name);
		
		pointRepository.save(point);
		
		return point;
	}
	
	public boolean deletePoint(Long id) {
	    pointRepository.deleteById(id);
	    return true;
	  }
	
	
}
