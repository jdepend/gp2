//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\ObjectTarget.java

package com.rofine.gp.domain.organization.target.scheme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetVO;
import com.rofine.gp.domain.organization.target.execute.ObjectTargetExecuteDomainServiceStub;
import com.rofine.gp.platform.entity.IdEntity2;

@Entity
@Table(name = "domain_object_target")
public class ObjectTarget extends IdEntity2 {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scheme_id")
	private Scheme scheme;

	@Column
	private Float score;

	@Column
	private int weight;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "object_id")
	private SchemeObject object;

	@Column(name = "object_code")
	private String objectCode;

	@Column(name = "object_type")
	private String objectType;

	@Column(name = "role_id")
	private String roleId;

	@Column(name = "fill_id")
	private String fillId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "target_id")
	private Target target;

	/**
	 * @throws TargetException
	 * @roseuid 573A70BC0137
	 */
	public void createExecutes() throws TargetException {
		ObjectTargetExecuteDomainServiceStub.getBean().createExecutes(this.target.getFrequencyType(), this.toObjectTargetVO());
	}

	/**
	 * @roseuid 573ABCCE0094
	 */
	protected void setScore() {

	}

	/**
	 * @roseuid 573ADE2202D2
	 */
	public void calculate() {

	}

	public SchemeObject getObject() {
		return object;
	}

	public void setObject(SchemeObject object) {
		this.object = object;
	}

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
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

	public ObjectTargetVO toObjectTargetVO() {
		ObjectTargetVO objectTargetVO = new ObjectTargetVO();

		objectTargetVO.setId(getId());
		objectTargetVO.setObjectCode(objectCode);
		objectTargetVO.setObjectType(objectType);
		objectTargetVO.setPlanEvaluateId(target.getEvaluateId());
		objectTargetVO.setPlanEvaluateRoleId(target.getEvaluateRoleId());
		objectTargetVO.setPlanFillId(fillId);
		objectTargetVO.setPlanFillRoleId(roleId);
		objectTargetVO.setSchemeId(scheme.getId());
		objectTargetVO.setSubjectId(target.getSubjectId());
		objectTargetVO.setTargetId(target.getId());
		objectTargetVO.setTargetName(target.getName());

		return objectTargetVO;
	}

	public void save() {
		this.getRepo("objectTargetRepo").save(this);
	}

}
