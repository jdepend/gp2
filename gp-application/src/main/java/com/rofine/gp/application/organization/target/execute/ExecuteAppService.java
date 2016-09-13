//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-application\\src\\main\\java\\com\\rofine\\gp\\application\\DesignModel\\DesignElement\\application\\organization\\target\\execute\\ExecuteAppService.java

package com.rofine.gp.application.organization.target.execute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteDomainService;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.domain.organization.target.scheme.SchemeDomainService;
import com.rofine.gp.domain.organization.target.scheme.model.Scheme;
import com.rofine.gp.domain.organization.target.scheme.model.Target;
import com.rofine.gp.platform.user.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExecuteAppService {

	@Autowired
	private ObjectTargetExecuteDomainService objectTargetExecuteDomainService;

	@Autowired
	private SchemeDomainService schemeDomainService;

	@Autowired
	private NotificationService notificationService;

	public List<ObjectTargetExecuteVO> getFillingExecutes(String schemeId, User user) throws TargetException {
		return objectTargetExecuteDomainService.getFillingExecutes(schemeId, user);
	}

	public List<ObjectTargetExecuteVO> getEvaluatingExecutes(String schemeId, User user) throws TargetException {
		return objectTargetExecuteDomainService.getEvaluatingExecutes(schemeId, user);
	}

	public void fill(String schemeId, List<FillVO> fills, User user) throws TargetException {
		objectTargetExecuteDomainService.fill(schemeId, fills, user);
	}

	/**
	 * @param evaluateVOs
	 * @param userId
	 * @throws TargetException
	 * @roseuid 573A8A9F01C3
	 */
	public void evaluate(String schemeId, List<EvaluateVO> evaluates, User user) throws TargetException {
		objectTargetExecuteDomainService.evaluate(schemeId, evaluates, user);
	}

	public List<ObjectTargetExecuteVO> getOperatedExecutes(String schemeId, User user) throws TargetException {
		return objectTargetExecuteDomainService.getOperatedExecutes(schemeId, user);
	}

	/**
	 * @param schemeId
	 * @roseuid 573B1224022D
	 */
	public List<Target> monitor(String schemeId) {
		List<TargetStatVO> targetStatVOs = objectTargetExecuteDomainService.getTargetStats(schemeId);
		Map<String, TargetStatVO> targetStatMap = new HashMap<String, TargetStatVO>();
		targetStatVOs.forEach(targetStatVO -> {
			targetStatMap.put(targetStatVO.getTargetId(), targetStatVO);
		});

		Scheme scheme = schemeDomainService.getScheme(schemeId);
		scheme.getTargets().forEach(target -> {
			target.setTargetStatVO(targetStatMap.get(target.getId()));
		});

		return scheme.getTargets();
	}

	public void deleteExecutes(List<String> executeIds) {
		objectTargetExecuteDomainService.deleteExecutes(executeIds);
	}

	@Scheduled(cron = "0 0 01 * * ?")
	// 每天早1点执行一次
	public void remind() {

		List<ObjectTargetExecuteVO> executes = objectTargetExecuteDomainService.getRemindExecutes();

		String receiver;
		for (ObjectTargetExecuteVO execute : executes) {
			receiver = null;
			if (execute.getState().equals(ObjectTargetExecuteConstant.State_Filling)) {
				receiver = execute.getPlanFillId();
			} else if (execute.getState().equals(ObjectTargetExecuteConstant.State_Evaluating)) {
				receiver = execute.getPlanEvaluateId();
			}
			if (receiver != null) {
				List<String> receivers = new ArrayList<String>();
				receivers.add(receiver);
				NotificationVO notificationVO = new NotificationVO();
				notificationVO.setSourceId("system");
				notificationVO.setReceivers(receivers);
				notificationVO.setMessage("考核方案[" + execute.getSchemeId() + "]需要您及时参与填报和打分");
			}
		}
	}
}
