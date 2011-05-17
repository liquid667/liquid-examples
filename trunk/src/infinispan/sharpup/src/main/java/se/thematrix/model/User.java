package se.thematrix.model;

import java.io.Serializable;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.ProvidedId;
import org.hibernate.search.annotations.Store;

@ProvidedId
@Indexed
public class User implements Serializable {

	private static final long serialVersionUID = 3426477320353647086L;

	private int userId;
	@Field(store = Store.YES, index=Index.TOKENIZED)
	private String userName;
	@Field(store = Store.YES, index=Index.TOKENIZED)
	private String password;
	private String firstName;
	private String lastName;
	
	private UserPreferences userPreferences;
	
	public User() {
		super();
	}
	
	public User(int userId, String userName, String password, String firstName,
			String lastName, UserPreferences userPreferences) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userPreferences = userPreferences;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(UserPreferences userPreferences) {
		this.userPreferences = userPreferences;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", userPreferences="
				+ userPreferences + "]";
	}
}
