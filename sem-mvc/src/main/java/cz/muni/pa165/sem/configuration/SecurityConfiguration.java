package cz.muni.pa165.sem.configuration;

import cz.muni.pa165.sem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Matej Majdis
 */
@Configuration
@EnableWebSecurity
@ComponentScan({"cz.muni.pa165.sem.service"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	@Qualifier("semUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/test/**").access("hasRole('ROLE_ADMIN')").and().formLogin();
		http.authorizeRequests().antMatchers("/auth/**").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')").and().formLogin();
		http.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(Constants.BC_STRENGTH);
		return passwordEncoder;
	}
}

