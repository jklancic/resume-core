package xyz.blackmonster.resume.ws.response;

/**
 * Web Service response object for Category
 */
public class CategoryWS {

	private String uuid;
	private String name;
	
	public CategoryWS() {
		
	}

	public CategoryWS(String uuid, String name) {
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
