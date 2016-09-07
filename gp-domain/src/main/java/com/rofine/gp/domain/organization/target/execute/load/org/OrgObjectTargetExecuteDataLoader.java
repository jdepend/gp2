package com.rofine.gp.domain.organization.target.execute.load.org;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.target.execute.load.ObjectTargetExecuteDataLoader;
import com.rofine.gp.domain.organization.target.execute.load.common.UnitObjectTargetExecuteRepo;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.DateUtil;

@Service
public class OrgObjectTargetExecuteDataLoader implements ObjectTargetExecuteDataLoader {

	@Autowired
	private UnitObjectTargetExecuteRepo repo;

	@Override
	public List<ObjectTargetExecute> getFillingExecutes(String schemeId, User user) {
		Date sysDate = DateUtil.getSysDate();
		return repo.findBySchemeIdAndObjectCodeAndObjectTypeAndStateAndStartDateLessThanAndEndDateGreaterThan(schemeId,
				user.getOrgId(), "org", ObjectTargetExecute.State_Filling, sysDate, sysDate);
	}

	@Override
	public List<ObjectTargetExecute> getEvaluatingExecutes(String schemeId, User user) {
		Date sysDate = DateUtil.getSysDate();
		return repo.findBySchemeIdAndSubjectIdAndStateAndStartDateLessThanAndEndDateGreaterThan(schemeId,
				user.getDeptId(), ObjectTargetExecute.State_Evaluating, sysDate, sysDate);

	}

}
