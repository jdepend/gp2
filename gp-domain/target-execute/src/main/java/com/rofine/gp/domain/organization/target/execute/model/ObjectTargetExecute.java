//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\execute\\ObjectTargetExecute.java

package com.rofine.gp.domain.organization.target.execute.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteConstant;
import com.rofine.gp.domain.organization.target.execute.ObjectTargetVO;
import com.rofine.gp.domain.organization.target.target.frequency.TargetFrequency;
import com.rofine.gp.platform.entity.IdEntity;

@Entity
@Table(name = "domain_object_target_execute")
public class ObjectTargetExecute extends IdEntity {

	@Column(name = "target_name", length = 512)
	private String targetName;

	@Column(name = "frequency_code", length = 32)
	private String frequencyCode;

	@Column(name = "frequency_name", length = 64)
	private String frequencyName;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "remind_date")
	private Date remindDate;

	@Column(name = "limit_date")
	private Date limitDate;

	@Column(name = "fill_score")
	private Float fillScore;

	@Column(name = "fill_id", length = 36)
	private String fillId;

	@Column(name = "fill_date")
	private Date fillDate;

	@Column(length = 32)
	private String state;

	@Column(name = "subject_id", length = 36)
	private String subjectId;

	@Column(name = "evaluate_score")
	private Float evaluateScore;

	@Column(name = "evaluate_id", length = 36)
	private String evaluateId;

	@Column(name = "evaluate_date")
	private Date evaluateDate;

	@Column(name = "plan_fill_id", length = 36)
	private String planFillId;

	@Column(name = "plan_fill_role_id", length = 36)
	private String planFillRoleId;

	@Column(name = "plan_evaluate_id", length = 36)
	private String planEvaluateId;

	@Column(name = "plan_evaluate_role_id", length = 36)
	private String planEvaluateRoleId;

	private String objectTargetId;

	@Column(name = "object_code", length = 36)
	private String objectCode;

	@Column(name = "object_type", length = 32)
	private String objectType;

	private String schemeId;

	private String targetId;

	public ObjectTargetExecute() {

	}

	/**
	 * @roseuid 573BDF66000E
	 */
	public ObjectTargetExecute(TargetFrequency targetFrequency,
			ObjectTargetVO objectTarget) {
		this.targetName = objectTarget.getTargetName();
		this.frequencyCode = targetFrequency.getCode();
		this.frequencyName = targetFrequency.getName();

		this.startDate = targetFrequency.getStartDate();
		this.endDate = targetFrequency.getEndDate();
		this.remindDate = targetFrequency.getRemindDate();
		this.limitDate = targetFrequency.getLimitDate();

		this.state = ObjectTargetExecuteConstant.State_Init;
		this.subjectId = objectTarget.getSubjectId();

		this.schemeId = objectTarget.getSchemeId();
		this.targetId = objectTarget.getTargetId();
		this.objectTargetId = objectTarget.getId();
		this.objectCode = objectTarget.getObjectCode();
		this.objectType = objectTarget.getObjectType();

		this.planFillId = objectTarget.getPlanFillId();
		this.planFillRoleId = objectTarget.getPlanFillRoleId();

		this.planEvaluateId = objectTarget.getPlanEvaluateId();
		this.planEvaluateRoleId = objectTarget.getPlanEvaluateRoleId();

	}

	public boolean hasOperation() {
		return this.state.equals(ObjectTargetExecuteConstant.State_Evaluating)
				|| this.state
						.equals(ObjectTargetExecuteConstant.State_Evaluated)
				|| this.state.equals(ObjectTargetExecuteConstant.State_Close);
	}

	/**
	 * @roseuid 573C21940144
	 */
	public void start() {
		this.state = ObjectTargetExecuteConstant.State_Filling;
	}

	public void close() {
		this.state = ObjectTargetExecuteConstant.State_Close;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getFrequencyCode() {
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(Date remindDate) {
		this.remindDate = remindDate;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public Float getFillScore() {
		return fillScore;
	}

	public void setFillScore(Float fillScore) {
		this.fillScore = fillScore;
	}

	public String getFillId() {
		return fillId;
	}

	public void setFillId(String fillId) {
		this.fillId = fillId;
	}

	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public Float getEvaluateScore() {
		return evaluateScore;
	}

	public void setEvaluateScore(Float evaluateScore) {
		this.evaluateScore = evaluateScore;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public Date getEvaluateDate() {
		return evaluateDate;
	}

	public void setEvaluateDate(Date evaluateDate) {
		this.evaluateDate = evaluateDate;
	}

	public String getPlanFillId() {
		return planFillId;
	}

	public void setPlanFillId(String planFillId) {
		this.planFillId = planFillId;
	}

	public String getPlanFillRoleId() {
		return planFillRoleId;
	}

	public void setPlanFillRoleId(String planFillRoleId) {
		this.planFillRoleId = planFillRoleId;
	}

	public String getPlanEvaluateRoleId() {
		return planEvaluateRoleId;
	}

	public void setPlanEvaluateRoleId(String planEvaluateRoleId) {
		this.planEvaluateRoleId = planEvaluateRoleId;
	}

	public String getPlanEvaluateId() {
		return planEvaluateId;
	}

	public void setPlanEvaluateId(String planEvaluateId) {
		this.planEvaluateId = planEvaluateId;
	}

	public String getObjectTargetId() {
		return objectTargetId;
	}

	public void setObjectTargetId(String objectTargetId) {
		this.objectTargetId = objectTargetId;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

}
