package com.rofine.gp.domain.organization.target.execute;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.EvaluateVO;
import com.rofine.gp.domain.organization.target.domain.FillVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.DateUtil;

@Service
public class ObjectTargetExecuteDomainStub {
	
	public List<ObjectTargetExecuteVO> getFillingExecutes(String schemeId, User user) {
		return null;
	}

	public List<ObjectTargetExecuteVO> getEvaluatingExecutes(String schemeId, User user) {
		return null;
	}

	public List<ObjectTargetExecuteVO> getOperatedExecutes(String schemeId, User user) {
		return null;
	}
	
	public List<ObjectTargetExecuteVO> getRemindExecutes(){
		Date sysDate = DateUtil.getSysDate();
		return null;
	}

	public void fill(List<FillVO> fills, User user) throws TargetException {
		
	}
	
	/**
	 * @param evaluates
	 * @param userId
	 * @throws TargetException
	 * @roseuid 573A8DF9021C
	 */
	public void evaluate(List<EvaluateVO> evaluates, User user) throws TargetException {
		
	}
	
	public void deleteExecutes(List<String> executeIds) {
		
	}

	/**
	 * @param schemeId
	 * @roseuid 573BC90901EE
	 */
	public List<TargetStatVO> getTargetStats(String schemeId) {

		return null;
	}

}
