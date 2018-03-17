package xyz.blackmonster.resume.models;

/**
 * Defined category for skill set
 */
public class Category {
	
	private String uuid;
	private String name;
	
	public Category() {
		
	}

	public Category(String uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
