/**
 * 
 */
package com.xiudun.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Administrator
 *
 */
public class LoginServiceImpl implements LoginService {

	private Properties pro = new Properties();
	@Override
	public boolean login(String username, String password) {
		File file = new File("c:/xiu/user/");
		File[] files = file.listFiles();
		for(File f:files) {
			try(InputStream in = new FileInputStream(f)){
				pro.load(in);
				if(username.equals(pro.getProperty("username")) &&
						password.equals(pro.getProperty("password"))) {
					return true;
				}
			}catch(Exception e) {e.printStackTrace();}
		}		
		return false;
	}

}






























