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

		http.formLogin().loginPage("/login").defaultSuccessUrl("/my-account?login").failureUrl("/login?error");
		http.logout().logoutSuccessUrl("/?logout");

		http.authorizeRequests().antMatchers("/my-account/**").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/test/**").access("hasRole('ROLE_ADMIN')").and().formLogin();
		http.authorizeRequests().antMatchers("/auth/**").access("hasRole('ROLE_USER')").and().formLogin();

		http.authorizeRequests().antMatchers("/events/create").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/events/*").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/events/*/update").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/events/*/delete").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/events/*/enroll").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/events/*/unenroll").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/events/*/invite").access("hasRole('ROLE_USER')").and().formLogin();

		http.authorizeRequests().antMatchers("/results").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/results/*").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/results/*/update").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/results/*/delete").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/results/*/participants").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/results/*/reset/*").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/results/*/insert/*").access("hasRole('ROLE_USER')").and().formLogin();

		http.authorizeRequests().antMatchers("/sports/create").access("hasRole('ROLE_ADMIN')").and().formLogin();
		http.authorizeRequests().antMatchers("/sports/*/update").access("hasRole('ROLE_ADMIN')").and().formLogin();
		http.authorizeRequests().antMatchers("/sports/*/delete").access("hasRole('ROLE_ADMIN')").and().formLogin();

		http.authorizeRequests().antMatchers("/accept/*").access("hasRole('ROLE_USER')").and().formLogin();
		http.authorizeRequests().antMatchers("/decline/*").access("hasRole('ROLE_USER')").and().formLogin();

		http.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(Constants.BC_STRENGTH);
		return passwordEncoder;
	}

}
