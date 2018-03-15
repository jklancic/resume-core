package xyz.blackmonster.resume.services;

import java.util.List;

import javax.inject.Inject;

import xyz.blackmonster.resume.models.Achievement;
import xyz.blackmonster.resume.repositories.AchievementDAO;

/**
 * Achievement service
 */
public class AchievementServiceImpl implements AchievementService {
	
	private AchievementDAO achievementDAO;
	
	@Inject
	public AchievementServiceImpl(AchievementDAO achievementDAO) {
		this.achievementDAO = achievementDAO;
	}

	@Override
	public List<Achievement> getAllByUser(String userUuid) {
		return achievementDAO.getAllByUser(userUuid);
	}

	@Override
	public Achievement getByUuid(String uuid, String userUuid) {
		return achievementDAO.getByUuid(uuid, userUuid);
	}
}
