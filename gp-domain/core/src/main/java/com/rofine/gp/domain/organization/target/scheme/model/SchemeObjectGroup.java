//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\SchemeObjectGroup.java

package com.rofine.gp.domain.organization.target.scheme.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "domain_scheme_object_group")
public class SchemeObjectGroup extends SchemeObjectComponent {
	
	@OneToMany(mappedBy = "group", cascade = { CascadeType.ALL })
	private List<SchemeObject> objects;

	public List<SchemeObject> getObjects() {
		return objects;
	}

	public void setObjects(List<SchemeObject> objects) {
		this.objects = objects;
	}

	
}
