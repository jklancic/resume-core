package xyz.blackmonster.resume.model.builder;

import xyz.blackmonster.resume.model.ContactInfo;

/**
 * Contact info builder
 */
public class ContactInfoBuilder implements Builder<ContactInfo> {

	private ContactInfo contactInfo;

	private String uuid;
	private String email;
	private String phone;
	private String street;
	private String city;
	private String postalCode;
	private String country;

	public ContactInfoBuilder() {
		contactInfo = new ContactInfo();
	}

	public ContactInfoBuilder uuid(String uuid) {
		contactInfo.setUuid(uuid);
		return this;
	}

	public ContactInfoBuilder email(String email) {
		contactInfo.setEmail(email);
		return this;
	}

	public ContactInfoBuilder phone(String phone) {
		contactInfo.setPhone(phone);
		return this;
	}

	public ContactInfoBuilder street(String street) {
		contactInfo.setStreet(street);
		return this;
	}

	public ContactInfoBuilder city(String city) {
		contactInfo.setCity(city);
		return this;
	}

	public ContactInfoBuilder postalCode(String postalCode) {
		contactInfo.setPostalCode(postalCode);
		return this;
	}

	public ContactInfoBuilder country(String country) {
		contactInfo.setCountry(country);
		return this;
	}

	@Override
	public ContactInfo build() {
		return contactInfo;
	}
}
