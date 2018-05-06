package xyz.blackmonster.resume.services;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.models.ContactInfo;
import xyz.blackmonster.resume.repositories.dao.ContactInfoDAO;
import xyz.blackmonster.resume.ws.mapper.ContactInfoWSMapper;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;

/**
 * Contact info service
 */
public class ContactInfoServiceImpl implements ContactInfoService {
	
	private ContactInfoDAO contactInfoDAO;
	
	@Inject
	public ContactInfoServiceImpl(ContactInfoDAO contactInfoDAO) {
		this.contactInfoDAO = contactInfoDAO;
	}
	
	@Override
	@Transaction
	public ContactInfoWS getByUuid(String uuid) {
		Optional<ContactInfo> optionalContactInfo = contactInfoDAO.getByUuid(uuid);
		if(!optionalContactInfo.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any contact info with uuid=%s", uuid));
		}
		return ContactInfoWSMapper.toWS(optionalContactInfo.get());
	}

	@Override
	@Transaction
	public ContactInfoWS getByPersonUuid(String userUuid) {
		Optional<ContactInfo> optionalContactInfo = contactInfoDAO.getByPersonUuid(userUuid);
		if(!optionalContactInfo.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any contact info for person with uuid=%s", userUuid));
		}
		return ContactInfoWSMapper.toWS(optionalContactInfo.get());
	}

	@Override
	@Transaction
	public ContactInfoWS update(ContactInfoWS contactInfoWS) {
		contactInfoDAO.update(ContactInfoWSMapper.toModel(contactInfoWS));
		return getByUuid(contactInfoWS.getUuid());
	}
}
