//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\TargetComponent.java

package com.rofine.gp.domain.organization.target.scheme.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.rofine.gp.platform.entity.IdEntity;

@MappedSuperclass
public abstract class TargetComponent extends IdEntity {

	@Column(length=512)
	private String name;
	
	@Column
	private int level;

	@Column
	private int weight;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private TargetType parent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scheme_id")
	private Scheme scheme;
	
	public static final int DEFAULT_WEIGHT = 100;

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}

	public TargetType getParent() {
		return parent;
	}

	public void setParent(TargetType parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
