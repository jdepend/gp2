package com.rofine.gp.domain.organization.target.execute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.rofine.gp.domain.organization.target.execute.service.ObjectTargetExecuteDomainService;

@RestController
public class ObjectTargetExecuteDomainController {
	
	@Autowired
	private ObjectTargetExecuteDomainService executeDomainService;
	
	

}
