package se.thematrix.model;

public class UserPreferences {
	
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "UserPreferences [language=" + language + "]";
	}
}
