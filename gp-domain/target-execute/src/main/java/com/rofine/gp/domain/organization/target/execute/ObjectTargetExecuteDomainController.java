package com.rofine.gp.domain.organization.target.execute;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.EvaluateVO;
import com.rofine.gp.domain.organization.target.domain.FillVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteDomainService;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetVO;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.domain.organization.target.domain.UserImpl;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.JsonUtil;

@RestController
public class ObjectTargetExecuteDomainController {

	@Autowired
	private ObjectTargetExecuteDomainService executeDomainService;

	@RequestMapping(value = "/create/executes/{frequencyType}", method = RequestMethod.POST)
	public void create(@PathVariable("frequencyType") String frequencyType, @RequestBody ObjectTargetVO objectTarget)
			throws TargetException {
		executeDomainService.createExecutes(frequencyType, objectTarget);
	}

	@RequestMapping(value = "/start/executes/scheme/{schemeId}", method = RequestMethod.POST)
	public void start(@PathVariable("schemeId") String schemeId) throws TargetException {
		executeDomainService.startExecutes(schemeId);
	}

	@RequestMapping(value = "/scheme/{schemeId}/fill", method = RequestMethod.GET)
	public List<ObjectTargetExecuteVO> fill(@PathVariable("schemeId") String schemeId,
			@RequestParam(required = false, value = "user") String userInfo) throws TargetException {
		User user = JsonUtil.toObject(userInfo, UserImpl.class);
		return executeDomainService.getFillingExecutes(schemeId, user);
	}

	@RequestMapping(value = "/scheme/{schemeId}/fill", method = RequestMethod.POST)
	public void fill(@PathVariable("schemeId") String schemeId, @RequestBody List<FillVO> fills,
			@RequestParam(required = false, value = "user") String userInfo) throws TargetException {
		User user = JsonUtil.toObject(userInfo, UserImpl.class);
		executeDomainService.fill(schemeId, fills, user);
	}

	@RequestMapping(value = "/scheme/{schemeId}/evaluate", method = RequestMethod.GET)
	public List<ObjectTargetExecuteVO> evaluate(@PathVariable("schemeId") String schemeId,
			@RequestParam(required = false, value = "user") String userInfo) throws TargetException {
		User user = JsonUtil.toObject(userInfo, UserImpl.class);
		return executeDomainService.getEvaluatingExecutes(schemeId, user);
	}

	@RequestMapping(value = "/scheme/{schemeId}/evaluate", method = RequestMethod.POST)
	public void evaluate(@PathVariable("schemeId") String schemeId, @RequestBody List<EvaluateVO> evaluates,
			@RequestParam(required = false, value = "user") String userInfo) throws TargetException {
		User user = JsonUtil.toObject(userInfo, UserImpl.class);
		executeDomainService.evaluate(schemeId, evaluates, user);
	}

	@RequestMapping(value = "/object/target/{objectTargetId}/executes", method = RequestMethod.GET)
	public List<ObjectTargetExecuteVO> getExecutesByObjectTarget(@PathVariable("objectTargetId") String objectTargetId) {
		return executeDomainService.getExecutesByObjectTarget(objectTargetId);
	}

	@RequestMapping(value = "/executes/{ids}/ids", method = RequestMethod.GET)
	public List<ObjectTargetExecuteVO> getExecutesByIds(@PathVariable("ids") String idInfo) throws TargetException {
		List<String> ids = JsonUtil.toArray(idInfo, String.class);
		return executeDomainService.getExecutesByIds(ids);
	}

	@RequestMapping(value = "/scheme/{schemeId}/monitor", method = RequestMethod.GET)
	public List<TargetStatVO> getTargetStats(@PathVariable("schemeId") String schemeId) {
		return executeDomainService.getTargetStats(schemeId);
	}

	@RequestMapping(value = "/close/executes/scheme/{schemeId}", method = RequestMethod.POST)
	public void close(@PathVariable("schemeId") String schemeId) throws TargetException {
		executeDomainService.closeExecutes(schemeId);
	}
}
