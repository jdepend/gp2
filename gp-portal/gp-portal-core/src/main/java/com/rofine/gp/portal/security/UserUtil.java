package com.rofine.gp.portal.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.rofine.gp.platform.exception.GpException;
import com.rofine.gp.platform.user.User;

public class UserUtil {
	
	private static User user = null;

	public static User getUser() throws GpException {

		if(user == null){
			Subject subject = SecurityUtils.getSubject();
			User user = (User)subject.getPrincipal();
			if(user == null){
				throw new GpException("没有登录");
			}
			
			return user;
		}

		return user;
	}
	
	public static void setUser(User u){
		user = u;
	}

}
