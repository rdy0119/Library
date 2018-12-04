/**
 * 
 */
package com.xiudun.action;

import com.xiudun.service.LoginService;
import com.xiudun.service.LoginServiceImpl;
import com.xiudun.service.LoginService;

/**
 * @author Administrator
 *
 */
public class LoginAction {

	private LoginService service = new LoginServiceImpl();
	
	public boolean login(String username,String password) {
		return service.login(username, password);
	}
}
