package com.rofine.gp.application.organization.target.execute.audit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.UserImpl;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.JsonUtil;

@RestController
public class ObjectTargetExecuteAuditController {

	@Autowired
	private ObjectTargetExecuteAuditService objectTargetExecuteAuditService;

	@RequestMapping(value = "/scheme/{schemeId}/audit", method = RequestMethod.GET)
	public List<ObjectTargetExecuteVO> fill(@PathVariable("schemeId") String schemeId,
			@RequestParam(required = false, value = "user") String userInfo) throws TargetException {
		User user = JsonUtil.toObject(userInfo, UserImpl.class);
		return objectTargetExecuteAuditService.getAuditFillingExecutes(schemeId, user);
	}

	@RequestMapping(value = "/scheme/{schemeId}/audit", method = RequestMethod.POST)
	public void fill(@PathVariable("schemeId") String schemeId, @RequestBody List<AuditFillVO> auditFills,
			@RequestParam(required = false, value = "user") String userInfo) throws TargetException {
		User user = JsonUtil.toObject(userInfo, UserImpl.class);
		objectTargetExecuteAuditService.auditFill(schemeId, auditFills, user);
	}

}
