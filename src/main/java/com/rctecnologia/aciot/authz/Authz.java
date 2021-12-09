package com.rctecnologia.aciot.authz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.model.User;
import com.rctecnologia.aciot.repository.PointRepository;
import com.rctecnologia.aciot.repository.UserRepository;

/*
 * Algoritmo
 * Recebe topico e Id dos usuparios
 * Recupera atributos atributos
 * monta contexto
 * Verifica no banco as políticas
 * retorna sim ou não.
 */

public class Authz {

	Point point;
	User usuario;
    
    @Autowired
    private PointRepository pointRepository;
    
    @Autowired
    private UserRepository userRepository;
    
	public void authorization(String topic, Long id){
		Calendar cal = Calendar.getInstance();
		
		String day =String.valueOf(cal.get(Calendar.DAY_OF_WEEK));
				
		String hour =  new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
		
		Optional<User> user = userRepository.findById(id);
		
		usuario = user.get();
		
		String role = usuario.getRole();
		
		
		
		
	}
}
