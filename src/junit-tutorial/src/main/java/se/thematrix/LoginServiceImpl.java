package se.thematrix;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginServiceImpl implements LoginService {

	private UserDAO userDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean login(String userName, String password) {
		boolean valid = false;
		
		try {
			String passwordHash = null;
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			passwordHash = new String(md5.digest());
			System.out.println(passwordHash);
			
			User results = userDao.loadByUsernameAndPassword(userName, passwordHash);
			if(results != null){
				valid = true;
			}
			
		} catch (Exception e) {}
		return valid;
	}

}
