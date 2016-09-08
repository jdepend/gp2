package com.rofine.gp.platform.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射.
 * 
 */
@MappedSuperclass
public abstract class IdEntity2 extends SuperEntity {

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	private String id;

	public IdEntity2() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
