package com.rofine.gp.domain.organization.target.execute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetVO;
import com.rofine.gp.domain.organization.target.execute.service.ObjectTargetExecuteDomainService;

@RestController
public class ObjectTargetExecuteDomainController {

	@Autowired
	private ObjectTargetExecuteDomainService executeDomainService;

	@RequestMapping(value = "/create/executes/{frequencyType}", method = RequestMethod.POST)
	public void create(@PathVariable("frequencyType") String frequencyType,
			@RequestBody ObjectTargetVO objectTarget) throws TargetException {
		executeDomainService.createExecutes(frequencyType, objectTarget);
	}

	@RequestMapping(value = "/start/executes/scheme/{schemeId}", method = RequestMethod.POST)
	public void start(@PathVariable("schemeId") String schemeId) throws TargetException {
		executeDomainService.startExecutes(schemeId);
	}
}
