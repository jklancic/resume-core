package xyz.blackmonster.resume.model;

public enum Role {
	USER(0), 
	ADMIN(1);
	
	private int roleIndex;
	
	Role(int roleIndex) {
		this.roleIndex = roleIndex;
	}

	public int getRoleIndex() {
		return roleIndex;
	}

	public static Role toRole(int roleIndex) throws IllegalArgumentException {
		switch (roleIndex) {
			case 0: 
				return USER;
			case 1:
				return ADMIN;
			default:
					throw new IllegalArgumentException("Index value is not valid.");
		}
	}
}
