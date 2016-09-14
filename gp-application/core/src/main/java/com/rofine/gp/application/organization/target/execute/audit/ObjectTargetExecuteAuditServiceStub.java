package com.rofine.gp.application.organization.target.execute.audit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.JsonUtil;

public class ObjectTargetExecuteAuditServiceStub implements ObjectTargetExecuteAuditService {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	private String serviceUrl;

	private final static String urlPrefix = "/rest";

	public ObjectTargetExecuteAuditServiceStub(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
		this.serviceUrl += urlPrefix;
	}

	@Override
	public List<ObjectTargetExecuteVO> getAuditFillingExecutes(String schemeId, User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schemeId", schemeId);
		params.put("user", JsonUtil.toJson(user));

		return Arrays.asList(restTemplate.getForObject(this.serviceUrl + "/scheme/{schemeId}/audit?user={user}",
				ObjectTargetExecuteVO[].class, params));
	}

	@Override
	public void auditFill(String schemeId, List<AuditFillVO> auditFills, User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schemeId", schemeId);
		params.put("user", JsonUtil.toJson(user));

		restTemplate.postForObject(this.serviceUrl + "/scheme/{schemeId}/audit?user={user}", auditFills, List.class,
				params);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
