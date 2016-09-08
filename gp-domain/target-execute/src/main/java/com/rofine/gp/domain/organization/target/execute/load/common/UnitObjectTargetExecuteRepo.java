//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\execute\\ObjectTargetExecuteRepo.java

package com.rofine.gp.domain.organization.target.execute.load.common;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;

public interface UnitObjectTargetExecuteRepo extends CrudRepository<ObjectTargetExecute, String> {

	public List<ObjectTargetExecute> findBySchemeIdAndObjectCodeAndObjectTypeAndStateAndStartDateLessThanAndEndDateGreaterThan(
			String schemeId, String objectCode, String objectType, String state, Date startDate, Date endDate);

	public List<ObjectTargetExecute> findBySchemeIdAndSubjectIdAndStateAndStartDateLessThanAndEndDateGreaterThan(
			String schemeId, String subjectId, String state, Date startDate, Date endDate);

}
