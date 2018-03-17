package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import xyz.blackmonster.resume.repositories.AchievementDAO;
import xyz.blackmonster.resume.ws.AchievementWS;
import xyz.blackmonster.resume.ws.mapper.AchievementWSMapper;

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
	public List<AchievementWS> getAllByUser(String personUuid) {
		return achievementDAO.getAllByUser(personUuid).stream().map(AchievementWSMapper::convert).collect(Collectors.toList());
	}

	@Override
	public AchievementWS getByUuid(String uuid, String personUuid) {
		return AchievementWSMapper.convert(achievementDAO.getByUuid(uuid, personUuid));
	}
}
