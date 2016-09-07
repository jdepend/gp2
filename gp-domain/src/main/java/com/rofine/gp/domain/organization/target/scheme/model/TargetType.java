//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\TargetType.java

package com.rofine.gp.domain.organization.target.scheme.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "domain_target_type")
public class TargetType extends TargetComponent {

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL })
	private List<TargetType> children = new ArrayList<TargetType>();

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL })
	private List<Target> targets = new ArrayList<Target>();
	
	public List<TargetType> getChildren() {
		return children;
	}

	public void setChildren(List<TargetType> children) {
		this.children = children;
	}
	
	public void addChild(TargetType targetType){
		children.add(targetType);
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}
	
	public void addTarget(Target target){
		targets.add(target);
	}

	public void save() {
		this.getRepo("targetTypeRepo").save(this);
	}

}
