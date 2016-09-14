package com.rofine.gp.application.organization.target.execute.audit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

import com.rofine.gp.application.organization.target.execute.audit.AuditFillVO;
import com.rofine.gp.application.organization.target.execute.audit.ObjectTargetExecuteAuditService;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.platform.user.User;

public class ObjectTargetExecuteAuditServiceStub implements ObjectTargetExecuteAuditService {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	private String serviceUrl;
	
	public ObjectTargetExecuteAuditServiceStub(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}
	
	@Override
	public List<ObjectTargetExecuteVO> getAuditFillingExecutes(String schemeId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void auditFill(List<AuditFillVO> auditFills, User auditFillUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
