package com.rofine.gp.application.organization.target.plan;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.rofine.gp.application.organization.target.plan.schemeext.SchemeExt;
import com.rofine.gp.domain.organization.target.scheme.model.Scheme;

public class SchemeVO {

	private String id;

	private String name;

	private String state;

	private int year;

	private int targetLevelCount;

	private String description;

	private String creator;

	private Date createDate;

	private String createOrg;

	public SchemeVO() {
		super();
	}

	public SchemeVO(Scheme scheme, SchemeExt schemeExt) {
		if (scheme != null) {
			BeanUtils.copyProperties(scheme, this);
		}
		if (schemeExt != null) {
			BeanUtils.copyProperties(schemeExt, this);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTargetLevelCount() {
		return targetLevelCount;
	}

	public void setTargetLevelCount(int targetLevelCount) {
		this.targetLevelCount = targetLevelCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public Scheme getScheme() {

		Scheme scheme = new Scheme();

		BeanUtils.copyProperties(this, scheme);

		return scheme;

	}

	public SchemeExt getSchemeExt() {

		SchemeExt schemeExt = new SchemeExt();

		BeanUtils.copyProperties(this, schemeExt);

		return schemeExt;
	}

}
