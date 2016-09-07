package com.rofine.gp.domain.organization.target.scheme.event;

import org.springframework.context.ApplicationEvent;

import com.rofine.gp.domain.organization.target.scheme.model.Target;

public class TargetUpdateBeforeEvent extends ApplicationEvent {

	private Target originalObject;

	private Target newObject;

	public TargetUpdateBeforeEvent(Object source) {
		super(source);
	}

	public TargetUpdateBeforeEvent(Target originalObject, Target newObject) {
		super(originalObject);
		this.originalObject = originalObject;
		this.newObject = newObject;
	}

	public Target getOriginalObject() {
		return originalObject;
	}

	public void setOriginalObject(Target originalObject) {
		this.originalObject = originalObject;
	}

	public Target getNewObject() {
		return newObject;
	}

	public void setNewObject(Target newObject) {
		this.newObject = newObject;
	}

}
