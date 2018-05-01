package xyz.blackmonster.resume.ws.response;

/**
 * Web Service response object for ContactInfo
 */
public class ContactInfoWS {

	private String uuid;
	private String email;
	private String phone;
	private String street;
	private String city;
	private String postalCode;
	private String country;
	
	public ContactInfoWS() {
		
	}

	public ContactInfoWS(String uuid, String email, String phone, String street, String city, String postalCode, String country) {
		this.uuid = uuid;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
