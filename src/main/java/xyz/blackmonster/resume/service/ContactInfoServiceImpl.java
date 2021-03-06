package xyz.blackmonster.resume.service;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.repository.dao.ContactInfoDAO;
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
	public ContactInfoWS getByPersonUuid(String personUuid) {
		Optional<ContactInfo> optionalContactInfo = contactInfoDAO.getByPersonUuid(personUuid);
		if(!optionalContactInfo.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any contact info for person with uuid=%s", personUuid));
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
