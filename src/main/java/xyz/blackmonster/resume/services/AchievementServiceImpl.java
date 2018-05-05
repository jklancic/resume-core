package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import xyz.blackmonster.resume.models.Achievement;
import xyz.blackmonster.resume.repositories.dao.AchievementDAO;
import xyz.blackmonster.resume.ws.response.AchievementWS;
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
	public List<AchievementWS> getAllByPerson(String personUuid) {
		return achievementDAO.getAllByPerson(personUuid).stream().map(AchievementWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	public AchievementWS getByUuid(String uuid, String personUuid) {
		Optional<Achievement> optionalAchievement = achievementDAO.getByUuid(uuid, personUuid);
		if(!optionalAchievement.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any achievement with uuid=%s and person uuid=%s", uuid, personUuid));
		}
		return AchievementWSMapper.toWS(optionalAchievement.get());
	}

	@Override
	public AchievementWS create(AchievementWS achievementWS) {
		return null;
	}

	@Override
	public AchievementWS update(String uuid, AchievementWS achievementWS) {
		return null;
	}

	@Override
	public void delete(String uuid) {

	}
}
