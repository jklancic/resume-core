package xyz.blackmonster.resume.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;
import javax.ws.rs.NotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.auth0.jwt.exceptions.JWTCreationException;
import xyz.blackmonster.resume.config.app.RuntimeConfiguration;
import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.model.builder.UserBuilder;
import xyz.blackmonster.resume.repository.dao.UserDAO;
import xyz.blackmonster.resume.security.util.PasswordUtil;
import xyz.blackmonster.resume.ws.response.UserWS;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	private UserDAO userDAO;
	@Mock
	private JWTService jwtService;

	private UserService userService;

	@Before
	public void setUp() {
		userService = new UserServiceImpl(userDAO, jwtService);
	}

	@Test
	public void testGetAll() {
		User user = new UserBuilder()
			.uuid("uuid")
			.username("username")
			.role(Role.USER)
			.build();
		List<User> userList = new ArrayList<>(1);
		userList.add(user);
		when(userDAO.getAll()).thenReturn(userList);

		List<UserWS> userWSList = userService.getAll();
		assertThat(userWSList.size()).isEqualTo(userList.size());
		validate(userWSList.get(0), userList.get(0));
	}

	@Test
	public void testGetByUuid() {
		String uuid = "uuid";
		User user = new UserBuilder()
			.uuid(uuid)
			.username("username")
			.role(Role.USER)
			.build();
		when(userDAO.getByUuid(eq(uuid))).thenReturn(Optional.of(user));

		UserWS userWS = userService.getByUuid(uuid);
		validate(userWS, user);
	}

	@Test(expected = NotFoundException.class)
	public void testGetByUuid_NotFoundException() {
		when(userDAO.getByUuid(anyString())).thenReturn(Optional.empty());

		userService.getByUuid("uuid");
	}

	@Test
	public void testGetByUsername() {
		String username = "username";
		User user = new UserBuilder()
			.uuid("uuid")
			.username(username)
			.role(Role.USER)
			.build();
		when(userDAO.getByUsername(eq(username))).thenReturn(Optional.of(user));

		UserWS userWS = userService.getByUsername(username);
		validate(userWS, user);
	}

	@Test(expected = NotFoundException.class)
	public void testGetByUsername_NotFoundException() {
		when(userDAO.getByUsername(anyString())).thenReturn(Optional.empty());

		userService.getByUsername("username");
	}

	@Test
	public void testAuthenticateUser() throws Exception {
		RuntimeConfiguration.get().setJwtSecret("secret");
		String password = "password";
		String securePassword = PasswordUtil.generateSecurePassword(password);
		String username = "username";
		User user = new UserBuilder()
			.uuid("uuid")
			.username(username)
			.password(securePassword)
			.role(Role.USER)
			.build();
		when(userDAO.getByUsername(eq(username))).thenReturn(Optional.of(user));
		String token = "token";
		when(jwtService.createToken(eq(user.getUuid()), eq(user.getUsername()), eq(user.getRole().name()))).thenReturn(token);

		String createdToken = userService.authenticateUser(username, password);
		assertThat(createdToken).isEqualTo(token);
		verify(jwtService, times(1)).createToken(eq(user.getUuid()), eq(user.getUsername()), eq(user.getRole().name()));
	}

	@Test(expected = AuthenticationException.class)
	public void testAuthenticateUser_NoUser_AuthenticationException() throws Exception {
		String password = "password";
		String username = "username";
		when(userDAO.getByUsername(eq(username))).thenReturn(Optional.empty());

		userService.authenticateUser(username, password);
	}

	@Test(expected = AuthenticationException.class)
	public void testAuthenticateUser_PasswordMismatch_AuthenticationException() throws Exception {
		RuntimeConfiguration.get().setJwtSecret("secret");
		String username = "username";
		User user = new UserBuilder()
			.uuid("uuid")
			.username(username)
			.password(PasswordUtil.generateSecurePassword("password"))
			.role(Role.USER)
			.build();
		when(userDAO.getByUsername(eq(username))).thenReturn(Optional.of(user));

		userService.authenticateUser(username, "wrongPassword");
	}

	@Test(expected = JWTCreationException.class)
	public void testAuthenticateUser_JWTCreationException() throws Exception {
		RuntimeConfiguration.get().setJwtSecret("secret");
		String password = "password";
		String securePassword = PasswordUtil.generateSecurePassword(password);
		String username = "username";
		User user = new UserBuilder()
			.uuid("uuid")
			.username(username)
			.password(securePassword)
			.role(Role.USER)
			.build();
		when(userDAO.getByUsername(eq(username))).thenReturn(Optional.of(user));
		when(jwtService.createToken(eq(user.getUuid()), eq(user.getUsername()), eq(user.getRole().name()))).thenThrow(new JWTCreationException("Oops", new Exception()));

		userService.authenticateUser(username, password);
	}

	@Test
	public void testCreateUser() {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		UserWS userWS = new UserWS();
		userWS.setUsername("username");
		userWS.setPassword("password");
		userWS.setRole(Role.USER.name());
		User user = new UserBuilder()
			.username(userWS.getUsername())
			.password(userWS.getPassword())
			.role(Role.valueOf(Role.USER.name()))
			.build();
		when(userDAO.getByUuid(anyString())).thenReturn(Optional.of(user));

		UserWS createUser = userService.createUser(userWS);
		assertThat(createUser.getUsername()).isEqualTo(userWS.getUsername());
		verify(userDAO, times(1)).create(captor.capture(), eq(userWS.getUsername()), anyString(), eq(Role.USER.getRoleIndex()));
		assertThat(captor.getValue()).isNotBlank();
		verify(userDAO, times(1)).getByUuid(anyString());
	}

	@Test
	public void testUpdateUser() {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		UserWS userWS = new UserWS();
		userWS.setUuid("uuid");
		userWS.setUsername("username");
		userWS.setPassword("password");
		userWS.setRole(Role.USER.name());
		User user = new UserBuilder()
			.username(userWS.getUsername())
			.password(userWS.getPassword())
			.role(Role.valueOf(Role.USER.name()))
			.build();
		when(userDAO.getByUuid(eq(userWS.getUuid()))).thenReturn(Optional.of(user));

		UserWS createUser = userService.updateUser(userWS);
		assertThat(createUser.getUsername()).isEqualTo(userWS.getUsername());
		verify(userDAO, times(1)).update(eq(userWS.getUsername()), captor.capture(), eq(Role.USER.getRoleIndex()), eq(userWS.getUuid()));
		assertThat(captor.getValue()).isNotBlank();
		verify(userDAO, times(1)).getByUuid(eq(userWS.getUuid()));
	}

	private void validate(UserWS userWS, User user) {
		assertThat(userWS.getUuid()).isEqualTo(user.getUuid());
		assertThat(userWS.getUsername()).isEqualTo(user.getUsername());
		assertThat(userWS.getRole()).isEqualTo(user.getRole().name());
	}
}
