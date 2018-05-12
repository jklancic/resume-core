package xyz.blackmonster.resume.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.model.Achievement;
import xyz.blackmonster.resume.repository.dao.AchievementDAO;
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
	@Transaction
	public List<AchievementWS> getAllByPerson(String personUuid) {
		return achievementDAO.getAllByPerson(personUuid).stream().map(AchievementWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	@Transaction
	public AchievementWS getByUuid(String uuid, String personUuid) {
		Optional<Achievement> optionalAchievement = achievementDAO.getByUuid(uuid, personUuid);
		if(!optionalAchievement.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any achievement with uuid=%s and person uuid=%s", uuid, personUuid));
		}
		return AchievementWSMapper.toWS(optionalAchievement.get());
	}

	@Override
	@Transaction
	public AchievementWS create(AchievementWS achievementWS, String personUuid) {
		achievementWS.setUuid(UUID.randomUUID().toString());
		achievementDAO.create(AchievementWSMapper.toModel(achievementWS, personUuid));
		return getByUuid(achievementWS.getUuid(), personUuid);
	}

	@Override
	@Transaction
	public AchievementWS update(AchievementWS achievementWS, String personUuid) {
		achievementDAO.update(AchievementWSMapper.toModel(achievementWS, personUuid));
		return getByUuid(achievementWS.getUuid(), personUuid);
	}

	@Override
	@Transaction
	public void delete(String uuid) {
		achievementDAO.delete(uuid);
	}
}
