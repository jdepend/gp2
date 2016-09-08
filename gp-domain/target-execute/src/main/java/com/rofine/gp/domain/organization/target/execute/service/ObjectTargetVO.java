package com.rofine.gp.domain.organization.target.execute.service;

public class ObjectTargetVO {

	private String id;

	private String subjectId;

	private String schemeId;

	private String targetId;
	private String targetName;

	private String objectCode;
	private String objectType;

	private String planFillId;
	private String planFillRoleId;

	private String planEvaluateId;
	private String planEvaluateRoleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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

	public String getPlanEvaluateId() {
		return planEvaluateId;
	}

	public void setPlanEvaluateId(String planEvaluateId) {
		this.planEvaluateId = planEvaluateId;
	}

	public String getPlanEvaluateRoleId() {
		return planEvaluateRoleId;
	}

	public void setPlanEvaluateRoleId(String planEvaluateRoleId) {
		this.planEvaluateRoleId = planEvaluateRoleId;
	}
}
