package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportsmanDAO;
import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matej Majdis
 */
@Service
@Qualifier("semUserDetailsService")
public class SemUserDetailService implements UserDetailsService {

	@Autowired
	private SportsmanDAO sportsmanDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Sportsman sportsman = sportsmanDAO.findByEmail(email);

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return new User(sportsman.getEmail(), sportsman.getPassword(), true, true, true, true, authorities);
	}

	public SportsmanDAO getSportsmanDAODAO() {
		return sportsmanDAO;
	}

	public void setSportsmanDAO(SportsmanDAO sportsmanDAO) {
		this.sportsmanDAO = sportsmanDAO;
	}
}


