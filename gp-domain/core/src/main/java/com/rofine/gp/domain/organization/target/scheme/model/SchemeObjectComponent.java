//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\SchemeObjectComponent.java

package com.rofine.gp.domain.organization.target.scheme.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.rofine.gp.platform.entity.IdEntity;

@MappedSuperclass
public abstract class SchemeObjectComponent extends IdEntity {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Scheme.class)
	@JoinColumn(name = "scheme_id")
	private Scheme scheme;

	@Column(length = 128)
	private String name;

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
