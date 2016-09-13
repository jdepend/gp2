package com.rofine.gp.domain.organization.target.execute;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.EvaluateVO;
import com.rofine.gp.domain.organization.target.domain.FillVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteDomainService;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetVO;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.platform.bean.ApplicationContextUtil;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.DateUtil;
import com.rofine.gp.platform.util.JsonUtil;

@Service
public class ObjectTargetExecuteDomainStub implements ObjectTargetExecuteDomainService{

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(ObjectTargetExecuteDomainStub.class.getName());

	public static ObjectTargetExecuteDomainService getBean() {
		return (ObjectTargetExecuteDomainService) ApplicationContextUtil.getApplicationContext().getBean(
				"objectTargetExecuteDomainStub");
	}

	public ObjectTargetExecuteDomainStub(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}

	public void createExecutes(String frequencyType, ObjectTargetVO objectTarget) throws TargetException{
		restTemplate.postForObject(this.serviceUrl + "/create/executes/{frequencyType}", objectTarget,
				ObjectTargetVO.class, frequencyType);
	}

	public void startExecutes(String schemeId) {
		restTemplate.postForObject(this.serviceUrl + "/start/executes/scheme/{schemeId}", null, String.class, schemeId);

	}

	public List<ObjectTargetExecuteVO> getFillingExecutes(String schemeId, User user) throws TargetException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("schemeId", schemeId);
			params.put("user", JsonUtil.toJson(user));

			return restTemplate.getForObject(this.serviceUrl + "/scheme/{schemeId}/fill?user={user}", List.class,
					params);
		} catch (Exception e) {
			throw new TargetException(e);
		}

	}

	public List<ObjectTargetExecuteVO> getEvaluatingExecutes(String schemeId, User user) throws TargetException {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("schemeId", schemeId);
			params.put("user", JsonUtil.toJson(user));

			return restTemplate.getForObject(this.serviceUrl + "/scheme/{schemeId}/evaluate?user={user}", List.class,
					params);
		} catch (Exception e) {
			throw new TargetException(e);
		}
	}

	public List<ObjectTargetExecuteVO> getOperatedExecutes(String schemeId, User user) {
		return null;
	}

	public List<ObjectTargetExecuteVO> getRemindExecutes() {
		Date sysDate = DateUtil.getSysDate();
		return null;
	}

	public void fill(String schemeId, List<FillVO> fills, User user) throws TargetException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("schemeId", schemeId);
			params.put("user", JsonUtil.toJson(user));

			restTemplate.postForObject(this.serviceUrl + "/scheme/{schemeId}/fill?user={user}", fills, List.class,
					params);
		} catch (Exception e) {
			throw new TargetException(e);
		}

	}

	/**
	 * @param evaluates
	 * @param userId
	 * @throws TargetException
	 * @roseuid 573A8DF9021C
	 */
	public void evaluate(String schemeId, List<EvaluateVO> evaluates, User user) throws TargetException {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("schemeId", schemeId);
			params.put("user", JsonUtil.toJson(user));

			restTemplate.postForObject(this.serviceUrl + "/scheme/{schemeId}/evaluate?user={user}", evaluates,
					List.class, params);
		} catch (Exception e) {
			throw new TargetException(e);
		}

	}

	public void deleteExecutes(List<String> executeIds) {

	}

	/**
	 * @param schemeId
	 * @roseuid 573BC90901EE
	 */
	public List<TargetStatVO> getTargetStats(String schemeId) {

		return null;
	}
}
