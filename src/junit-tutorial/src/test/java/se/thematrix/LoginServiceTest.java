package se.thematrix;

import org.easymock.*;
import junit.framework.TestCase;

import org.junit.Before;

public class LoginServiceTest extends TestCase {
	private LoginServiceImpl service;
	private UserDAO mockDao;
	
	@Before
	public void setUp() throws Exception {
		service = new LoginServiceImpl();
		mockDao = EasyMock.createStrictMock(UserDAO.class);
		service.setUserDao(mockDao);
	}

	
	public void testRosyScencario(){
		User results = new User();
		String userName = "testUserName";
		String password = "testPassword";
		String passwordHash = "jaklsdfjkalsdfjö=.";
		
		EasyMock.expect(mockDao.loadByUsernameAndPassword(EasyMock.eq(userName), EasyMock.eq(passwordHash))).andReturn(results);
		
		EasyMock.replay(mockDao);
		assertTrue(service.login(userName,password));
		EasyMock.verify(mockDao);
		
	}
}
