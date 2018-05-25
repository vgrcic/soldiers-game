package com.grca.games.soldiers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private AuthenticationEntryPoint authEntryPoint;
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder encoder;
	private CsrfTokenRepository csrf;

	@Autowired
	public SpringSecurityConfig(AuthenticationEntryPoint authEntryPoint, UserDetailsService userDetailsService,
			BCryptPasswordEncoder encoder, CsrfTokenRepository csrf) {
		this.authEntryPoint = authEntryPoint;
		this.userDetailsService = userDetailsService;
		this.encoder = encoder;
		this.csrf = csrf;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.csrf().csrfTokenRepository(csrf).and()
			.authorizeRequests()
				.antMatchers("/register").permitAll()
				.anyRequest().authenticated()
			.and().formLogin()
				.permitAll()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureForwardUrl("/login")
				.loginProcessingUrl("/perform_login")
			.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
			.and().httpBasic()
				.authenticationEntryPoint(authEntryPoint);
	}
	
	@Autowired
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder);
	}
	
	public SpringSecurityConfig() {}

}
