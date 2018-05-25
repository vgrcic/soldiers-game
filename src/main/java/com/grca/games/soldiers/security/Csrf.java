package com.grca.games.soldiers.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Component;

@Component
public class Csrf {
	
	@Bean
	public CsrfTokenRepository getCsrfTokenRepository() {
		return CookieCsrfTokenRepository.withHttpOnlyFalse();
	}

}
