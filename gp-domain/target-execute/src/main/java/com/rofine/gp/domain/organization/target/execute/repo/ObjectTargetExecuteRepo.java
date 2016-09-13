//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\execute\\ObjectTargetExecuteRepo.java

package com.rofine.gp.domain.organization.target.execute.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;

public interface ObjectTargetExecuteRepo extends CrudRepository<ObjectTargetExecute, String> {

	@Query("select e from ObjectTargetExecute e where e.schemeId = :schemeId and (e.fillId = :operator or e.evaluateId = :operator)")
	public List<ObjectTargetExecute> findOperateExecutes(@Param("schemeId") String schemeId,
			@Param("operator") String operator);

	@Query("select e from ObjectTargetExecute e where e.remindDate >= :sysDate and (e.state = 'filling' or e.state = 'evaluating')")
	public List<ObjectTargetExecute> findRemindExecutes(@Param("sysDate") Date sysDate);

	public List<ObjectTargetExecute> findByIdIn(List<String> ids);

	public List<ObjectTargetExecute> findBySchemeId(String schemeId);

	public List<ObjectTargetExecute> findByObjectTargetId(String objectTargetId);

}
