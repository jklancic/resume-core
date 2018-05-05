package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Role;
import xyz.blackmonster.resume.models.User;
import xyz.blackmonster.resume.ws.response.UserWS;

/**
 * Mapper for transforming to and from UserWS
 */
public class UserWSMapper {

	public static UserWS toWS(User user) {
		return new UserWS(
			user.getUuid(),
			user.getUsername(),
			user.getPassword(),
			user.getRole().name());
	}

	public static User toModel(UserWS userWS) {
		return new User(
			userWS.getUuid(),
			userWS.getUsername(),
			userWS.getPassword(),
			Role.valueOf(userWS.getRole()));
	}
}
