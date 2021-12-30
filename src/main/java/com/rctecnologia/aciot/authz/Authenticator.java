package com.rctecnologia.aciot.authz;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Collection;
import java.util.Optional;


@Component
public class Authenticator {
	
	public static final String ROLE_AUTHENTICATED = "AUTHENTICATED";
	static final String BEARER = "Bearer ";
	//estudar essa linha
	private static final Collection<GrantedAuthority> AUTHORITIES = Collections.singleton(new SimpleGrantedAuthority(ROLE_AUTHENTICATED));

	
	public boolean isAuthenticated() {
	    return (SecurityContextHolder.getContext().getAuthentication() instanceof PreAuthenticatedAuthenticationToken);
	  }
	
	
	public void doAuthenticate(String authorizationHeader) {
	    Optional.ofNullable(authorizationHeader)
	        .filter(header -> header.startsWith(BEARER))
	        .map(header -> header.substring(BEARER.length()))
	        .ifPresent(this::authenticate);	
	}


	private void authenticate(String token) {
		//  Optional.ofNullable(gameService.validateToken(token))
	    Optional.ofNullable(true)
	        .map(player -> new PreAuthenticatedAuthenticationToken(player, token, AUTHORITIES))
	        .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
	}

}
