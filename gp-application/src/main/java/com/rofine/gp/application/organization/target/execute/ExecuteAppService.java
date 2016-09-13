//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-application\\src\\main\\java\\com\\rofine\\gp\\application\\DesignModel\\DesignElement\\application\\organization\\target\\execute\\ExecuteAppService.java

package com.rofine.gp.application.organization.target.execute;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rofine.gp.application.notification.NotificationService;
import com.rofine.gp.application.notification.NotificationVO;
import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.EvaluateVO;
import com.rofine.gp.domain.organization.target.domain.FillVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteConstant;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.domain.organization.target.execute.ObjectTargetExecuteDomainServiceStub;
import com.rofine.gp.platform.user.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExecuteAppService {
	@Autowired
	private ObjectTargetExecuteDomainServiceStub executeDomainStub;

	@Autowired
	private NotificationService notificationService;

	public List<ObjectTargetExecuteVO> getFillingExecutes(String schemeId,
			User user) throws TargetException {
		return executeDomainStub.getFillingExecutes(schemeId, user);
	}

	public List<ObjectTargetExecuteVO> getEvaluatingExecutes(String schemeId,
			User user) throws TargetException {
		return executeDomainStub.getEvaluatingExecutes(schemeId, user);
	}

	public void fill(String schemeId, List<FillVO> fills, User user)
			throws TargetException {
		executeDomainStub.fill(schemeId, fills, user);
	}

	/**
	 * @param evaluateVOs
	 * @param userId
	 * @throws TargetException
	 * @roseuid 573A8A9F01C3
	 */
	public void evaluate(String schemeId, List<EvaluateVO> evaluates, User user)
			throws TargetException {
		executeDomainStub.evaluate(schemeId, evaluates, user);
	}

	public List<ObjectTargetExecuteVO> getOperatedExecutes(String schemeId,
			User user) {
		return executeDomainStub.getOperatedExecutes(schemeId, user);
	}

	/**
	 * @param schemeId
	 * @roseuid 573B1224022D
	 */
	public List<TargetStatVO> monitor(String schemeId) {
		return executeDomainStub.getTargetStats(schemeId);
	}

	public void deleteExecutes(List<String> executeIds) {
		executeDomainStub.deleteExecutes(executeIds);
	}

	@Scheduled(cron = "0 0 01 * * ?")
	// 每天早1点执行一次
	public void remind() {

		List<ObjectTargetExecuteVO> executes = executeDomainStub
				.getRemindExecutes();

		String receiver;
		for (ObjectTargetExecuteVO execute : executes) {
			receiver = null;
			if (execute.getState().equals(
					ObjectTargetExecuteConstant.State_Filling)) {
				receiver = execute.getPlanFillId();
			} else if (execute.getState().equals(
					ObjectTargetExecuteConstant.State_Evaluating)) {
				receiver = execute.getPlanEvaluateId();
			}
			if (receiver != null) {
				List<String> receivers = new ArrayList<String>();
				receivers.add(receiver);
				NotificationVO notificationVO = new NotificationVO();
				notificationVO.setSourceId("system");
				notificationVO.setReceivers(receivers);
				notificationVO.setMessage("考核方案[" + execute.getSchemeId()
						+ "]需要您及时参与填报和打分");
			}
		}
	}
}
