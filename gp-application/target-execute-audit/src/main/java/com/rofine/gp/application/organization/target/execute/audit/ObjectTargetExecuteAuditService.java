package com.rofine.gp.application.organization.target.execute.audit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteConstant;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;
import com.rofine.gp.domain.organization.target.execute.repo.ObjectTargetExecuteRepo;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.DateUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class ObjectTargetExecuteAuditService {

	@Autowired
	private ObjectTargetExecuteAuditLoader auditLoader;

	@Autowired
	private ObjectTargetExecuteAuditRepo auditRepo;

	@Autowired
	private ObjectTargetExecuteRepo executeRepo;

	public List<ObjectTargetExecute> getAuditFillingExecutes(String schemeId, User user) {
		return auditLoader.findBySchemeIdAndObjectCodeAndObjectTypeAndState(schemeId, user.getDeptId(), "dept",
				ObjectTargetExecuteAudit.State_Fill_Auditing);
	}

	public void auditFill(List<AuditFillVO> auditFills, User auditFillUser) {

		// 生成审批记录
		Date sysDate = DateUtil.getSysDate();

		List<ObjectTargetExecuteAudit> audits = new ArrayList<ObjectTargetExecuteAudit>();
		ObjectTargetExecuteAudit audit;

		List<String> nextExecuteIds = new ArrayList<String>();
		List<String> backExecuteIds = new ArrayList<String>();

		for (AuditFillVO auditFill : auditFills) {

			audit = new ObjectTargetExecuteAudit();
			audit.setAuditDate(sysDate);
			audit.setAuditId(auditFillUser.getId());
			audit.setAuditName(auditFillUser.getName());
			audit.setExecuteId(auditFill.getExecuteId());
			audit.setResult(auditFill.isResult());
			audit.setSuggestion(auditFill.getSuggestion());

			audits.add(audit);

			if (auditFill.isResult()) {
				nextExecuteIds.add(auditFill.getExecuteId());
			} else {
				backExecuteIds.add(auditFill.getExecuteId());
			}
		}

		auditRepo.save(audits);

		// 修改执行状态
		if (nextExecuteIds.size() > 0) {
			List<ObjectTargetExecute> executes = executeRepo.findByIdIn(nextExecuteIds);
			for (ObjectTargetExecute execute : executes) {
				execute.setState(ObjectTargetExecuteConstant.State_Evaluating);
			}
			executeRepo.save(executes);
		}

		if (backExecuteIds.size() > 0) {
			List<ObjectTargetExecute> executes = executeRepo.findByIdIn(backExecuteIds);
			for (ObjectTargetExecute execute : executes) {
				execute.setState(ObjectTargetExecuteConstant.State_Filling);
			}
			executeRepo.save(executes);
		}

	}

	public void clear() {
		auditRepo.deleteAll();
	}

}
