package de.slag.basic.backend.impl.auth;

public interface AuthService {

	String getToken(String username, String password);

	String getUsername(String token);

	boolean isValid(String token);

}
