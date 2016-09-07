package com.rofine.gp.domain.organization.target.execute.load.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.target.execute.load.ObjectTargetExecuteDataLoader;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.DateUtil;

@Service
public class UserObjectTargetExecuteDataLoader implements ObjectTargetExecuteDataLoader {

	@Autowired
	private UserObjectTargetExecuteRepo repo;

	@Override
	public List<ObjectTargetExecute> getFillingExecutes(String schemeId, User user) {
		Date sysDate = DateUtil.getSysDate();
		return repo.findBySchemeIdAndPlanFillIdAndStateAndStartDateLessThanAndEndDateGreaterThan(schemeId,
				user.getId(), ObjectTargetExecute.State_Filling, sysDate, sysDate);
	}

	@Override
	public List<ObjectTargetExecute> getEvaluatingExecutes(String schemeId, User user) {
		Date sysDate = DateUtil.getSysDate();
		return repo.findBySchemeIdAndPlanEvaluateIdAndStateAndStartDateLessThanAndEndDateGreaterThan(schemeId,
				user.getId(), ObjectTargetExecute.State_Evaluating, sysDate, sysDate);

	}

}
