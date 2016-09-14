package com.rofine.gp.application.organization.target.execute.audit;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;

public interface ObjectTargetExecuteAuditLoader extends CrudRepository<ObjectTargetExecute, String> {

	public List<ObjectTargetExecute> findBySchemeIdAndObjectCodeAndObjectTypeAndState(String schemeId,
			String objectCode, String objectType, String state);

}
