package com.rofine.gp.domain.organization.target.scheme;

import com.rofine.gp.domain.organization.target.scheme.model.SchemeObject;

public class SchemeObjectVO {

	private String objectId;

	private String roleId;

	private String fillId;

	private SchemeObject object;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFillId() {
		return fillId;
	}

	public void setFillId(String fillId) {
		this.fillId = fillId;
	}

	public SchemeObject getObject() {
		return object;
	}

	public void setObject(SchemeObject object) {
		this.object = object;
	}

}
