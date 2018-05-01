package xyz.blackmonster.resume.services;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

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
	public ContactInfoWS getByUuid(String uuid) {
		Optional<ContactInfo> optionalContactInfo = contactInfoDAO.getByUuid(uuid);
		if(!optionalContactInfo.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any contact info with uuid=%s", uuid));
		}
		return ContactInfoWSMapper.toWS(optionalContactInfo.get());
	}
}
