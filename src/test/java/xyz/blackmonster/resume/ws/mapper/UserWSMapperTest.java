package xyz.blackmonster.resume.ws.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.model.builder.UserBuilder;
import xyz.blackmonster.resume.ws.response.UserWS;

public class UserWSMapperTest {

	@Test
	public void testToWS() {
		User user = new UserBuilder()
			.uuid("uuid")
			.username("username")
			.role(Role.USER)
			.build();

		UserWS userWS = UserWSMapper.toWS(user);
		validate(user, userWS);
	}

	@Test
	public void testToModel() {
		UserWS userWS = new UserWS();
		userWS.setUuid("uuid");
		userWS.setUsername("username");
		userWS.setRole(Role.USER.name());

		User user = UserWSMapper.toModel(userWS);
		validate(user, userWS);
	}

	private void validate(User user, UserWS userWS) {
		assertThat(user.getUuid()).isEqualTo(userWS.getUuid());
		assertThat(user.getUsername()).isEqualTo(userWS.getUsername());
		assertThat(user.getRole().name()).isEqualTo(userWS.getRole());
	}
}
