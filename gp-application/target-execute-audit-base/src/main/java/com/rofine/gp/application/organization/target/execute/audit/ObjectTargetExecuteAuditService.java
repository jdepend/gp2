package com.rofine.gp.application.organization.target.execute.audit;

import java.util.List;

import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.platform.user.User;

public interface ObjectTargetExecuteAuditService {

	public abstract List<ObjectTargetExecuteVO> getAuditFillingExecutes(String schemeId, User user);

	public abstract void auditFill(String schemeId, List<AuditFillVO> auditFills, User auditFillUser);

	public abstract void clear();

}