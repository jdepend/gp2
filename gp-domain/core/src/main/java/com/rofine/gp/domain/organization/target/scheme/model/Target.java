//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\Target.java

package com.rofine.gp.domain.organization.target.scheme.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.domain.organization.target.scheme.SchemeObjectVO;

@Entity
@Table(name = "domain_target")
public class Target extends TargetComponent {

	@Column(name = "subject_id", length = 36)
	private String subjectId;

	@Column(name = "evaluate_role_id", length = 36)
	private String evaluateRoleId;

	@Column(name = "evaluate_id", length = 36)
	private String evaluateId;

	@OneToMany(mappedBy = "target", cascade = { CascadeType.ALL })
	private List<ObjectTarget> objectTargets;

	@Column(name = "frequency_type", length = 32)
	private String frequencyType;

	/**
	 * 越小优先级越高
	 */
	@NotNull
	@Range(min = 1, max = 999)
	@Column(length = 3, nullable = false)
	private Integer priority = 999;

	@Transient
	private TargetStatVO targetStatVO;

	/**
	 * @param objectIds
	 * @throws TargetException
	 * @roseuid 5739B927028A
	 */
	public void relationObjects(List<SchemeObjectVO> objects) throws TargetException {

		for (SchemeObjectVO object : objects) {
			ObjectTarget objectTarget = new ObjectTarget();
			objectTarget.setWeight(this.getWeight());
			objectTarget.setTarget(this);
			objectTarget.setObject(object.getObject());
			objectTarget.setObjectCode(object.getObject().getObjectId());
			objectTarget.setObjectType(object.getObject().getType());
			objectTarget.setScheme(this.getScheme());
			objectTarget.setFillId(object.getFillId());
			objectTarget.setRoleId(object.getRoleId());

			objectTarget.createExecutes();

			this.objectTargets.add(objectTarget);
		}

		// this.save();
	}

	public void save() throws TargetException {
		this.getRepo("targetRepo").save(this);
	}

	/**
	 * @roseuid 573C163F03BD
	 */
	public boolean isRelationObject() {
		return this.objectTargets.size() != 0;
	}

	/**
	 * @roseuid 573C210C0187
	 */
	public void countObjectTarget() {

	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public List<ObjectTarget> getObjectTargets() {
		return objectTargets;
	}

	public void setObjectTargets(List<ObjectTarget> objectTargets) {
		this.objectTargets = objectTargets;
	}

	public String getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public String getEvaluateRoleId() {
		return evaluateRoleId;
	}

	public void setEvaluateRoleId(String evaluateRoleId) {
		this.evaluateRoleId = evaluateRoleId;
	}

	public TargetStatVO getTargetStatVO() {
		return targetStatVO;
	}

	public void setTargetStatVO(TargetStatVO targetStatVO) {
		this.targetStatVO = targetStatVO;
	}

	public void update(Target target) throws TargetException {
//		if (!this.subjectId.equals(target.getSubjectId())) {
//			this.subjectId = target.getSubjectId();
//
//			for (ObjectTarget objectTarget : this.objectTargets) {
//				for (ObjectTargetExecute execute : objectTarget.getObjectTargetExecutes()) {
//					// 影响未评分记录，对于已评分记录不影响
//					if (!execute.getState().equals(ObjectTargetExecute.State_Evaluated)) {
//						execute.setSubjectId(this.subjectId);
//					}
//				}
//			}
//
//		} else if (!this.frequencyType.equals(target.getFrequencyType())) {
//			boolean hasOperation = false;
//			L: for (ObjectTarget objectTarget : this.objectTargets) {
//				for (ObjectTargetExecute execute : objectTarget.getObjectTargetExecutes()) {
//					// 影响未操作记录
//					if (execute.hasOperation()) {
//						hasOperation = true;
//						break L;
//					}
//				}
//			}
//			if (hasOperation) {
//				throw new TargetException("指标[" + target.getName() + "]，已经有操作记录了，不能修改考核频次 ");
//			}
//		} else if (!this.getName().equals(target.getName())) {
//			this.setName(target.getName());
//
//			for (ObjectTarget objectTarget : this.objectTargets) {
//				for (ObjectTargetExecute execute : objectTarget.getObjectTargetExecutes()) {
//					execute.setTargetName(this.getName());
//				}
//			}
//		}

		this.save();

	}
}
