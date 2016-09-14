package com.rofine.gp.domain.organization.target.service;

import java.util.List;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.EvaluateVO;
import com.rofine.gp.domain.organization.target.domain.FillVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetVO;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.platform.user.User;

public interface ObjectTargetExecuteDomainService {

	public abstract void createExecutes(String frequencyType, ObjectTargetVO objectTarget) throws TargetException;

	public abstract void startExecutes(String schemeId);

	public abstract List<ObjectTargetExecuteVO> getFillingExecutes(String schemeId, User user) throws TargetException;

	public abstract List<ObjectTargetExecuteVO> getEvaluatingExecutes(String schemeId, User user)
			throws TargetException;

	public abstract List<ObjectTargetExecuteVO> getOperatedExecutes(String schemeId, User user) throws TargetException;

	public abstract List<ObjectTargetExecuteVO> getRemindExecutes();

	public abstract void fill(String schemeId, List<FillVO> fills, User user) throws TargetException;

	/**
	 * @param evaluates
	 * @param userId
	 * @throws TargetException
	 * @roseuid 573A8DF9021C
	 */
	public abstract void evaluate(String schemeId, List<EvaluateVO> evaluates, User user) throws TargetException;

	public abstract void deleteExecutes(List<String> executeIds);

	/**
	 * @param schemeId
	 * @roseuid 573BC90901EE
	 */
	public abstract List<TargetStatVO> getTargetStats(String schemeId);

	public abstract List<ObjectTargetExecuteVO> getExecutesByObjectTarget(String objectTargetId);
	
	public abstract List<ObjectTargetExecuteVO> getExecutesByIds(List<String> ids) throws TargetException;

	public abstract void closeExecutes(String schemeId);

}