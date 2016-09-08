package com.rofine.gp.domain.organization.target.execute.load.role;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteConstant;
import com.rofine.gp.domain.organization.target.execute.load.ObjectTargetExecuteDataLoader;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.DateUtil;

@Service
public class RoleObjectTargetExecuteDataLoader implements ObjectTargetExecuteDataLoader {

	@Autowired
	private RoleObjectTargetExecuteRepo repo;

	@Override
	public List<ObjectTargetExecute> getFillingExecutes(String schemeId, User user) {
		Date sysDate = DateUtil.getSysDate();

		return repo.findBySchemeIdAndPlanFillRoleIdInAndStateAndStartDateLessThanAndEndDateGreaterThan(schemeId,
				user.getRoleIds(), ObjectTargetExecuteConstant.State_Filling, sysDate, sysDate);
	}

	@Override
	public List<ObjectTargetExecute> getEvaluatingExecutes(String schemeId, User user) {
		Date sysDate = DateUtil.getSysDate();
		return repo.findBySchemeIdAndPlanFillRoleIdInAndStateAndStartDateLessThanAndEndDateGreaterThan(schemeId,
				user.getRoleIds(), ObjectTargetExecuteConstant.State_Evaluating, sysDate, sysDate);

	}

}
