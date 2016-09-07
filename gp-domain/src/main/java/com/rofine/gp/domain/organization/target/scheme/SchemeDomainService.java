//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\SchemeDomainService.java

package com.rofine.gp.domain.organization.target.scheme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;
import com.rofine.gp.domain.organization.target.scheme.event.SchemeCloseBeforeEvent;
import com.rofine.gp.domain.organization.target.scheme.event.SchemeClosedEvent;
import com.rofine.gp.domain.organization.target.scheme.event.SchemeCreatedEvent;
import com.rofine.gp.domain.organization.target.scheme.event.SchemeStartBeforeEvent;
import com.rofine.gp.domain.organization.target.scheme.event.SchemeStartedEvent;
import com.rofine.gp.domain.organization.target.scheme.event.Target2ObjectCreatedEvent;
import com.rofine.gp.domain.organization.target.scheme.event.TargetUpdateBeforeEvent;
import com.rofine.gp.domain.organization.target.scheme.event.TargetUpdatedEvent;
import com.rofine.gp.domain.organization.target.scheme.model.Scheme;
import com.rofine.gp.domain.organization.target.scheme.model.SchemeObject;
import com.rofine.gp.domain.organization.target.scheme.model.Target;
import com.rofine.gp.domain.organization.target.scheme.model.TargetType;
import com.rofine.gp.domain.organization.target.scheme.repo.SchemeRepo;
import com.rofine.gp.domain.organization.target.scheme.repo.TargetRepo;
import com.rofine.gp.domain.organization.target.scheme.repo.TargetTypeRepo;
import com.rofine.gp.domain.organization.target.target.score.ObjectScoreCalculator;
import com.rofine.gp.domain.organization.target.target.score.ObjectTargetScoreCalculator;
import com.rofine.gp.platform.exception.GpException;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.util.DateUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class SchemeDomainService {

	@Autowired
	private SchemeRepo schemeRepo;

	@Autowired
	private TargetTypeRepo targetTypeRepo;

	@Autowired
	private TargetRepo targetRepo;

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private ObjectTargetScoreCalculator objectTargetScoreCalculator;
	
	@Autowired
	private ObjectScoreCalculator objectScoreCalculator;

	/**
	 * @param schemeId
	 * @param targetId
	 * @param objectIds
	 * @throws GpException
	 * @roseuid 5739B756009C
	 */
	public void target2object(String schemeId, String targetId, List<SchemeObjectVO> objects) throws TargetException {
		Scheme scheme = schemeRepo.findOne(schemeId);
		if (scheme == null) {
			throw new TargetException("方案id=" + schemeId + " 不存在");
		}

		scheme.target2object(targetId, objects);

		applicationContext.publishEvent(new Target2ObjectCreatedEvent(schemeId, targetId, objects));

	}

	/**
	 * @param objectTargetExecute
	 * @throws TargetException
	 * @roseuid 573ADFCF01DA
	 */
	public void syncScore(ObjectTargetExecute execute) throws TargetException {

		objectTargetScoreCalculator.calculate(execute.getObjectTarget());

		execute.getObjectTarget().save();

		objectScoreCalculator.calculate(execute.getObjectTarget().getObject());

		execute.getObjectTarget().getObject().save();
	}

	/**
	 * @param schemeId
	 * @throws TargetException 
	 * @roseuid 573BC90901EE
	 */
	public List<TargetType> getTargetTypes(String schemeId) throws TargetException {
		
		Scheme scheme = this.schemeRepo.findOne(schemeId);
		if (scheme == null) {
			throw new TargetException("schemeId=" + schemeId + "不存在");
		}
		
		return scheme.getTargetTypes();
	}

	public Scheme createScheme(Scheme scheme, User user) {
		scheme.setState(Scheme.State_Init);
		scheme.setCreateDate(DateUtil.getSysDate());
		scheme.setCreateOrg(user.getOrgId());
		scheme.setCreator(user.getId());

		scheme.save();

		applicationContext.publishEvent(new SchemeCreatedEvent(scheme));

		return scheme;
	}

	public Scheme getScheme(String schemeId) {
		return this.schemeRepo.findOne(schemeId);
	}

	public void createTarget(Target target) throws TargetException {
		Scheme scheme = this.schemeRepo.findOne(target.getScheme().getId());
		if (scheme == null) {
			throw new TargetException("schemeId=" + target.getScheme().getId() + "不存在");
		}

		if (scheme.getState().equals(Scheme.State_Init)) {
			// 自动计算level
			if (target.getParent() == null) {
				if (scheme.getTargetLevelCount() > 0) {
					throw new TargetException("方案规定的指标层次为" + scheme.getTargetLevelCount() + "，不能直接建立指标");
				}
				target.setLevel(1);
			} else {
				TargetType parent = this.targetTypeRepo.findOne(target.getParent().getId());
				if (parent == null) {
					throw new TargetException("targetTypeId=" + target.getParent().getId() + "不存在");
				}
				if (scheme.getTargetLevelCount() != parent.getLevel() + 1) {
					throw new TargetException("必须在方案规定的指标层次（" + scheme.getTargetLevelCount() + "）上建立考核指标");
				}
				target.setLevel(parent.getLevel() + 1);
			}
			target.save();
		} else {
			throw new TargetException("指标所在方案[" + scheme.getName() + "]已经处于[" + scheme.getState() + "]状态，不能新增和修改");
		}

	}

	public void createSchemeObject(SchemeObject object) throws TargetException {

		Scheme scheme = this.schemeRepo.findOne(object.getScheme().getId());
		if (scheme == null) {
			throw new TargetException("schemeId=" + object.getScheme().getId() + "不存在");
		}
		if (scheme.getState().equals(Scheme.State_Init)) {
			object.save();
		} else {
			throw new TargetException("被考核单元所在方案[" + scheme.getName() + "]已经处于[" + scheme.getState() + "]状态，不能新增和修改");
		}
	}

	public void createTargetType(TargetType targetType) throws TargetException {
		Scheme scheme = this.schemeRepo.findOne(targetType.getScheme().getId());
		if (scheme == null) {
			throw new TargetException("schemeId=" + targetType.getScheme().getId() + "不存在");
		}

		if (scheme.getState().equals(Scheme.State_Init)) {
			// 自动计算level
			if (targetType.getParent() == null) {
				targetType.setLevel(1);
			} else {
				TargetType parent = this.targetTypeRepo.findOne(targetType.getParent().getId());
				if (parent == null) {
					throw new TargetException("targetTypeId=" + targetType.getParent().getId() + "不存在");
				}
				if (scheme.getTargetLevelCount() <= parent.getLevel() + 1) {
					throw new TargetException("已经超过了方案规定的指标层次（" + scheme.getTargetLevelCount() + "）");
				}
				targetType.setLevel(parent.getLevel() + 1);
			}

			// 设置默认权重
			if (targetType.getWeight() == 0) {
				targetType.setWeight(TargetType.DEFAULT_WEIGHT);
			}
			targetType.save();
		} else {
			throw new TargetException("指标所在方案[" + scheme.getName() + "]已经处于[" + scheme.getState() + "]状态，不能新增和修改");
		}

	}
	
	/**
	 * @param schemeId
	 * @throws TargetException
	 * @roseuid 573C0EBC0297
	 */
	public void startScheme(String schemeId) throws TargetException {
		Scheme scheme = this.schemeRepo.findOne(schemeId);
		if (scheme == null) {
			throw new TargetException("schemeId=" + schemeId + "不存在");
		}

		applicationContext.publishEvent(new SchemeStartBeforeEvent(scheme));

		scheme.start();

		applicationContext.publishEvent(new SchemeStartedEvent(scheme));
	}
	
	public void closeScheme(String schemeId) throws TargetException {
		
		Scheme scheme = this.schemeRepo.findOne(schemeId);
		if (scheme == null) {
			throw new TargetException("schemeId=" + schemeId + "不存在");
		}

		applicationContext.publishEvent(new SchemeCloseBeforeEvent(scheme));

		scheme.close();

		applicationContext.publishEvent(new SchemeClosedEvent(scheme));
	}

	public TargetType getTargetType(String id) {
		return targetTypeRepo.findOne(id);
	}

	public Target getTarget(String schemeId, String targetId) {
		return targetRepo.findOne(targetId);
	}

	public void updateTarget(Target target) throws TargetException {
		Target t = targetRepo.findOne(target.getId());

		applicationContext.publishEvent(new TargetUpdateBeforeEvent(t, target));

		t.update(target);

		applicationContext.publishEvent(new TargetUpdatedEvent(t));
	}

	public Page<Scheme> listScheme(Pageable pageable) {
		return this.schemeRepo.findAll(pageable);
	}

	public List<Scheme> listScheme(int year) {
		return this.schemeRepo.findByYear(year);
	}
}
