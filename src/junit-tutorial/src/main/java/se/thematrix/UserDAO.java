package se.thematrix;

public interface UserDAO {
	User loadByUsernameAndPassword(String userName, String passwordHash);
}
