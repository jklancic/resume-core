package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.model.builder.UserBuilder;
import xyz.blackmonster.resume.ws.response.UserWS;

/**
 * Mapper for transforming to and from UserWS
 */
public class UserWSMapper {

	public static UserWS toWS(User user) {
		return new UserWS(user);
	}

	public static User toModel(UserWS userWS) {
		return new UserBuilder()
			.uuid(userWS.getUuid())
			.username(userWS.getUsername())
			.role(Role.valueOf(userWS.getRole()))
			.build();
	}
}
