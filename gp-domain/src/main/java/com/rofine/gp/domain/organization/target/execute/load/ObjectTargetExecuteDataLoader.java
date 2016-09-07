package com.rofine.gp.domain.organization.target.execute.load;

import java.util.List;

import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;
import com.rofine.gp.platform.user.User;

public interface ObjectTargetExecuteDataLoader {
	
	public List<ObjectTargetExecute> getFillingExecutes(String schemeId, User user);

	public List<ObjectTargetExecute> getEvaluatingExecutes(String schemeId, User user);

}
