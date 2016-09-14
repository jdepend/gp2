package com.rofine.gp.application.organization.target.execute.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rofine.gp.platform.entity.IdEntity;

@Entity
@Table(name = "app_target_execute_audit")
public class ObjectTargetExecuteAudit extends IdEntity {

	@Column(name = "execute_id", length = 36)
	private String executeId;

	private boolean result;

	@Column(length = 2048)
	private String suggestion;

	@Column(name = "audit_id", length = 36)
	private String auditId;

	@Column(name = "audit_name", length = 32)
	private String auditName;

	@Column(name = "audit_date")
	private Date auditDate;

	public static final String State_Fill_Auditing = "fill_auditing";

	public String getExecuteId() {
		return executeId;
	}

	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

}
