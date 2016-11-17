package cz.muni.pa165.sem.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Matej Majdis
 */
@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfiguration {

	@Value("${mail.host}")
	private String host;

	@Value("${mail.socketFactory.port}")
	private String socketFactoryPort;

	@Value("${mail.socketFactory.class}")
	private String socketFactoryClass;

	@Value("${mail.auth}")
	private String auth;

	@Value("${mail.port}")
	private String port;

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.address}")
	private String address;

	@Value("${mail.personal}")
	private String personal;

	public String getHost() {
		return host;
	}

	public String getSocketFactoryPort() {
		return socketFactoryPort;
	}

	public String getSocketFactoryClass() {
		return socketFactoryClass;
	}

	public String getAuth() {
		return auth;
	}

	public String getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getPersonal() {
		return personal;
	}
}
