//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\execute\\ObjectTargetExecuteDomainService.java

package com.rofine.gp.domain.organization.target.execute.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.EvaluateVO;
import com.rofine.gp.domain.organization.target.domain.FillVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteConstant;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteDomainService;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetVO;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.domain.organization.target.execute.event.ObjectTargetExecuteEvalutedEvent;
import com.rofine.gp.domain.organization.target.execute.event.ObjectTargetExecuteFilledEvent;
import com.rofine.gp.domain.organization.target.execute.load.ObjectTargetExecuteDataLoader;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;
import com.rofine.gp.domain.organization.target.execute.repo.ObjectTargetExecuteRepo;
import com.rofine.gp.domain.organization.target.target.frequency.TargetFrequency;
import com.rofine.gp.domain.organization.target.target.frequency.TargetFrequencyType;
import com.rofine.gp.domain.organization.target.target.frequency.TargetFrequencyTypeFactory;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.DateUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class ObjectTargetExecuteDomainServiceImpl implements ObjectTargetExecuteDomainService {
	@Autowired
	private ObjectTargetExecuteRepo executeRepo;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@Qualifier(value = "objectTargetExecuteDataLoader")
	private ObjectTargetExecuteDataLoader loader;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#createExecutes(java.lang.String,
	 * com.rofine.gp.domain.organization.target.domain.ObjectTargetVO)
	 */
	@Override
	public void createExecutes(String frequencyType, ObjectTargetVO objectTarget) throws TargetException {

		List<ObjectTargetExecute> objectTargetExecutes = new ArrayList<ObjectTargetExecute>();

		TargetFrequencyType targetFrequencyType = TargetFrequencyTypeFactory.create(frequencyType);

		List<TargetFrequency> targetFrequencys = targetFrequencyType.createTargetFrequencys();
		for (TargetFrequency targetFrequency : targetFrequencys) {
			objectTargetExecutes.add(new ObjectTargetExecute(targetFrequency, objectTarget));
		}

		executeRepo.save(objectTargetExecutes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#startExecutes(java.lang.String)
	 */
	@Override
	public void startExecutes(String schemeId) {
		List<ObjectTargetExecute> objectTargetExecutes = executeRepo.findBySchemeId(schemeId);

		for (ObjectTargetExecute execute : objectTargetExecutes) {
			execute.start();
		}

		executeRepo.save(objectTargetExecutes);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#getFillingExecutes(java.lang.String,
	 * com.rofine.gp.platform.user.User)
	 */
	@Override
	public List<ObjectTargetExecuteVO> getFillingExecutes(String schemeId, User user) throws TargetException {
		List<ObjectTargetExecute> exexutes = loader.getFillingExecutes(schemeId, user);

		List<ObjectTargetExecuteVO> executeVOs = new ArrayList<ObjectTargetExecuteVO>(exexutes.size());
		for (ObjectTargetExecute execute : exexutes) {
			executeVOs.add(execute.toVO());
		}

		return executeVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#getEvaluatingExecutes(java.lang.String,
	 * com.rofine.gp.platform.user.User)
	 */
	@Override
	public List<ObjectTargetExecuteVO> getEvaluatingExecutes(String schemeId, User user) throws TargetException {
		List<ObjectTargetExecute> exexutes = loader.getEvaluatingExecutes(schemeId, user);

		List<ObjectTargetExecuteVO> executeVOs = new ArrayList<ObjectTargetExecuteVO>(exexutes.size());
		for (ObjectTargetExecute execute : exexutes) {
			executeVOs.add(execute.toVO());
		}

		return executeVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#getOperatedExecutes(java.lang.String,
	 * com.rofine.gp.platform.user.User)
	 */
	@Override
	public List<ObjectTargetExecuteVO> getOperatedExecutes(String schemeId, User user) throws TargetException {
		List<ObjectTargetExecute> exexutes = executeRepo.findOperateExecutes(schemeId, user.getId());

		List<ObjectTargetExecuteVO> executeVOs = new ArrayList<ObjectTargetExecuteVO>(exexutes.size());
		for (ObjectTargetExecute execute : exexutes) {
			executeVOs.add(execute.toVO());
		}

		return executeVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#getRemindExecutes()
	 */
	@Override
	public List<ObjectTargetExecuteVO> getRemindExecutes() {
		Date sysDate = DateUtil.getSysDate();
		List<ObjectTargetExecute> exexutes = executeRepo.findRemindExecutes(sysDate);

		List<ObjectTargetExecuteVO> executeVOs = new ArrayList<ObjectTargetExecuteVO>(exexutes.size());
		for (ObjectTargetExecute execute : exexutes) {
			executeVOs.add(execute.toVO());
		}

		return executeVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#fill(java.util.List,
	 * com.rofine.gp.platform.user.User)
	 */
	@Override
	public void fill(String schemeId, List<FillVO> fills, User user) throws TargetException {
		Map<String, Float> fillMap = new HashMap<String, Float>();
		for (FillVO fill : fills) {
			fillMap.put(fill.getExecuteId(), fill.getScore());
		}

		List<ObjectTargetExecute> executes = executeRepo.findByIdIn(new ArrayList<String>(fillMap.keySet()));
		for (ObjectTargetExecute execute : executes) {
			if (!execute.getState().equals(ObjectTargetExecuteConstant.State_Filling)) {
				throw new TargetException("执行记录[" + execute.getEvaluateId() + "]的状态为[" + execute.getState() + "]，不能填报");
			}
			execute.setFillScore(fillMap.get(execute.getId()));
			execute.setFillId(user.getId());
			execute.setFillDate(DateUtil.getSysDate());
			execute.setState(ObjectTargetExecuteConstant.State_Evaluating);
		}

		executeRepo.save(executes);

		applicationContext.publishEvent(new ObjectTargetExecuteFilledEvent(executes));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#evaluate(java.util.List,
	 * com.rofine.gp.platform.user.User)
	 */
	@Override
	public void evaluate(String schemeId, List<EvaluateVO> evaluates, User user) throws TargetException {
		Map<String, Float> evaluateMap = new HashMap<String, Float>();
		for (EvaluateVO evaluate : evaluates) {
			evaluateMap.put(evaluate.getExecuteId(), evaluate.getScore());
		}

		List<ObjectTargetExecute> executes = executeRepo.findByIdIn(new ArrayList<String>(evaluateMap.keySet()));
		for (ObjectTargetExecute execute : executes) {
			if (!execute.getState().equals(ObjectTargetExecuteConstant.State_Evaluating)) {
				throw new TargetException("执行记录[" + execute.getEvaluateId() + "]的状态为[" + execute.getState() + "]，不能打分");
			}
			execute.setEvaluateScore(evaluateMap.get(execute.getId()));
			execute.setEvaluateId(user.getId());
			execute.setEvaluateDate(DateUtil.getSysDate());
			execute.setState(ObjectTargetExecuteConstant.State_Evaluated);

			// schemeDomainService.syncScore(execute);
		}

		executeRepo.save(executes);

		applicationContext.publishEvent(new ObjectTargetExecuteEvalutedEvent(executes));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#deleteExecutes(java.util.List)
	 */
	@Override
	public void deleteExecutes(List<String> executeIds) {
		for (String executeId : executeIds) {
			executeRepo.delete(executeId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rofine.gp.domain.organization.target.execute.service.
	 * ObjectTargetExecuteDomainService#getTargetStats(java.lang.String)
	 */
	@Override
	public List<TargetStatVO> getTargetStats(String schemeId) {
		return new ArrayList<TargetStatVO>(this.getTargetStatVOs(schemeId).values());
	}

	private Map<String, TargetStatVO> getTargetStatVOs(String schemeId) {
		List<ObjectTargetExecute> executes = executeRepo.findBySchemeId(schemeId);
		Map<String, TargetStatVO> targetStatVOMap = new HashMap<String, TargetStatVO>();
		TargetStatVO targetStatVO;
		Date sysDate = DateUtil.getSysDate();
		for (ObjectTargetExecute execute : executes) {
			targetStatVO = targetStatVOMap.get(execute.getTargetId());
			if (targetStatVO == null) {
				targetStatVO = new TargetStatVO();
				targetStatVO.setTargetId(execute.getTargetId());
				targetStatVO.setFrequencyCode(execute.getFrequencyCode());
				targetStatVOMap.put(execute.getTargetId(), targetStatVO);
			}
			if (execute.getState().equals(ObjectTargetExecuteConstant.State_Evaluated)) {
				targetStatVO.setEvaluatedCount(targetStatVO.getEvaluatedCount() + 1);
			} else if (execute.getState().equals(ObjectTargetExecuteConstant.State_Evaluating)) {
				if (execute.getRemindDate().after(sysDate)) {

				} else if (execute.getRemindDate().before(sysDate) && execute.getStartDate().after(sysDate)) {
					targetStatVO.setWaitEvaluateCount(targetStatVO.getWaitEvaluateCount() + 1);
				} else if (execute.getStartDate().before(sysDate) && execute.getEndDate().after(sysDate)) {
					targetStatVO.setEvaluatingCount(targetStatVO.getEvaluatingCount() + 1);
				} else if (execute.getEndDate().before(sysDate) && execute.getLimitDate().after(sysDate)) {
					targetStatVO.setOverdueEvaluateCount(targetStatVO.getOverdueEvaluateCount() + 1);
				} else if (execute.getLimitDate().before(sysDate)) {
					targetStatVO.setOverdueEvaluateCount(targetStatVO.getOverdueEvaluateCount() + 1);
				}
			}
		}

		return targetStatVOMap;
	}

}
