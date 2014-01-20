package com.sbgl.app.services.login;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.entity.Loginuser;


public interface LoginService {
	public Loginuser findUser(Loginuser loginuser);
}
