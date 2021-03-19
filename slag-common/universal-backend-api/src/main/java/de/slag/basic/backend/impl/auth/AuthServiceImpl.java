package de.slag.basic.backend.impl.auth;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.basic.backend.impl.adm.AdmConfigService;

public abstract class AuthServiceImpl implements AuthService {

	private static final String PASSWORD_POSTFIX = ".password";

	private static final String USERNAME_POSTFIX = ".username";

	private static final String ADM_USER_PREFIX = "adm.user.";

	private static final Log LOG = LogFactory.getLog(AuthServiceImpl.class);

	@Resource
	private AdmConfigService admConfigService;

	private Map<String, Collection<AuthToken>> tokenMap = new HashMap<>();

	private Collection<User> users = new ArrayList<>();

	@PostConstruct
	public void setUp() {
		setUpUsers();
	}

	@Override
	public String getToken(String username, String password) {
		final User user = users.stream()
				.filter(u -> u.getUsername()
						.equals(username))
				.findAny()
				.get();
		if (!user.getPassword()
				.equals(password)) {
			throw new RuntimeException("password wrong");
		}

		if (!tokenMap.containsKey(username)) {
			tokenMap.put(username, new ArrayList<>());
		}
		final Collection<AuthToken> collection = tokenMap.get(username);
		final AuthToken authToken = new AuthToken();
		collection.add(authToken);
		return authToken.getToken();
	}

	private void setUpUsers() {
		final Map<String, String> properties = admConfigService.getProperties(ADM_USER_PREFIX);
		LOG.info(properties);
		final Set<String> userIds = properties.keySet()
				.stream()
				.map(k -> (String) k)
				.map(k -> k.split("\\.")[2])
				.collect(Collectors.toSet());

		users.addAll(userIds.stream()
				.map(id -> {
					String key = ADM_USER_PREFIX + id + USERNAME_POSTFIX;
					LOG.debug(key);
					String key2 = ADM_USER_PREFIX + id + PASSWORD_POSTFIX;
					String username = properties.get(key);
					String password = properties.get(key2);

					return new User(Long.valueOf(id), username, password);

				})
				.collect(Collectors.toList()));
		LOG.info("users: " + users);
	}

	private class AuthToken {
		private final String token;

		private final LocalDateTime created = LocalDateTime.now();

		public AuthToken() {
			super();
			this.token = String.valueOf(System.currentTimeMillis());
		}

		public String getToken() {
			return token;
		}

		public LocalDateTime getCreated() {
			return created;
		}

	}

	private class User {

		private final Long id;

		private final String username;

		private final String password;

		public User(Long id, String username, String password) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
		}

		public Long getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
		}

	}

	@Override
	public String getUsername(String token) {
		return tokenMap.keySet()
				.stream()
				.filter(username -> {
					return tokenMap.get(username)
							.stream()
							.anyMatch(authToken -> authToken.getToken()
									.equals(token));
				})
				.findAny()
				.get();
	}

	@Override
	public boolean isValid(String token) {
		return getUsername(token) != null;
	}
}
